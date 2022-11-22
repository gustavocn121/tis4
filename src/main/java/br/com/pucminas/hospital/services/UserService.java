package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PermissionMapper;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.PermissionDTO;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.Permission;
import br.com.pucminas.hospital.model.entity.User;
import br.com.pucminas.hospital.model.request.CreateUser;
import br.com.pucminas.hospital.repository.PermissionRepository;
import br.com.pucminas.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Autowired
    PermissionRepository permissionRepository;

    PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
    }

    public void setRecoveryToken(String recoveryToken, String username){
        var user = repository.findByUserName(username)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        user.setRecoveryToken(recoveryToken);
        repository.save(user);
    }

    public Boolean validateRecoveryToken(String recoveryToken, String username){
        var user = repository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (user.getRecoveryToken().equals(recoveryToken)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public UserDTO getUserByUsername(String username) {

        var user = repository.findByUserName(username).orElseThrow();

        return UserMapper.INSTANCE.entityToDto(user);
    }
    
    public UserDTO createUser(CreateUser createUserData) {

        validateUserAlreadyRegistered(createUserData.getUserName());

        UserDTO userDTO = UserDTO.getFromCreateUserData(createUserData);

        String description = createUserData.getIsAdmin() ? "ADMIN" : "USER";

        Permission permissionEntity = permissionRepository.findByDescription(description).orElseThrow(() -> new ResourceNotFoundException("Permissão de usuário não encontrada"));

        User user = userSecurityConfig(userDTO);

        user.setUserName(createUserData.getUserName());

        user.setPermission(List.of(permissionEntity));

        User userEntity = repository.save(user);

        userDTO.setIdUser(userEntity.getIdUser());

        PermissionDTO permissionDTO = PermissionMapper.INSTANCE.entityToDto(permissionEntity);

        userDTO.setPermission(List.of(permissionDTO));

        return userDTO;
    }

    @Transactional
    public void changePassword(String username, String password) {
        loadUserByUsername(username);
        repository.changePasswordByUsername(username, passwordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("Username " + username + " não encontrado!"));
    }

    private User userSecurityConfig(UserDTO userDTO) {
        var user = UserMapper.INSTANCE.dtoToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        return user;
    }

    public void inactivateUser(String username) {
        var user = findUnregisteredUserByUsername(username);
        user.setEnabled(false);
        repository.save(user);
    }

    private User findUnregisteredUserByUsername(String username) {
        var user = repository.findByUserName(username);

        if (user.isPresent()) {
            throw new BusinesException("Usuário já cadastrado no sistema", HttpStatus.BAD_REQUEST);
        }

        return user.get();
    }

    private void validateUserAlreadyRegistered(String username) {
        var user = repository.findByUserName(username);

        if (user.isPresent()) {
            throw new BusinesException("Usuário já cadastrado no sistema", HttpStatus.BAD_REQUEST);
        }
    }

    public User getUserByRecoveryToken(String recoveryToken) {
        Optional<User> user = repository.findByRecoveryToken(recoveryToken);
        if(user.isEmpty()){
            throw new BusinesException("Recovery Token inválido", HttpStatus.BAD_REQUEST);
        }
        return user.get();
    }

    public UserDTO changePasswordByToken(String token, String password) {
        User user = getUserByRecoveryToken(token);
        changePassword(user.getUsername(), password);
        return UserMapper.INSTANCE.entityToDto(user);
    }
}