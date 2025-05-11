package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.FaqDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/faq/*")
public class FaqController {

    @Autowired
    UserService userService;
    @Autowired
    FaqService faqService;


    @PostMapping("/add")
    public ResponseEntity<?> createQuizController(@RequestBody FaqDTO faqDTO) {
        faqService.saveFaq(faqDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Quiz "+ faqDTO.getTitre()+ " Créé avec succès", faqDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllFaq() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Faq Donnees", faqService.listFaqs()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getFaqByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Faq ", faqService.findFaqByIdUser(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findFaqById(@PathVariable(value = "id") Long id){
        Faq faq = faqService.findFaqById(id);
        if(faq==null)
            throw new ApiRequestException("Cette Faq n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Faq trouvée", faq), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteFaq(@PathVariable(value = "id") Long id) {
        faqService.deleteFaqById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Faq supprimee avec succes"), HttpStatus.OK);
    }

}
