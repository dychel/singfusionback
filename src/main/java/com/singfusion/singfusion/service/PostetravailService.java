package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.PosteTravailDTO;
import com.singfusion.singfusion.entity.Postetravail;
import java.util.List;

public interface PostetravailService {

    Postetravail savePoste(PosteTravailDTO posteTravailDTO);
    Postetravail updatePoste(Long id, PosteTravailDTO posteTravailDTO);
    Postetravail findPosteById(Long id);
    List<Postetravail> listPoste();
    void deletePosteById(Long id);
}
