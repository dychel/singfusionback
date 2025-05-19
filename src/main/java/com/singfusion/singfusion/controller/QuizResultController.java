package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.dto.QuizResultDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.ProjetService;
import com.singfusion.singfusion.service.QuizResultService;
import com.singfusion.singfusion.service.QuizService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/quizresult/*")
public class QuizResultController {

    @Autowired
    ProjetService projetService;
    @Autowired
    QuizResultService quizResultService;
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizResult;

    @PostMapping("/add")
    public ResponseEntity<?> createQuizResultController(@RequestBody QuizResultDTO quizResultDTO) {
        quizResultService.saveQuizResult(quizResultDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Quiz result "+ quizResultDTO.getTitre()+ " Créé avec succès", quizResultDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllResult() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Quiz Result", projetService.listProjet()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyquiz/{id}")
    public ResponseEntity<?> getQuizResultByQuiz(@PathVariable(value = "id") Long id) {
        Quiz quiz=quizResult.findQuizById(id);
        if (quizResult==null)
            throw new ApiRequestException("Ce quiz n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des quiz result", quizResultService.findQuizResultById(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getQuizResultByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Quiz Result ", projetService.findProjetByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findQuizResultById(@PathVariable(value = "id") Long id){
        QuizResult quizResult = quizResultService.findQuizResultById(id);
        if(quizResult==null)
            throw new ApiRequestException("Ce quiz result n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Quiz Result trouvé", quizResult), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteQuizResult(@PathVariable(value = "id") Long id) {
        quizResultService.deleteQuizResultById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Quiz Result supprime avec succes"), HttpStatus.OK);
    }

}
