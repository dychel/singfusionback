package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.PosteTravailDTO;
import com.singfusion.singfusion.entity.Postetravail;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.PostetravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/postetravail/*")
public class PosteTravailController {

    @Autowired
    PostetravailService postetravailService;

    @PostMapping("/add")
    public ResponseEntity<?> createPostetravail(@RequestBody PosteTravailDTO posteTravailDTO) {
        postetravailService.savePoste(posteTravailDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Poste travail "+ posteTravailDTO.getTitre()+ " Créé avec succès", posteTravailDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllPostetravail() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Poste travail ", postetravailService.listPoste()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findPostetravailById(@PathVariable(value = "id") Long id){
        Postetravail postetravail = postetravailService.findPosteById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Poste travail trouvé", postetravail), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePostetravailo(@PathVariable(value = "id") Long id) {
        postetravailService.deletePosteById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Poste travail supprime avec succes"), HttpStatus.OK);
    }
}
