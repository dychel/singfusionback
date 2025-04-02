package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ContenusDTO;
import com.singfusion.singfusion.entity.Contenus;
import java.util.List;

public interface ContenusService {

    Contenus saveContenus(ContenusDTO contenusDTO);
    Contenus updateContenus(Long id, ContenusDTO contenusDTO);
    Contenus findContenusById(Long id);
    List<Contenus> listContenus();
    void deleteContenusById(Long id);
}
