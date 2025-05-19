package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.dto.RapportEtonnementDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.DocumentService;
import com.singfusion.singfusion.service.QuizService;
import com.singfusion.singfusion.service.RapportEtonnementService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/rapportetonnement/*")
public class RapportEtonnementController {

    @Autowired
    RapportEtonnementService rapportEtonnementService;
    @Autowired
    QuizService quizService;
    @Autowired
    DocumentService documentService;
    @Autowired
    UserService userService;


    @PostMapping("/add")
    public ResponseEntity<?> createrapportEtonnementController(@RequestBody RapportEtonnementDTO rapportEtonnementDTO) {
        rapportEtonnementService.saveRapportEtonnement(rapportEtonnementDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "rapport "+ rapportEtonnementDTO.getTitre()+ " Créé avec succès", rapportEtonnementDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllrapportEtonnement() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des rapport Etonnement", rapportEtonnementService.listRapportEtonnement()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbycontenu/{id}")
    public ResponseEntity<?> getrapportEtonnementByDocument(@PathVariable(value = "id") Long id) {
        Document document=documentService.findDocumentById(id);
        if (document==null)
            throw new ApiRequestException("Ce document n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des rapport Etonnement", rapportEtonnementService.findRapportEtonnementById(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getrapportEtonnementByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des rapport Etonnement ", rapportEtonnementService.findRapportEtonnementById(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyquiz/{id}")
    public ResponseEntity<?> getQuizByUser(@PathVariable(value = "id") Long id) {
        Quiz quiz =quizService.findQuizById(id);
        if (quiz==null)
            throw new ApiRequestException("Ce quiz n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des rapport Etonnement ", rapportEtonnementService.findRapportEtonnementByIdQuiz(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findrapportEtonnementById(@PathVariable(value = "id") Long id){
        RapportEtonnement rapportEtonnement = rapportEtonnementService.findRapportEtonnementById(id);
        if(rapportEtonnement==null)
            throw new ApiRequestException("Ce rapport n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Rapport trouvée", rapportEtonnement), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable(value = "id") Long id) {
        rapportEtonnementService.deleteRapportEtonnementById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "rapport supprime avec succes"), HttpStatus.OK);
    }

}
