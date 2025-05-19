package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.PosteTravailDTO;
import com.singfusion.singfusion.entity.Postetravail;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.PosteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PosteTravailServiceImpl implements PostetravailService {

    @Autowired
    PosteRepository posteRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Postetravail savePoste(PosteTravailDTO posteTravailDTO) {
        Postetravail postetravail = modelMapper.map(posteTravailDTO, Postetravail.class);
        return posteRepository.save(postetravail);
    }

    @Override
    public Postetravail updatePoste(Long id, PosteTravailDTO posteTravailDTO) {
        Postetravail postetravailToUpdate = posteRepository.findByIdPosteTravail(id);
        if (postetravailToUpdate == null)
            throw new ApiRequestException("Poste non trouvé");
        //enregister les nouvelle infos
        Postetravail postetravail = modelMapper.map(posteTravailDTO, Postetravail.class);
        postetravail.setId(postetravailToUpdate.getId());
        return posteRepository.save(postetravail);
    }

    @Override
    public Postetravail findPosteById(Long id) {
        return posteRepository.findByIdPosteTravail(id);
    }

    @Override
    public List<Postetravail> listPoste() {
        return posteRepository.findAll();
    }

    @Override
    public void deletePosteById(Long id) {
        posteRepository.deleteById(id);
    }
}
