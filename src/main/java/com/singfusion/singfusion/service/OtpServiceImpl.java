package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.OtpDTO;
import com.singfusion.singfusion.entity.OtpUsers;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.OtpRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService{

    @Autowired
    OtpRepository otpRepository;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SMSService smsService;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override

    public OtpUsers getOtpByUsers(Long id) {
        OtpUsers otpUsers = otpRepository.findOtpByUser(id);
        if(!(otpUsers ==null))
            return otpUsers;
        return null;
    }

    @Override
    public OtpUsers CheckOtpByUsers(OtpDTO otpDTO) {
        Users users = userService.getUserByTelephone(otpDTO.getNumero_telephone());
        OtpUsers otpUsers = otpRepository.findOtpByUser(users.getId());
        if (Objects.equals(otpUsers.getCodeOtp(), otpDTO.getCodeOtp()))
            return otpUsers;
        throw new ApiRequestException("Otp incorrect");
    }

    @Override
    public OtpUsers saveOtp(OtpDTO otpDTO) {

        Users users = userService.getUserByTelephone(otpDTO.getNumero_telephone());
        OtpUsers otpUsers = modelMapper.map(otpDTO, OtpUsers.class);
        OtpUsers check_if_exist = otpRepository.findOtpByUser(users.getId());

        //get the otp code
        int otp;
        Random r = new Random( System.currentTimeMillis() );
        otp =(1 + r.nextInt(2)) * 10000 + r.nextInt(10000);

        otpUsers.setCodeOtp(otp);
        otpUsers.setUsers(users);

        //si otp déja envoyé au user, on met à jour
        if (!(check_if_exist ==null))
            return this.updateOtp(otpDTO);
        //send OTP to user
//        String message = smsService.sendSMS(otpDTO.getNumero_telephone(), "Le code de vérification pour se connecter est " + otp);
//        if (!message.equals("queued"))
//            throw new ApiRequestException("Numéro de télehone incorrect");
//
        return otpRepository.save(otpUsers);
    }

    @Override
    public OtpUsers updateOtp(OtpDTO otpDTO) {
        Users users = userService.getUserByTelephone(otpDTO.getNumero_telephone());
        OtpUsers otpToUpdate = otpRepository.findOtpByUser(users.getId());
        currentdate = new Date(currentTimeInMillis);
        if (otpToUpdate == null)
            throw new ApiRequestException("Otp non trouvé");
        OtpUsers otpUsers = modelMapper.map(otpDTO, OtpUsers.class);

        //get the otp code
        int otp;
        Random r = new Random( System.currentTimeMillis() );
        otp =(1 + r.nextInt(2)) * 10000 + r.nextInt(10000);

        //send OTP to user
//        String message = smsService.sendSMS(otpDTO.getNumero_telephone(), "Le code de vérification pour se connecter est " + otp);
//        if (!message.equals("queued"))
//            throw new ApiRequestException("Numéro de télehone incorrect");
//
        otpUsers.setId(otpToUpdate.getId());
        otpUsers.setCodeOtp(otp);
        otpUsers.setUsers(users);
        // MAJ id users
        return otpRepository.save(otpUsers);
    }
}
