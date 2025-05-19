package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.entity.Projet;
import java.util.List;

public interface ProjetService {
    Projet saveProjet(ProjetDTO projetDTO);
    Projet updateProjet(Long id, ProjetDTO projetDTO);
    Projet findProjetById(Long id);
    Projet findProjetByIdUsers(Long id);
    Projet findProjetByIdDocument(Long id);
    List<Projet> listProjet();
    void deleteProjetById(Long id);
}
