package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ResponsabiliteDTO;
import com.singfusion.singfusion.entity.Responsabilite;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.ResponsabiliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/responsabilite/*")
public class ResponsabiliteController {

    @Autowired
    ResponsabiliteService responsabiliteService;
    @PostMapping("/add")
    public ResponseEntity<?> createRespo(@RequestBody ResponsabiliteDTO responsabiliteDTO) {

        responsabiliteService.saveResponsabilite(responsabiliteDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "acces "+ responsabiliteDTO.getTitre()+ " Créé avec succès", responsabiliteDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllRespo() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des responsabilites ", responsabiliteService.listResponsabilites()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findRespoById(@PathVariable(value = "id") Long id){
        Responsabilite responsabilite = responsabiliteService.findResponsabiliteById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "responsabilite trouvé", responsabilite), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteRespo(@PathVariable(value = "id") Long id) {
        responsabiliteService.deleteResponsabiliteById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "responsabilite supprime avec succes"), HttpStatus.OK);
    }
}
