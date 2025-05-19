package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ReponsesDTO;
import com.singfusion.singfusion.entity.Reponses;
import java.util.List;

public interface ReponsesService {

    Reponses saveReponses(ReponsesDTO reponsesDTO);
    Reponses updateReponses(Long id, ReponsesDTO reponsesDTO);
    Reponses findReponsesById(Long id);
    List<Reponses> findReponsesByIdQuestions(Long id);
    List<Reponses> listReponses();
    void deleteReponsesById(Long id);
}
