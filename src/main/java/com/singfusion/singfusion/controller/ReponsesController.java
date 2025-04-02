package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.dto.ReponsesDTO;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Reponses;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.QuestionsService;
import com.singfusion.singfusion.service.ReponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/reponses/*")
public class ReponsesController {

    @Autowired
    ReponsesService reponsesService;

    @PostMapping("/add")
    public ResponseEntity<?> createReponses(@RequestBody ReponsesDTO reponsesDTO) {

        reponsesService.saveReponses(reponsesDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Reponse "+ reponsesDTO.getContenu()+ " Créé avec succès", reponsesDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllReponses() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Reponses ", reponsesService.listReponses()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findReponsesById(@PathVariable(value = "id") Long id){
        Reponses reponses = reponsesService.findReponsesById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Reponses trouvée", reponses), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteReponses(@PathVariable(value = "id") Long id) {
        reponsesService.deleteReponsesById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Reponses supprimée avec succes"), HttpStatus.OK);
    }
}
