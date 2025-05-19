package com.singfusion.singfusion.controller;
import com.singfusion.singfusion.dto.DocumentDTO;
import com.singfusion.singfusion.entity.Document;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.response.ResponseMessage;
import com.singfusion.singfusion.service.DocumentService;
import com.singfusion.singfusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/singfusion/document/*")
public class DocumentController {

    @Autowired
    DocumentService documentService;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> createDocumentController(@RequestBody DocumentDTO documentDTO) {
        documentService.saveDocument(documentDTO);
        return new ResponseEntity<>(new ResponseMessage("ok", "Document "+ documentDTO.getIntutile()+ " Créé avec succès", documentDTO),
                HttpStatus.OK);
    }

    @GetMapping(value ="/all")
    public ResponseEntity<?> getAllDocument() {
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des Document", documentService.listDocument()),
                HttpStatus.OK);
    }

    @GetMapping(value ="/findbyuser/{id}")
    public ResponseEntity<?> getDocumentByUser(@PathVariable(value = "id") Long id) {
        Users users =userService.getUserById(id);
        if (users==null)
            throw new ApiRequestException("Ce utilisateur n'existe pas");
        return new ResponseEntity<>(new ResponseMessage("ok", "Liste des documents ", documentService.findDocumentById(id)),
                HttpStatus.OK);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<ResponseMessage> findDocumentById(@PathVariable(value = "id") Long id){
        Document document = documentService.findDocumentById(id);
        if(document==null)
            throw new ApiRequestException("Cet document n'existe pas");
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("ok", "Document trouvée", document), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable(value = "id") Long id) {
        documentService.deleteDocumentById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("delete", "Document supprimee avec succes"), HttpStatus.OK);
    }
}
