package com.singfusion.singfusion.service;

import com.singfusion.singfusion.dto.VerificationEmailDTO;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.entity.VerificationEmail;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.VerificationEmailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class VerificationEmailServiceImpl implements VerificationEmailService{

    @Autowired
    VerificationEmailRepository verificationEmailRepository;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
//    @Autowired
//    JavaMailSender javaMailSender;
//    @Autowired
//    EmailService emailService;

    @Override
    public VerificationEmail getOtpByUsers(Long id) {
        VerificationEmail verificationEmail = verificationEmailRepository.findOtpByUser(id);
        if(!(verificationEmail ==null))
            return verificationEmail;
        return null;
    }

    @Override
    public VerificationEmail CheckOtpByUsers(VerificationEmailDTO verificationEmailDTO) {
        Users users = userService.getUserByEmail(verificationEmailDTO.getEmail());
        VerificationEmail verificationEmail = verificationEmailRepository.findOtpByUser(users.getId());
        if (Objects.equals(verificationEmail.getCodeOtp(), verificationEmailDTO.getCodeOtp()))
            return verificationEmail;
        throw new ApiRequestException("Otp incorrect");
    }

    @Override
    public VerificationEmail saveOtp(VerificationEmailDTO verificationEmailDTO) {
        Users users = userService.getUserByEmail(verificationEmailDTO.getEmail());
//        if (!(users ==null))
//            throw new ApiRequestException("Cette adresse email est déja associée à un compte");
        VerificationEmail verificationEmail = modelMapper.map(verificationEmailDTO, VerificationEmail.class);

        //get the otp code
        int otp;
        Random r = new Random( System.currentTimeMillis() );
        otp =(1 + r.nextInt(2)) * 10000 + r.nextInt(10000);

        //verificationEmail
        verificationEmailDTO.setCodeOtp(otp);
        verificationEmailDTO.setUserId(verificationEmailDTO.getUserId());
        verificationEmailDTO.setMessageMail("Le code de validation de votre adresse Email est envoyé");

        VerificationEmail check_if_exist = verificationEmailRepository.findOtpByUser(users.getId());
        //si otp déja envoyé au user, on met à jour
        if (!(check_if_exist ==null))
            return this.updateOtp(verificationEmailDTO);
        //send OTP to user by mail, mailtrap service used
        try {
//            emailService.sendEmail("dychel@shikam.com", "dycheloko@gmail.com","Vérification adresse email projet saas", "Le code verification de adresse email est le suivant: " + otp);
        }
        catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return verificationEmailRepository.save(verificationEmail);
    }

    @Override
    public VerificationEmail updateOtp(VerificationEmailDTO verificationEmailDTO) {
        Users users = userService.getUserByEmail(verificationEmailDTO.getEmail());
        VerificationEmail verificationEmailToUpdate = verificationEmailRepository.findOtpByUser(users.getId());
        currentdate = new Date(currentTimeInMillis);
        if (verificationEmailToUpdate == null)
            throw new ApiRequestException("Otp non valide");
        VerificationEmail verificationEmail = modelMapper.map(verificationEmailDTO, VerificationEmail.class);

        //get the otp code
        int otp;
        Random r = new Random( System.currentTimeMillis() );
        otp =(1 + r.nextInt(2)) * 10000 + r.nextInt(10000);

        //send OTP to user by email, mailtrap service used
        try {
//            emailService.sendEmail("dychel@shikam.com", "dycheloko@gmail.com","Vérification adresse email projet saas", "Le code verification de adresse email est le suivant: " + otp);
        }
        catch (Exception e) {
            throw new ApiRequestException("L'adresse mail entré est incorrecte'");
        }
        // others infos
        verificationEmail.setId(verificationEmailToUpdate.getId());
        verificationEmail.setCodeOtp(otp);
        verificationEmail.setUsers(users);
        verificationEmail.setMessageMail("Le code de validation de votre adresse Email est envoyé");
        // MAJ id users
        return verificationEmailRepository.save(verificationEmail);
    }
}
