package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.DocumentDTO;
import com.singfusion.singfusion.entity.Document;
import java.util.List;

public interface DocumentService {

    Document saveDocument(DocumentDTO documentDTO);
    Document updateDocument(Long id, DocumentDTO documentDTO);
    Document findDocumentById(Long id);
    Document findDocumentByIdUsers(Long id);
    List<Document> listDocument();
    void deleteDocumentById(Long id);
}
