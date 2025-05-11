package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.entity.Formalites;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.FormalitesService;
import com.singfusion.singfusion.service.QuestionsService;
import com.singfusion.singfusion.service.QuizService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/formalites/*")
public class FormalitesController {

    @Autowired
    FormalitesService formalitesService;
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> createFormalites(@RequestBody FormalitesDTO formalitesDTO) {

        formalitesService.saveFormalites(formalitesDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Formalites "+ formalitesDTO.getTitre()+ " Créé avec succès", formalitesDTO),
                HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateFormalites(@PathVariable(value = "id" ) Long id, @RequestBody FormalitesDTO formalitesDTO){
        formalitesService.updateFormalites(id, formalitesDTO);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Formalite Updated!", formalitesService.updateFormalites(id, formalitesDTO)), HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllFormalites() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des formalites ", formalitesService.listFormalites()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyquiz/{id}")
    public ResponseEntity<?> getFormalitesByQuiz(@PathVariable(value = "id") Long id) {
        Quiz quiz=quizService.findQuizById(id);
        if (quiz==null)
            throw new ApiRequestException("Ce quiz n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des formalites ", formalitesService.findFormalitesByIdQuiz(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getFormalitesByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des formalites ", formalitesService.findFormalitesByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findFormalitesById(@PathVariable(value = "id") Long id){
        Formalites formalites = formalitesService.findFormalitesById(id);
        if(formalites==null)
            throw new ApiRequestException("Cette formalite n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "formalite trouvée", formalites), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteFormalite(@PathVariable(value = "id") Long id) {
        formalitesService.deleteFormalitesById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Formalite supprimee avec succes"), HttpStatus.OK);
    }
}
