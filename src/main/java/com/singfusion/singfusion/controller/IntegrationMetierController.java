package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.IntegrationMetierDTO;
import com.singfusion.singfusion.dto.PresentationGeneraleDTO;
import com.singfusion.singfusion.entity.Contenus;
import com.singfusion.singfusion.entity.IntegrationMetier;
import com.singfusion.singfusion.entity.PresentationGenerale;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.ContenusService;
import com.singfusion.singfusion.service.IntegrationMetierService;
import com.singfusion.singfusion.service.QuizService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/integrationmetier/*")
public class IntegrationMetierController {

    @Autowired
    IntegrationMetierService integrationMetierService;
    @Autowired
    UserService userService;
    @Autowired
    ContenusService contenusService;
    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> createIntegrationMetierController(@RequestBody IntegrationMetierDTO integrationMetierDTO) {
        integrationMetierService.saveIntegrationMetier(integrationMetierDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Integration Metier "+ integrationMetierDTO.getTitre()+ " Créé avec succès", integrationMetierDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllIntegrationMetier() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Integration Metier ", integrationMetierService.listIntegrationMetier()),
                HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateIntegrationMetier(@PathVariable(value = "id" ) Long id, @RequestBody IntegrationMetierDTO integrationMetierDTO){
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Integration Metier Updated!", integrationMetierService.updateIntegrationMetier(id, integrationMetierDTO)), HttpStatus.OK);
    }

    @GetMapping(value ="/findbycontenu/{id}")
    public ResponseEntity<?> getIntegrationMetierrByContenus(@PathVariable(value = "id") Long id) {
        Contenus contenus=contenusService.findContenusById(id);
        if (contenus==null)
            throw new ApiRequestException("Ce contenu n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Integration metier ", integrationMetierService.findIntegrationMetierByIdContenus(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getIntegrationMetierByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des presentations ", integrationMetierService.findIntegrationMetierByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findIntegrationMetierById(@PathVariable(value = "id") Long id){
        IntegrationMetier integrationMetier = integrationMetierService.findIntegrationMetierById(id);
        if(integrationMetier==null)
            throw new ApiRequestException("Cette integration metier Generale n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Presentation Generale trouvée", integrationMetier), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteIntegrationMetier(@PathVariable(value = "id") Long id) {
        integrationMetierService.deleteIntegrationMetierById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Integration Generale supprimee avec succes"), HttpStatus.OK);
    }

}
