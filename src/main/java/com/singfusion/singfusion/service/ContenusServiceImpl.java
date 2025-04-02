package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ContenusDTO;
import com.singfusion.singfusion.entity.Contenus;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.ContenusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ContenusServiceImpl implements ContenusService {

    @Autowired
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    ContenusRepository contenusRepository;
    @Override
    public Contenus saveContenus(ContenusDTO contenusDTO) {
        Contenus contenus = modelMapper.map(contenusDTO, Contenus.class);
        return contenusRepository.save(contenus);
    }

    @Override
    public Contenus updateContenus(Long id, ContenusDTO contenusDTO) {
        Contenus contenusToUpdate = contenusRepository.findByIdContenus(id);
        if (contenusToUpdate == null)
            throw new ApiRequestException("Contenu non trouvé");
        //enregister les nouvelle infos
        currentdate = new Date(currentTimeInMillis);
        Contenus contenus = modelMapper.map(contenusDTO, Contenus.class);
        contenus.setDateAjout(currentdate);
        contenus.setId(contenusToUpdate.getId());
        return contenusRepository.save(contenus);
    }

    @Override
    public Contenus findContenusById(Long id) {
        return contenusRepository.findByIdContenus(id);
    }

    @Override
    public List<Contenus> listContenus() {
        List<Contenus> listContenus = contenusRepository.findAll();
        if (listContenus.isEmpty())
            throw new ApiRequestException("Pas de contenus enregister dans la base de donnees");
        return contenusRepository.findAll();
    }

    @Override
    public void deleteContenusById(Long id) {
        Contenus contenus = contenusRepository.findByIdContenus(id);
        if (contenus==null)
            throw new ApiRequestException("ID contenu non trouve");
        contenusRepository.deleteById(id);
    }
}
