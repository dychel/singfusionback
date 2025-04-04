package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ConnaissanceDonneeDTO;
import com.singfusion.singfusion.entity.ConnaissanceDonnee;
import java.util.List;

public interface ConnaissanceDonneeService {

    ConnaissanceDonnee saveConnaissanceDonnee(ConnaissanceDonneeDTO connaissanceDonneeDTO);
    ConnaissanceDonnee updateConnaissanceDonnee(Long id, ConnaissanceDonneeDTO connaissanceDonneeDTO);
    ConnaissanceDonnee findConnaissanceDonneeById(Long id);
    ConnaissanceDonnee findConnaissanceDonneeByIdUsers(Long id);
    ConnaissanceDonnee findConnaissanceDonneeByIdDocument(Long id);
    List<ConnaissanceDonnee> listConnaissanceDonnee();
    void deleteConnaissanceDonneeById(Long id);
}
