package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.ContenusDTO;
import com.singfusion.singfusion.entity.Contenus;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.ContenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/contenus/*")
public class ContenusController {

    @Autowired
    ContenusService contenusService;

    //contenu file or video to add
    @PostMapping("/add")
    public ResponseEntity<?> createContenu(@RequestBody ContenusDTO contenusDTO) {
        contenusService.saveContenus(contenusDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "contenu "+ contenusDTO.getIntutile()+ " Créé avec succès", contenusDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllContenu() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des contenus ", contenusService.listContenus()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findContenuById(@PathVariable(value = "id") Long id){
        Contenus contenus = contenusService.findContenusById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "contenu trouvé", contenus), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteContenu(@PathVariable(value = "id") Long id) {
        contenusService.deleteContenusById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "contenu supprime avec succes"), HttpStatus.OK);
    }
}
