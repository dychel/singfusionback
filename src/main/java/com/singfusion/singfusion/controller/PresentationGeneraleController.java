package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.dto.PresentationGeneraleDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.PresentationGeneraleRepository;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.ContenusService;
import com.singfusion.singfusion.service.PresentationGeneraleService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/presentationgenerale/*")
public class PresentationGeneraleController {

    @Autowired
    PresentationGeneraleService presentationGeneraleService;
    @Autowired
    UserService userService;
    @Autowired
    ContenusService contenusService;

    @PostMapping("/add")
    public ResponseEntity<?> createPresentationGenerale(@RequestBody PresentationGeneraleDTO presentationGeneraleDTO) {
        PresentationGenerale presentationGeneraleExists = presentationGeneraleService.findPresentationGeneraleById(presentationGeneraleDTO.getUserId());
        if (presentationGeneraleExists!=null)
            throw new ApiRequestException("Deja effectue cette etape");
        presentationGeneraleService.savePresentationGenerale(presentationGeneraleDTO);
        PresentationGenerale presentationGenerale = presentationGeneraleService.findPresentationGeneraleByIdUsers(presentationGeneraleDTO.getUserId());
        return new ResponseEntity<>(new ResponseMessage("ok", "Presentation Generale "+ presentationGeneraleDTO.getTitre()+ " Créé avec succès", presentationGenerale),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllPresentationGenerale() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des PresentationGenerale ", presentationGeneraleService.listPresentationGenerale()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbycontenu/{id}")
    public ResponseEntity<?> getPresentationGeneraleByContenus(@PathVariable(value = "id") Long id) {
        Contenus contenus=contenusService.findContenusById(id);
        if (contenus==null)
            throw new ApiRequestException("Ce contenu n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des presentations ", presentationGeneraleService.findPresentationGeneraleByIdContenus(id)),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getPresentationGeneraleByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des presentations ", presentationGeneraleService.findPresentationGeneraleByIdUsers(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findPresentationGeneraleById(@PathVariable(value = "id") Long id){
        PresentationGenerale presentationGenerale = presentationGeneraleService.findPresentationGeneraleById(id);
        if(presentationGenerale==null)
            throw new ApiRequestException("Cette Presentation Generale n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Presentation Generale trouvée", presentationGenerale), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updatePresentation(@PathVariable(value = "id" ) Long id, @RequestBody PresentationGeneraleDTO presentationGeneraleDTO){
        presentationGeneraleService.updatePresentationGenerale(id, presentationGeneraleDTO);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Presentation Updated!", presentationGeneraleService.updatePresentationGenerale(id, presentationGeneraleDTO)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePresentationGenerale(@PathVariable(value = "id") Long id) {
        presentationGeneraleService.deletePresentationGeneraleById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Presentation Generale supprimee avec succes"), HttpStatus.OK);
    }

}
