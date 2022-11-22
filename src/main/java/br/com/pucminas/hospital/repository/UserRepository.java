package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.userName != :userName")
    List<User> findAllUserDiferentByUsername(@Param("userName") String userName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User user set user.password =:password where user.userName =:userName")
    void changePasswordByUsername(@Param("userName") String userName, @Param("password") String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByRecoveryToken(String recoveryToken);
}