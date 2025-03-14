package com.singfusion.singfusion.service;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
@Service
public class KitServiceImpl implements KitService{

    @Autowired
    KitRepository kitRepository;
    @Override
    public Kit saveKit(Kit kit) {
        kit.setDate_ajout(new Date());
        return kitRepository.save(kit);
    }

    @Override
    public Kit updateKit(Long id, Kit kit) {
        Kit kToUpdate = kitRepository.findByIdKit(id);

        if (kToUpdate == null)
            return null;
        return kitRepository.save(kToUpdate);
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
