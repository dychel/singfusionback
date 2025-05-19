package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.AccesDTO;
import com.singfusion.singfusion.entity.Acces;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.AccesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccesServiceImpl implements AccesService {

    @Autowired
    AccesRepository accesRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Acces saveAcces(AccesDTO accesDTO) {
        Acces acces = modelMapper.map(accesDTO, Acces.class);
        return accesRepository.save(acces);
    }

    @Override
    public Acces updateAccess(Long id, AccesDTO accesDTO) {
        Acces accesToUpdate = accesRepository.findByIdAcces(id);
        if (accesToUpdate == null)
            throw new ApiRequestException("Acces non trouvé");
        //enregister les nouvelle infos
        Acces acces = modelMapper.map(accesDTO, Acces.class);
        acces.setId(accesToUpdate.getId());
        return accesRepository.save(acces);
    }

    @Override
    public Acces findAccessById(Long id) {
        return accesRepository.findByIdAcces(id);
    }

    @Override
    public List<Acces> listAccess() {
        List<Acces> listAcces = accesRepository.findAll();
        if (listAcces.isEmpty())
            throw new ApiRequestException("Pas d'acces enregister dans la base de donnees");
        return accesRepository.findAll();
    }

    @Override
    public void deleteAccessById(Long id) {
        accesRepository.deleteById(id);
    }
}
