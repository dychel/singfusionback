package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.OutilsInformatiqueDTO;
import com.singfusion.singfusion.entity.OutilsInformatique;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.OutilsInformatiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/outilsinformatique/*")
public class OutilsInformatiqueController {

    @Autowired
    OutilsInformatiqueService outilsInformatiqueService;

    @PostMapping("/add")
    public ResponseEntity<?> createOutilsInfo(@RequestBody OutilsInformatiqueDTO outilsInformatiqueDTO) {
        outilsInformatiqueService.saveOutilsInfo(outilsInformatiqueDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Outils informatique "+ outilsInformatiqueDTO.getTitre()+ " Créé avec succès", outilsInformatiqueDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllOutilsInfo() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Outils informatique ", outilsInformatiqueService.listOutilsInformatique()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findOutilsInfoById(@PathVariable(value = "id") Long id){
        OutilsInformatique outilsInformatique = outilsInformatiqueService.findOutilsInformatiqueById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Outils informatique trouvé", outilsInformatique), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteOutilsInfo(@PathVariable(value = "id") Long id) {
        outilsInformatiqueService.deleteOutilsInformatiqueById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Ouils informatique supprimee avec succes"), HttpStatus.OK);
    }
}
