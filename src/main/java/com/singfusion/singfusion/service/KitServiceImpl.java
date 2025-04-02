package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.entity.Acces;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.KitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
@Service
public class KitServiceImpl implements KitService{

    @Autowired
    KitRepository kitRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Kit saveKit(KitDTO kitDTO) {
        Kit kit = modelMapper.map(kitDTO, Kit.class);
        return kitRepository.save(kit);
    }

    @Override
    public Kit updateKit(Long id, KitDTO kitDTO) {
        Kit kitToUpdate = kitRepository.findByIdKit(id);
        if (kitToUpdate == null)
            throw new ApiRequestException("Kit non trouvé");
        //enregister les nouvelle infos
        Kit kit = modelMapper.map(kitDTO, Kit.class);
        kit.setId(kitToUpdate.getId());
        return kitRepository.save(kit);
    }

    @Override
    public Kit findKitById(Long id) {
        return kitRepository.findByIdKit(id);
    }

    @Override
    public List<Kit> listKits() {
        return kitRepository.findAll();
    }

    @Override
    public void deleteKitById(Long id) {
        kitRepository.deleteById(id);
    }
}
