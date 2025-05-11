package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FormalitesServiceImpl implements FormalitesService{

    @Autowired
    FormalitesRepository formalitesRepository;
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizService;
    @Autowired
    AccesRepository accesRepository;
    @Autowired
    OutilsInformatiqueReposittory outilsInformatiqueReposittory;
    @Autowired
    KitRepository kitRepository;
    @Autowired
    ResponsabiliteService responsabiliteService;
    @Autowired
    PosteRepository posteRepository;
    @Autowired
    ResponsabiliteRepository responsabiliteRepository;

    @Autowired
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public Formalites saveFormalites(FormalitesDTO formalitesDTO) {
        Formalites formalites = modelMapper.map(formalitesDTO, Formalites.class);

        if (formalitesDTO.getPostetravailId()!=null)
                formalites.setAsPoste(true);
        // acces
        List<Long> list_dto= formalitesDTO.getAccesId();
        //
        if (!list_dto.isEmpty()){
            List<Acces> List_acces = accesRepository.findAllById(list_dto);
            formalites.setAcces(List_acces);
            formalites.setAsAccess(true);
        }
        //kit
        List<Long> list_dto2= formalitesDTO.getKitId();
        if (!list_dto2.isEmpty()){
            List<Kit> List_kit = kitRepository.findAllById(list_dto2);
            formalites.setKit(List_kit);
            formalites.setAsKit(true);
        }
        //outils informatique
        List<Long> list_dto3= formalitesDTO.getOutilsInformatiqueId();
        if (!list_dto2.isEmpty()){
            List<OutilsInformatique> List_outils = outilsInformatiqueReposittory.findAllById(list_dto3);
            formalites.setOutilsInformatique(List_outils);
            formalites.setAsKit(true);
        }
        return formalitesRepository.save(formalites);
    }

    @Override
    public Formalites updateFormalites(Long id, FormalitesDTO formalitesDTO) {
        Formalites formalitesToUpdate = formalitesRepository.findByIdFormalites(id);
        currentdate = new Date(currentTimeInMillis);
        if (formalitesToUpdate == null)
            throw new ApiRequestException("Formalites ID non trouvé");
        Formalites formalites = modelMapper.map(formalitesDTO, Formalites.class);
        formalites.setId(formalitesToUpdate.getId());
        formalites.setDate_ajout(currentdate);
        //poste
        Postetravail postetravail = posteRepository.findByIdPosteTravail(formalitesDTO.getPostetravailId());
        if (formalitesDTO.getPostetravailId()!=null){
            formalites.setPostetravail(formalites.getPostetravail());
            formalites.setAsPoste(true);
        }else {
            formalites.setPostetravail(postetravail);
        }
        //respo
        Responsabilite responsabilite = responsabiliteRepository.findByIdResponsabilite(formalitesDTO.getResponsabiliteId());
        if (formalitesDTO.getResponsabiliteId()!=null){
            formalites.setResponsabilite(formalites.getResponsabilite());
        }else {
            formalites.setResponsabilite(responsabilite);
        }
        // acces
        List<Long> list_dto= formalitesDTO.getAccesId();
        //
        if (!list_dto.isEmpty()){
            List<Acces> List_acces = accesRepository.findAllById(list_dto);
            formalites.setAcces(List_acces);
            formalites.setAsAccess(true);
        }else{
            formalites.setAcces(formalitesToUpdate.getAcces());
        }
        //kit
        List<Long> list_dto2= formalitesDTO.getKitId();
        if (!list_dto2.isEmpty()){
            List<Kit> List_kit = kitRepository.findAllById(list_dto2);
            formalites.setKit(List_kit);
            formalites.setAsKit(true);
        }else{
            formalites.setKit(formalitesToUpdate.getKit());
        }
        //outils informatique
        List<Long> list_dto3= formalitesDTO.getOutilsInformatiqueId();
        if (!list_dto2.isEmpty()){
            List<OutilsInformatique> List_outils = outilsInformatiqueReposittory.findAllById(list_dto3);
            formalites.setOutilsInformatique(List_outils);
            formalites.setAsKit(true);
        }else{
            formalites.setOutilsInformatique(formalitesToUpdate.getOutilsInformatique());
        }
        // MAJ id users
        updateForeignKeyQuizUsers(formalitesDTO, formalites);
        return formalitesRepository.save(formalites);
    }

    private void updateForeignKeyQuizUsers(FormalitesDTO formalitesDTO, Formalites formalites) {
        // mettre a jour id users si pas null
        List<Long> list_dto= formalitesDTO.getAccesId();
        // Find a list by all id and set questions value in user
        if (!list_dto.isEmpty()){
            List<Acces> List_acces = accesRepository.findAllById(list_dto);
            formalites.setAcces(List_acces);
        }
    }

    @Override
    public Formalites findFormalitesById(Long id) {
        return formalitesRepository.findByIdFormalites(id);
    }

    @Override
    public Formalites findFormalitesByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return formalitesRepository.findFormaliteByIdUsers(id);
    }

    @Override
    public Formalites findFormalitesByIdQuiz(Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null)
            throw new ApiRequestException("Quiz non trouvé");
        return formalitesRepository.findFormaliteByIdQuiz(id);
    }

    @Override
    public List<Formalites> listFormalites() {
        return formalitesRepository.findAll();
    }

    @Override
    public void deleteFormalitesById(Long id) {
        formalitesRepository.deleteById(id);
    }
}
