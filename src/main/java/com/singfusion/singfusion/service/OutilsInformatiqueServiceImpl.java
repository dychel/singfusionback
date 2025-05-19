package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.dto.OutilsInformatiqueDTO;
import com.singfusion.singfusion.entity.OutilsInformatique;
import com.singfusion.singfusion.repository.OutilsInformatiqueReposittory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OutilsInformatiqueServiceImpl implements OutilsInformatiqueService{

    @Autowired
    OutilsInformatiqueReposittory outilsInformatiqueReposittory;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OutilsInformatique saveOutilsInfo(OutilsInformatiqueDTO outilsInformatiqueDTO) {
        OutilsInformatique outilsInformatique = modelMapper.map(outilsInformatiqueDTO, OutilsInformatique.class);
        return outilsInformatiqueReposittory.save(outilsInformatique);
    }

    @Override
    public OutilsInformatique updateOutilsInfo(Long id, KitDTO kitDTO) {
        return null;
    }

    @Override
    public OutilsInformatique findOutilsInformatiqueById(Long id) {
        return outilsInformatiqueReposittory.findByIdOutilsInformatique(id);
    }

    @Override
    public List<OutilsInformatique> listOutilsInformatique() {
        return outilsInformatiqueReposittory.findAll();
    }

    @Override
    public void deleteOutilsInformatiqueById(Long id) {
        outilsInformatiqueReposittory.deleteById(id);
    }
}
