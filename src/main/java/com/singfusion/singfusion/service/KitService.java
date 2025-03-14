package com.singfusion.singfusion.service;
import com.singfusion.singfusion.entity.Kit;
import java.util.List;

public interface KitService {

    Kit saveKit(Kit kit);
    Kit updateKit(Long id, Kit kit);
    Kit findKitById(Long id);

    List<Kit> listKits();
    void deleteKitById(Long id);
}
