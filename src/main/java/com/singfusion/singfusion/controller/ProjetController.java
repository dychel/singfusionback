package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.DocumentService;
import com.singfusion.singfusion.service.ProjetService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/projet/*")
public class ProjetController {

    @Autowired
    ProjetService projetService;
    @Autowired
    DocumentService documentService;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> createProjetController(@RequestBody ProjetDTO projetDTO) {
        projetService.saveProjet(projetDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Projet "+ projetDTO.getIntitule()+ " Créé avec succès", projetDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllProjet() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des projets", projetService.listProjet()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbycontenu/{id}")
    public ResponseEntity<?> getProjetByDocument(@PathVariable(value = "id") Long id) {
        Document document=documentService.findDocumentById(id);
        if (document==null)
            throw new ApiRequestException("Ce document n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des projets", projetService.findProjetByIdDocument(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getProjetByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des projets ", projetService.findProjetByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findProjetById(@PathVariable(value = "id") Long id){
        Projet projet = projetService.findProjetById(id);
        if(projet==null)
            throw new ApiRequestException("Ce projet n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Projet trouvée", projet), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable(value = "id") Long id) {
        projetService.deleteProjetById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Projet supprime avec succes"), HttpStatus.OK);
    }

}
