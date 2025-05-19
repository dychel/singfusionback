package com.singfusion.singfusion.service;

import com.singfusion.singfusion.dto.AccesDTO;
import com.singfusion.singfusion.entity.Acces;
import com.singfusion.singfusion.entity.Kit;

import java.util.List;

public interface AccesService {

    Acces saveAcces(AccesDTO accesDTO);
    Acces updateAccess(Long id, AccesDTO accesDTO);
    Acces findAccessById(Long id);
    List<Acces> listAccess();
    void deleteAccessById(Long id);
}
