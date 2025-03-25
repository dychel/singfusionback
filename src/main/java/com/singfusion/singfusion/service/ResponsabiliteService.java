package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.dto.ResponsabiliteDTO;
import com.singfusion.singfusion.entity.Responsabilite;
import java.util.List;

public interface ResponsabiliteService {

    Responsabilite saveResponsabilite(ResponsabiliteDTO responsabiliteDTO);
    Responsabilite updateResponsabilite(Long id, ResponsabiliteDTO responsabiliteDTO);
    Responsabilite findResponsabiliteById(Long id);

    List<Responsabilite> listResponsabilites();
    void deleteResponsabiliteById(Long id);
}
