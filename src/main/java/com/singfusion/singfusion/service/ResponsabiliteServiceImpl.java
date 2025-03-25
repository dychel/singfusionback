package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.dto.ResponsabiliteDTO;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.entity.Responsabilite;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.ResponsabiliteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResponsabiliteServiceImpl implements ResponsabiliteService {

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    ResponsabiliteRepository responsabiliteRepository;
    @Override
    public Responsabilite saveResponsabilite(ResponsabiliteDTO responsabiliteDTO) {
        Responsabilite responsabilite = modelMapper.map(responsabiliteDTO, Responsabilite.class);
        return responsabiliteRepository.save(responsabilite);
    }

    @Override
    public Responsabilite updateResponsabilite(Long id, ResponsabiliteDTO responsabiliteDTO) {
        Responsabilite ResponsabiliteToUpdate = responsabiliteRepository.findByIdResponsabilite(id);
        if (ResponsabiliteToUpdate == null)
            throw new ApiRequestException("Responsabilite non trouvé");
        //enregister les nouvelle infos
        Responsabilite responsabilite = modelMapper.map(responsabiliteDTO, Responsabilite.class);
        responsabilite.setId(responsabilite.getId());
        return responsabiliteRepository.save(responsabilite);
    }

    @Override
    public Responsabilite findResponsabiliteById(Long id) {
        return responsabiliteRepository.findByIdResponsabilite(id);
    }

    @Override
    public List<Responsabilite> listResponsabilites() {
        return responsabiliteRepository.findAll();
    }

    @Override
    public void deleteResponsabiliteById(Long id) {
        responsabiliteRepository.deleteById(id);
    }
}
