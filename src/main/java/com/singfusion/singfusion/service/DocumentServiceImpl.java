package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.DocumentDTO;
import com.singfusion.singfusion.dto.IntegrationMetierDTO;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.entity.Document;
import com.singfusion.singfusion.entity.IntegrationMetier;
import com.singfusion.singfusion.entity.Projet;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Override
    public Document saveDocument(DocumentDTO documentDTO) {
        Document document = modelMapper.map(documentDTO, Document.class);
        return documentRepository.save(document);
    }

    @Override
    public Document updateDocument(Long id, DocumentDTO documentDTO) {
        Document documentToUpdate= documentRepository.findByIdDocument(id);
        currentdate = new Date(currentTimeInMillis);
        if (documentToUpdate == null)
            throw new ApiRequestException("Document ID non trouvé");
        Document document = modelMapper.map(documentDTO, Document.class);
        document.setId(documentToUpdate.getId());
        document.setDateAjout(currentdate);
        // MAJ id users
        updateForeignKeyUsers(documentDTO, document);
        return documentRepository.save(document);
    }

    private void updateForeignKeyUsers(DocumentDTO documentDTO, Document document) {
        // mettre a jour id users si pas null
        if (documentDTO.getUsersId() != null)
            document.setUsers(userService.getUserById(documentDTO.getUsersId()));
    }

    @Override
    public Document findDocumentById(Long id) {
        return documentRepository.findByIdDocument(id);
    }

    @Override
    public Document findDocumentByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return documentRepository.findByIdDocument(id);
    }

    @Override
    public List<Document> listDocument() {
        return documentRepository.findAll();
    }

    @Override
    public void deleteDocumentById(Long id) {
        Document document = documentRepository.findByIdDocument(id);
        if (document==null)
            throw new ApiRequestException("ID document non trouve");
        documentRepository.deleteById(id);
    }
}
