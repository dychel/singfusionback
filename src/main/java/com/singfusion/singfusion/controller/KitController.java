package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.response.UserResponse;
import com.singfusion.singfusion.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/kit/*")
public class KitController {

    @Autowired
    KitService kitService;

    @PostMapping("/add")
    public ResponseEntity<?> createKit(@RequestBody KitDTO kitDTO) {

        kitService.saveKit(kitDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Kit "+ kitDTO.getNomdukit()+ " Créé avec succès", kitDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllKit() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des kits ", kitService.listKits()),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findKitById(@PathVariable(value = "id") Long id){
        Kit kit = kitService.findKitById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "kit trouvé", kit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteKit(@PathVariable(value = "id") Long id) {
        kitService.deleteKitById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "kit supprime avec succes"), HttpStatus.OK);
    }
}
