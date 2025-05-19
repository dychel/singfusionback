package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Document;
import com.singfusion.singfusion.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select document from Document document where document.id = :id")
    Document findByIdDocument(@Param("id") Long id);
    @Query("select document from Document document where document.id != :id")
    Projet findByIdDifferentDocument(@Param("id") Long id);
    @Query("select document from Document document where document.users.id = :id")
    Projet findDocumentByIdUsers(@Param("id") Long id);
}
