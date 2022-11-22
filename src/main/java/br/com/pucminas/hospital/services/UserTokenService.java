package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PermissionMapper;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.repository.UserRepository;
import br.com.pucminas.hospital.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTokenService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public List<UserDTO> findAllUsersByToken(String token) {

        var userName = jwtTokenProvider.getUserToken(token);
        var users = userRepository.findAllUserDiferentByUsername(userName);

        return UserMapper.INSTANCE.entityToDto(users);
    }

    public UserDTO findUserByToken(String token) {
        var username = jwtTokenProvider.getUserToken(token);

        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username " + username + " não encontrado!"));

        return UserMapper.INSTANCE.entityToDto(user);
    }

    @Transactional
    public UserDTO updateUser(String token, UserDTO userDTO) {
        var username = jwtTokenProvider.getUserToken(token);
        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username " + username + " não encontrado!"));

        user.setUserName(userDTO.getUserName());
        user.setFullName(userDTO.getFullName());

        var permission = userDTO.getPermission().stream().map(permissionDTO
                -> PermissionMapper.INSTANCE.dtoToEntity(permissionDTO)).collect(Collectors.toList());

        user.setPermission(permission);

        var u = userRepository.save(user);

        return UserMapper.INSTANCE.entityToDto(u);
    }
}