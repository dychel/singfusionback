package com.singfusion.singfusion.service;
import com.singfusion.singfusion.config.Status;
import com.singfusion.singfusion.dto.OtpDTO;
import com.singfusion.singfusion.dto.UserDTO;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Qualifier("getTest")
//    @Autowired
//    PasswordEncoder passwordEncoder;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
    private String message_response;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public List<Users> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public Long getCountUser() {
        return userRepository.countUsers();
    }
    @Override
    public Users updateUser(Long id, UserDTO userDTO) {
        String pwd = "test";
        Users userToUpdate = userRepository.findByIdUser(id);

        if (userToUpdate == null)
            return null;

        Users user = modelMapper.map(userDTO, Users.class);

        user.setId(userToUpdate.getId());
        user.setLastName(userDTO.getLastName().toUpperCase());
        user.setUsername(userDTO.getFirstName());
//      Gestion du Password pour ne pas encoder à nouveau le password déjà encoder
        if (userDTO.getPassword() == null || userDTO.getPassword().equalsIgnoreCase(userToUpdate.getPassword()))
            pwd = userToUpdate.getPassword();
        else
            pwd = passwordEncoder.encode(userDTO.getPassword());

        user.setPassword(pwd);
//        Gestion du role du user
        if (userDTO.getRoleId() != null)
            user.setRole(roleService.findRoleById(userDTO.getRoleId()));
        return userRepository.save(user);
    }

    @Override
    public Users updateUserPhoto(String email, MultipartFile photo_profile) throws IOException {

        if (photo_profile==null)
            throw new ApiRequestException("Veuillez insérer une photo svp");
        Users userToUpdate = userRepository.findByEmail(email);
        userToUpdate.setPhoto_profile_type(photo_profile.getContentType());
        userToUpdate.setPhoto_profile_name(photo_profile.getOriginalFilename());
        userToUpdate.setPhoto_profile_data(photo_profile.getBytes());
        return userRepository.save(userToUpdate);
    }

    @Override
    public Users updatePassword(OtpDTO otpDTO) {
        //update password
        String pwd = "test";
        Users userToUpdate = userRepository.findByTelephone(otpDTO.getNumero_telephone());
        if (userToUpdate==null)
            throw new ApiRequestException("user not found by phone number");
        //update user password
        //Users user = modelMapper.map(otpDTO, Users.class);
        userToUpdate.setId(userToUpdate.getId());
//        Gestion du Password pour ne pas encoder à nouveau le password déjà encoder
        if (otpDTO.getPassword() == null || otpDTO.getPassword().equalsIgnoreCase(userToUpdate.getPassword()))
            pwd = userToUpdate.getPassword();
        else
            pwd = passwordEncoder.encode(otpDTO.getPassword());
        userToUpdate.setPassword(pwd);
        return userRepository.save(userToUpdate);
    }

    @Override
    public Users getUserByUsername(String username) {
      //  Users user =
//        if (user==null)
//            throw new ApiRequestException("Cet nom n'est associée à aucun utilisateur");
        return userRepository.findByUsername(username);
    }

    @Override
    public Users getUserByEmail(String email) {
        Users user =userRepository.findByEmail(email);
        if (user==null)
            throw new ApiRequestException("Cette adresse email n'est associée à aucun utilisateur");
        return user;
    }

    @Override
    public Users getUserByToken(String token) {
        Users users = userRepository.findByToken(token);
        if (users==null)
            throw new ApiRequestException("Ce token n'est associé a aucun utilisateur");
        return users;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> getUserByRole(Long id) {
        return userRepository.findUserByRole(id);
    }

    @Override
    public Users getUserByTelephone(String numero_telephone) {
        Users users = userRepository.findByTelephone(numero_telephone);
        if (users==null)
            throw new ApiRequestException("Ce numéro n'est associé a aucun utilisateur");
        return users;
    }

    @Override
    public void connect(Users users) {

    }

    @Override
    public void RemoveToken(Users users, String jwt) {
        Optional<Users>  checkUser = userRepository.findById(users.getId());
        if (checkUser.isPresent()) {
            Users storedUser = checkUser.get();
            storedUser.setToken(jwt);
            userRepository.save(storedUser);
        }
    }

    @Override
    public void saveToken(Users users, String jwt) {
        Optional<Users> checkUser = userRepository.findById(users.getId());
        if (checkUser.isPresent()) {
            Users storedUser = checkUser.get();
            storedUser.setToken(jwt);
            userRepository.save(storedUser);
        }
    }

    @Override
    public Users disconnect(Users users) {
        return null;
    }

    @Override
    public Users connectUsers(Users users) {
        return null;
    }

    @Override
    public List<Users> getConnectedUsers() {
        return userRepository.findByStatus(Status.ONLINE);
    }

    @Override
    public Users saveUser(UserDTO userDTO) {
        //init message response
        message_response="";
        // Match DTO with entity
        Users user = modelMapper.map(userDTO, Users.class);
        //Mettre à jour les infos du role du user
        if (userDTO.getRoleId() != null){
            user.setRole(roleService.findRoleById(userDTO.getRoleId()));
        }
        //password encoder
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLastName(userDTO.getLastName().toUpperCase());
        user.setUsername(userDTO.getFirstName());
        //return the user just saved
        return userRepository.save(user);
    }
}
