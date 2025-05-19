package com.singfusion.singfusion.service;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSService {

    @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;

    @Autowired
    UserService userService;

    @PostConstruct
    private void setup(){
//        log.info("Account_SID " + ACCOUNT_SID);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String sendSMS(String smsNumber, String smsMessage){
        Users user = userService.getUserByTelephone(smsNumber);
        if (!(user ==null))
            throw new ApiRequestException("Le numéro existe déja");
        try{
        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();
            return  message.getStatus().toString();
        } catch (Exception e) {
            throw new ApiRequestException("Numéro incorrecte, impossible d'envoyer un message!");
        }

    }
}
