package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.AccesDTO;
import com.singfusion.singfusion.entity.Acces;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.AccesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/acces/*")
public class AccesController {

    @Autowired
    AccesService accesService;
    @PostMapping("/add")
    public ResponseEntity<?> createAcces(@RequestBody AccesDTO accesDTO) {

        accesService.saveAcces(accesDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "acces "+ accesDTO.getTitre()+ " Créé avec succès", accesDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllAcces() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des kits ", accesService.listAccess()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findAccesById(@PathVariable(value = "id") Long id){
        Acces acces = accesService.findAccessById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "acces trouvé", acces), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteAcces(@PathVariable(value = "id") Long id) {
        accesService.deleteAccessById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "acces supprime avec succes"), HttpStatus.OK);
    }
}
