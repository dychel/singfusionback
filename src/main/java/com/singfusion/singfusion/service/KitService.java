package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.KitDTO;
import com.singfusion.singfusion.entity.Kit;
import java.util.List;

public interface KitService {

    Kit saveKit(KitDTO kitDTO);
    Kit updateKit(Long id, KitDTO kitDTO);
    Kit findKitById(Long id);

    List<Kit> listKits();
    void deleteKitById(Long id);
}
