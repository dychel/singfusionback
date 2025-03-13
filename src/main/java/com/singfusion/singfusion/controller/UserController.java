package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.*;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.response.UserResponse;
import com.singfusion.singfusion.security.jwt.JwtProvider;
import com.singfusion.singfusion.service.OtpService;
import com.singfusion.singfusion.service.RoleService;
import com.singfusion.singfusion.service.UserService;
import com.singfusion.singfusion.service.VerificationEmailService;
import com.singfusion.singfusion.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/users/*")
@Slf4j
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
//    @Qualifier("getTest")
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private String path_image;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    OtpService otpService;

//    @Autowired
//    VerificationEmailService verificationEmailService;

    Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
//
        //username remplacer par l'email
        logger.info("UserController: authenticateUser debut d'execution...");
       // logger.info("UserController: authenticateUser request {} ", Mapper.mapToJsonString(loginRequest.getEmail()));
        Users user = userService.getUserByEmail(loginRequest.getEmail());
        logger.info("UserController: authenticateUser user found {} ", Mapper.mapToJsonString(user.getEmail()));
//        if (!Objects.equals(user.getStatus(), statusActif)) {
//            return new ResponseEntity<>(new ResponseMessage("chao", "Compte bloqué!", null), HttpStatus.OK);
//        }

        //get username by email. the username is replace by email in login form
        String username = user.getUsername();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users myUser = userService.getUserByUsername(userDetails.getUsername());
        myUser.setToken(jwt);
        //save the token
        userService.saveToken(user, jwt);
        logger.info("UserController: authenticateUser  response {} ", Mapper.mapToJsonString(user.getEmail()));
        logger.info("UserController: authenticateUser  end ");
        return new ResponseEntity<>(new ResponseMessage("ok", "Utilisateur "+ myUser.getFirstName()+ " connecté avec succès", myUser),
                HttpStatus.OK);
//        return ResponseEntity.ok(new JwtResponse(
//                jwt,
//                myUser.getId(),
//                myUser.getFirstName(),
//                myUser.getLastName(),
//                myUser.getEmail(),
//                myUser.getUsername(),
//                myUser.getPassword(),
//                myUser.getStatus(),
//                myUser.getRole()));
    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<?> disconnectUser(@PathVariable(value = "id") Long id) {
        //username remplacer par l'email
        logger.info("UserController: disconnectUser debut d'execution...");
        logger.info("UserController: disconnectUser request {} ", Mapper.mapToJsonString(id));

        Users user = userService.getUserById(id);
        //set user chat_status to Online
        userService.disconnect(user);
        //get username by email. the username is replace by email in login form
        userService.RemoveToken(user, "");
        Users myUser = userService.getUserByUsername(user.getUsername());
        logger.info("UserController: disconnectUser  response {} ", Mapper.mapToJsonString(user.getEmail()));
        logger.info("UserController: disconnectUser  end");
        return new ResponseEntity<>(new ResponseMessage("ok", "Utilisateur "+ myUser.getFirstName()+ " déconnecté avec succès", myUser),
                HttpStatus.OK);
    }

    @GetMapping("/curr-user-details")
    public ResponseEntity<?> currentUserDetails() {
        logger.info("UserController: currentUserDetails debut d'execution...");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated())
            throw new ApiRequestException("Utilisateur non authentifier");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails==null)
            throw new ApiRequestException("Aucun user trouvé");
        Users users = userService.getUserByUsername(userDetails.getUsername());
        logger.info("UserController: currentUserDetails  response {} ", Mapper.mapToJsonString(users.getEmail()));
        logger.info("UserController: currentUserDetails  end");
        return new ResponseEntity<>(new ResponseMessage("ok", "Les informations de l'utilisateur connecté ", users),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(UserResponse.getMessageListUsers(userService.listUsers()), HttpStatus.OK);
    }

    @GetMapping(value ="/online/{id}")
    public ResponseEntity<?> OnlineMode(@PathVariable(value = "id") Long id) {
        Users users = userService.getUserById(id);
        return new ResponseEntity<>(new ResponseMessage("ok", "Vous etes en ligne", userService.connectUsers(users)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/offline/{id}")
    public ResponseEntity<?> OfflineMode(@PathVariable(value = "id") Long id) {
        Users users = userService.getUserById(id);
        return new ResponseEntity<>(new ResponseMessage("ok", "Vous etes en mode offline", userService.disconnect(users)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/count-user")
    public ResponseEntity<?> countUsers() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Nombre d'utilisateur ", userService.getCountUser()),
                HttpStatus.OK);
    }

//    @GetMapping(value ="/count-connected-user")
//    public ResponseEntity<?> countUsersConnected() {
//        return new ResponseEntity<>(new ResponseMessage("ok", "Nombre d'utilisateurs connectés", userService.getCountConnectedUser()),
//                HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
//        if (userService.getUserByUsername(userDTO.getUsername()) != null)
//            return new ResponseEntity<>(new ResponseMessage("exists", "This login already exists !", userDTO.getUsername()), HttpStatus.OK);
//        if (userService.getUserByEmail(userDTO.getEmail()) != null)
//            return new ResponseEntity<>(new ResponseMessage("exists", "This email already exists !", userDTO), HttpStatus.OK);
//        if (userService.getUserByTelephone(userDTO.getNumero_telephone()) != null)
//            return new ResponseEntity<>(new ResponseMessage("exists", "Ce numéro exist déja!", userDTO.getNumero_telephone()), HttpStatus.OK);
        //set user chat_status to Online
        userDTO.setStatus((byte) 1);
        userService.saveUser(userDTO);

        //Generate token
//        Users user = userService.getUserByEmail(userDTO.getEmail());
//        String username = user.getUsername();
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, userDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtProvider.generateJwtToken(authentication);
//        userDTO.setToken(jwt);
//        userDTO.setPassword("");
//        //
//        userService.connect(user);
//        Users user_to_display = userService.getUserByEmail(userDTO.getEmail());
//        user_to_display.setToken(jwt);
        return new ResponseEntity<>(new ResponseMessage("crée", "Utilisateur "+ userDTO.getFirstName()+ " Créé avec succès", userDTO),
                HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO) {
        Users userToUpdate = userService.getUserById(id);
        if (userToUpdate != null) {
//            Username unique
//            if (!(userDTO.getUsername().equalsIgnoreCase(userToUpdate.getUsername()))
//                    && userService.getUserByUsername(userDTO.getUsername()) != null) {
//                return new ResponseEntity<>(new ResponseMessage("chao", "This username already exists !", userDTO.getUsername()), HttpStatus.OK);
//            }
//            email unique
            if (!(userDTO.getEmail().equalsIgnoreCase(userToUpdate.getEmail()))
                    && userService.getUserByEmail(userDTO.getEmail()) != null) {
                return new ResponseEntity<>(new ResponseMessage("chao", "This email already exists !", userDTO.getEmail()), HttpStatus.OK);
            }
            userService.updateUser(id, userDTO);
            return new ResponseEntity<>(new ResponseMessage("modifié", "Mise à jour éffectué avec succès!", userDTO),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("chao", "The user you want to modify cannot be found !", null),
                    HttpStatus.OK);
        }
    }

    @PutMapping("/updatephoto/{email}")
    public ResponseEntity<?> updateProfilePhotoUser(@PathVariable("email") String email, @Valid @RequestBody MultipartFile photo_profile) throws IOException {
        Users userToUpdate = userService.getUserByEmail(email);
        if (userToUpdate != null) {
            userService.updateUserPhoto(email, photo_profile);
            return new ResponseEntity<>(new ResponseMessage("modifié", "Photo de profile mis à jour éffectué avec succès!", photo_profile.getOriginalFilename()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("chao", "Utilisateur non trouvé !", null),
                    HttpStatus.OK);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> saveOtp(@Valid @RequestBody OtpDTO otpDTO){
        return new ResponseEntity<>(new ResponseMessage("created", "Otp envoyé",otpService.saveOtp(otpDTO)), HttpStatus.OK);
    }

    @PutMapping("/check-otp")
    public ResponseEntity<?> checkotp(@Valid @RequestBody OtpDTO otpDTO){
        return new ResponseEntity<>(new ResponseMessage("created", "Otp vérifié",otpService.CheckOtpByUsers(otpDTO)), HttpStatus.OK);
    }

    // check email address
//    @PostMapping("/send-otp-bymail")
//    public ResponseEntity<?> sendOtpByEmail(@Valid @RequestBody VerificationEmailDTO verificationEmailDTO){
//        return new ResponseEntity<>(new ResponseMessage("created", "Otp envoyé",verificationEmailService.saveOtp(verificationEmailDTO)), HttpStatus.OK);
//    }
//
//    @PutMapping("/check-otp-sentbymail")
//    public ResponseEntity<?> checkotpSentByEmail(@Valid @RequestBody VerificationEmailDTO verificationEmailDTO){
//        return new ResponseEntity<>(new ResponseMessage("created", "Otp vérifié",verificationEmailService.CheckOtpByUsers(verificationEmailDTO)), HttpStatus.OK);
//    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> updatePassword( @Valid @RequestBody OtpDTO otpDTO) {
        return new ResponseEntity<>(new ResponseMessage("updated", "Mot de passe modifié",userService.updatePassword(otpDTO)), HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/findbyusername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable(value = "username") String username) {
        Users user = userService.getUserByUsername(username);
        if (user == null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("chao", "user not found !", null),
                    HttpStatus.OK);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "user found !", user),
                HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        Users user = userService.getUserById(id);

        if (user == null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("chao", "user not found", null),
                    HttpStatus.OK);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "user found", user),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(UserResponse.getMessageDeleteUserById(), HttpStatus.OK);
    }

}
