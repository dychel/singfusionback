package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.HashingDTO;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.EcobankHashSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaiementProcessController {

    @Autowired
    EcobankHashSercive ecobankHashSercive;
    @PostMapping("/hashing")
    public ResponseEntity<?> generateSecureHash(@RequestBody HashingDTO hashingDTO){
        return new ResponseEntity<>(new ResponseMessage("created", "Secure hash generated",ecobankHashSercive.Hashing(hashingDTO.payload)), HttpStatus.OK);
    }
}
