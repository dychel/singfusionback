package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.PosteTravailDTO;
import com.singfusion.singfusion.dto.QuizDTO;
import com.singfusion.singfusion.entity.Postetravail;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.PostetravailService;
import com.singfusion.singfusion.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/quiz/*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO) {
        quizService.saveQuiz(quizDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Quiz "+ quizDTO.getTitre()+ " Créé avec succès", quizDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllQuiz() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Quiz ", quizService.listQuiz()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findQuizById(@PathVariable(value = "id") Long id){
        Quiz quiz = quizService.findQuizById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Quiz trouvé", quiz), HttpStatus.OK);
    }

    @GetMapping("findbyetapes/{etape_integration}")
    public ResponseEntity<ResponseMessage> findByEtapes(@PathVariable(value = "etape_integration") String etape_integration){
        Quiz quiz = quizService.getByEtapes(etape_integration);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Quiz trouvé", quiz), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable(value = "id") Long id) {
        quizService.deleteQuizById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Quiz travail supprime avec succes"), HttpStatus.OK);
    }
}
