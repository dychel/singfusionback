package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.PresentationGeneraleDTO;
import com.singfusion.singfusion.entity.PresentationGenerale;
import java.util.List;

public interface PresentationGeneraleService {

    PresentationGenerale savePresentationGenerale(PresentationGeneraleDTO presentationGeneraleDTO);
    PresentationGenerale updatePresentationGenerale(Long id, PresentationGeneraleDTO presentationGeneraleDTO);
    PresentationGenerale findPresentationGeneraleById(Long id);
    PresentationGenerale findPresentationGeneraleByIdUsers(Long id);
    PresentationGenerale findPresentationGeneraleByIdContenus(Long id);
    List<PresentationGenerale> listPresentationGenerale();
    void deletePresentationGeneraleById(Long id);
}
