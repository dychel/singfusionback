package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.dto.OutilsInformatiqueDTO;
import com.singfusion.singfusion.entity.OutilsInformatique;
import java.util.List;

public interface OutilsInformatiqueService {

    OutilsInformatique saveOutilsInfo(OutilsInformatiqueDTO outilsInformatiqueDTO);
    OutilsInformatique updateOutilsInfo(Long id, KitDTO kitDTO);
    OutilsInformatique findOutilsInformatiqueById(Long id);
    List<OutilsInformatique> listOutilsInformatique();
    void deleteOutilsInformatiqueById(Long id);
}
