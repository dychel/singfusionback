package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.config.Status;
import com.singfusion.singfusion.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    boolean existsByEmail(String email);
    @Query("select users from Users users where users.token = :token")
    Users findByToken(@Param("token") String token);
    @Query("select count(*) users from Users users")
    Long countUsers();

    @Query("select users from Users users where users.id = :id")
    Users findByIdUser(@Param("id") Long id);

    @Query("select users from Users users where users.email = :email")
    Users findByEmail(@Param("email") String email);

    @Query("select users from Users users where users.numero_telephone = :numero_telephone")
    Users findByTelephone(@Param("numero_telephone") String numero_telephone);

    @Query("select users from Users users where users.role.id = :id")
    List<Users> findUserByRole(@PathVariable("id") Long id);

    @Query("select users from Users users where users.role.roleName = :rolename")
    List<Users> getAllDonneur(@PathVariable("rolename") String rolename);

    @Query("select users from Users users where users.role.roleName = :rolename")
    List<Users> getAllReceveur(@PathVariable("rolename") String rolename);

//    @Query("select count(*) users from Users users where users.chat_status = :chat_status")
//    Long countUsersConnected(@Param("chat_status") Status chat_status);

    List<Users> findByStatus(Status status);

}
