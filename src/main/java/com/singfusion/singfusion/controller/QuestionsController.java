package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.QuestionsService;
import com.singfusion.singfusion.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/questions/*")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;
    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> createQuestions(@RequestBody QuestionsDTO questionsDTO) {

        questionsService.saveQuestions(questionsDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Question "+ questionsDTO.getContenu()+ " Créé avec succès", questionsDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllQuestions() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Questions ", questionsService.listQuestions()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyquiz/{id}")
    public ResponseEntity<?> getQuestionsByQuiz(@PathVariable(value = "id") Long id) {
        Quiz quiz=quizService.findQuizById(id);
        if (quiz==null)
            throw new ApiRequestException("Ce quiz n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Questions ", questionsService.listQuestions()),
                HttpStatus.OK);
    }
    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findQuestionsById(@PathVariable(value = "id") Long id){
        Questions questions = questionsService.findQuestionsById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "questions trouvé", questions), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteQuestions(@PathVariable(value = "id") Long id) {
        questionsService.deleteQuestionsById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Questions supprimee avec succes"), HttpStatus.OK);
    }
}
