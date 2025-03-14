package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/kit/*")
public class KitController {

    @Autowired
    KitService kitService;

    @PostMapping("/add")
    public ResponseEntity<?> createKit(@RequestBody Kit rRequest) {
        return null;
    }
}
