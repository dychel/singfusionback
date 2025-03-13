package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.OtpDTO;
import com.singfusion.singfusion.dto.UserDTO;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface UserService{

    List<Users> listUsers();

    Users getUserById(Long id);

    Long getCountUser();

    Users updateUser(Long id, UserDTO userDTO);

    Users updateUserPhoto(String email, MultipartFile photo_profile) throws IOException;

    Users updatePassword(OtpDTO otpDTO);

    Users getUserByUsername(String username);

    Users getUserByEmail(String email);

    Users getUserByToken(String token);

    void deleteUserById(Long id);

    List<Users> getUserByRole(Long id);

    Users getUserByTelephone(String numero_telephone);

    void connect(Users users);

    void RemoveToken(Users users, String jwt);

    void saveToken(Users users,String jwt);

    Users disconnect(Users users);

    Users connectUsers(Users users);

    List<Users> getConnectedUsers();

    Users saveUser(UserDTO userDTO);
}
