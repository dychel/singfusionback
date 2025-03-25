package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.model.Request;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

//    @Value("${STRIPE_PUBLIC}")
//    private String publicKey;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("request", new Request(100L,"dycheloko@gmail.com","Credit simple"));
        return "index";
    }

    @PostMapping("/")
    public String showCard(@ModelAttribute @Valid Request request,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            return "index";
        }
        //model.addAttribute("publicKey", publicKey);
        model.addAttribute("amount", request.getAmount());
        model.addAttribute("email", request.getEmail());
       // model.addAttribute("quantity", request.getQuantite());
        model.addAttribute("productName", request.getProductName());

        return "checkout";
    }
}
