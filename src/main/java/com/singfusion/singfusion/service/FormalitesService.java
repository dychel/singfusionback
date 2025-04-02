package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.entity.Formalites;
import java.util.List;

public interface FormalitesService {

    Formalites saveFormalites(FormalitesDTO formalitesDTO);
    Formalites updateFormalites(Long id, FormalitesDTO formalitesDTO);
    Formalites findFormalitesById(Long id);

    Formalites findFormalitesByIdUsers(Long id);

    Formalites findFormalitesByIdQuiz(Long id);

    List<Formalites> listFormalites();
    void deleteFormalitesById(Long id);
}
