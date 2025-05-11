package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ConnaissanceDonneeDTO;
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
@RequestMapping("/api/singfusion/connaissancedonnee/*")
public class ConnaissanceDonneeController {

    @Autowired
    ConnaissanceDonneeService connaissanceDonneeService;
    @Autowired
    DocumentService documentService;
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizService;


    @PostMapping("/add")
    public ResponseEntity<?> createConnaissanceDonneeController(@RequestBody ConnaissanceDonneeDTO connaissanceDonneeDTO) {
        connaissanceDonneeService.saveConnaissanceDonnee(connaissanceDonneeDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Connaissance donnee "+ connaissanceDonneeDTO.getTitre()+ " Créé avec succès", connaissanceDonneeDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllConnaissanceDonnee() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Connaissance Donnees", connaissanceDonneeService.listConnaissanceDonnee()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbycontenu/{id}")
    public ResponseEntity<?> getConnaissanceDonneeByDocument(@PathVariable(value = "id") Long id) {
        Document document=documentService.findDocumentById(id);
        if (document==null)
            throw new ApiRequestException("Ce document n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Connaissance Donnees", connaissanceDonneeService.findConnaissanceDonneeById(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getConnaissanceDonneeByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Connaissance Donnees ", connaissanceDonneeService.findConnaissanceDonneeByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyquiz/{id}")
    public ResponseEntity<ResponseMessage> findConnaissanceDonneeByIdQuiz(@PathVariable(value = "id") Long id){
        Quiz quiz = quizService.findQuizById(id);
        if(quiz==null)
            throw new ApiRequestException("Cette donnees n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Connaissance Donnee trouvée", quiz), HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findConnaissanceDonneeById(@PathVariable(value = "id") Long id){
        ConnaissanceDonnee connaissanceDonnee = connaissanceDonneeService.findConnaissanceDonneeById(id);
        if(connaissanceDonnee==null)
            throw new ApiRequestException("Cette donnees n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Connaissance Donnee trouvée", connaissanceDonnee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteConnaissanceDonnee(@PathVariable(value = "id") Long id) {
        connaissanceDonneeService.deleteConnaissanceDonneeById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Connaissance Donnee supprime avec succes"), HttpStatus.OK);
    }

}
