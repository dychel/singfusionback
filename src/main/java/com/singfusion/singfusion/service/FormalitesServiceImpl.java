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
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    AccesRepository accesRepository;
    @Autowired
    OutilsInformatiqueReposittory outilsInformatiqueReposittory;
    @Autowired
    KitRepository kitRepository;
    @Autowired
    PosteRepository posteRepository;
    @Autowired
    ResponsabiliteRepository responsabiliteRepository;
    @Autowired
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizService;

    @Override
    public Formalites saveFormalites(FormalitesDTO formalitesDTO) {
        Formalites formalites = modelMapper.map(formalitesDTO, Formalites.class);

        if (formalitesDTO.getUserId()!=null){
            formalites.setUsers(userService.getUserById(formalitesDTO.getUserId()));
        }

        currentdate = new Date(currentTimeInMillis);
        formalites.setDateAjout(currentdate);
//        formalites.setDate_ajout(currentdate);
//
//        if (formalitesDTO.getQuizId()!=null){
//            formalites.setQuiz(quizService.findQuizById(formalitesDTO.getQuizId()));
//        }
//        //poste
//        if (formalitesDTO.getPostetravailId()!=null){
//            formalites.setPostetravail(formalites.getPostetravail());
//            formalites.setAsPoste(true);
//        }
//        //respo
//        if (formalitesDTO.getResponsabiliteId()!=null){
//            formalites.setResponsabilite(responsabiliteService.findResponsabiliteById(formalitesDTO.getResponsabiliteId()));
//            formalites.setAsVisited(true);
//        }
//        // acces
//        List<Long> list_dto= formalitesDTO.getAccesId();
//        //
//        if (list_dto!=null){
//            List<Acces> List_acces = accesRepository.findAllById(list_dto);
//            formalites.setAcces(List_acces);
//            formalites.setAsAccess(true);
//        }
//        //kit
//        List<Long> list_dto2= formalitesDTO.getKitId();
//        if (list_dto2!=null){
//            List<Kit> List_kit = kitRepository.findAllById(list_dto2);
//            formalites.setKit(List_kit);
//            formalites.setAsKit(true);
//        }
//        //outils informatique
//        List<Long> list_dto3= formalitesDTO.getOutilsInformatiqueId();
//        if (list_dto3!=null){
//            List<OutilsInformatique> List_outils = outilsInformatiqueReposittory.findAllById(list_dto3);
//            formalites.setOutilsInformatique(List_outils);
//            formalites.setAsOutilsInfo(true);
//        }
//        // mise a jour
//        if (formalites.getAsKit() && formalites.getAsAccess() && formalites.getAsVisited() && formalites.getAsOutilsInfo() && formalites.getAsPoste()){
//            formalites.setIsFinished(true);
//            // si tout est okay on met a jour l'etat
//            Users users2 = userRepository.findByIdUser(formalitesDTO.getUserId());
//            users2.setIsEtapes1Done(true);
//            userRepository.save(users2);
//        }
        return formalitesRepository.save(formalites);
    }

    @Override
    public Formalites updateFormalites(Long id, FormalitesDTO formalitesDTO) {
        Formalites formalitesToUpdate = formalitesRepository.findByIdFormalites(id);
        if (formalitesToUpdate == null)
            throw new ApiRequestException("Formalites ID non trouvé");
        currentdate = new Date(currentTimeInMillis);
        Formalites formalites = modelMapper.map(formalitesDTO, Formalites.class);
        // on recupere le user
        formalites.setId(formalitesToUpdate.getId());
        formalites.setUsers(formalitesToUpdate.getUsers());
        formalites.setDateAjout(currentdate);
        formalites.setTitre(formalitesToUpdate.getTitre());
        formalites.setDescritpion(formalitesToUpdate.getDescritpion());
        formalites.setAsVisited(formalitesToUpdate.getAsVisited());
        formalites.setAsAccess(formalitesToUpdate.getAsAccess());
        formalites.setAsOutilsInfo(formalitesToUpdate.getAsOutilsInfo());
        formalites.setAsKit(formalitesToUpdate.getAsKit());
        formalites.setAsPoste(formalitesToUpdate.getAsPoste());
        formalites.setResponsabilite(formalitesToUpdate.getResponsabilite());
        formalites.setPostetravail(formalitesToUpdate.getPostetravail());
        formalites.setOutilsInformatique(formalitesToUpdate.getOutilsInformatique());

        //quiz
        if (formalitesDTO.getQuizId()!=null){
            formalites.setQuiz(quizRepository.findByIdQuiz(formalitesDTO.getQuizId()));
        }
        //poste
        if (formalitesDTO.getPostetravailId()!=null){
            formalites.setPostetravail(posteRepository.findByIdPosteTravail(formalitesDTO.getPostetravailId()));
            formalites.setAsPoste(true);
        }
        //respo
        if (formalitesDTO.getResponsabiliteId()!=null){
            formalites.setAsVisited(true);
            formalites.setResponsabilite(responsabiliteRepository.findByIdResponsabilite(formalitesDTO.getResponsabiliteId()));
        }
        // acces
        List<Long> list_dto= formalitesDTO.getAccesId();
        //
        if (list_dto!=null){
            List<Acces> List_acces = accesRepository.findAllById(list_dto);
            formalites.setAcces(List_acces);
            formalites.setAsAccess(true);
        }else{
            formalites.setAcces(formalitesToUpdate.getAcces());
        }
        //kit
        List<Long> list_dto2= formalitesDTO.getKitId();
        if (list_dto2!=null){
            List<Kit> List_kit = kitRepository.findAllById(list_dto2);
            formalites.setKit(List_kit);
            formalites.setAsKit(true);
        }else{
            formalites.setKit(formalitesToUpdate.getKit());
        }
        //outils informatique
        List<Long> list_dto3= formalitesDTO.getOutilsInformatiqueId();
        if (list_dto3!=null){
            List<OutilsInformatique> List_outils = outilsInformatiqueReposittory.findAllById(list_dto3);
            formalites.setOutilsInformatique(List_outils);
            formalites.setAsOutilsInfo(true);
        }else{
            formalites.setOutilsInformatique(formalitesToUpdate.getOutilsInformatique());
        }
        // mise a jour
        if (formalites.getAsKit() && formalites.getAsAccess() && formalites.getAsVisited() && formalites.getAsOutilsInfo() && formalites.getAsPoste()){
            formalites.setIsFinished(true);
            // si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(formalitesToUpdate.getUsers().getId());
            users.setIsEtapes1Done(true);
            userRepository.save(users);
        }
        // MAJ id users
      //  updateForeignKeyQuizUsers(formalitesDTO, formalites);
        return formalitesRepository.save(formalites);
    }

    private void updateForeignKeyQuizUsers(FormalitesDTO formalitesDTO, Formalites formalites) {
        Users userTosave = userRepository.findByIdUser(formalitesDTO.getUserId());
        if (userTosave!=null){
            formalitesDTO.setUserId(userTosave.getId());
        }
        //quiz
        Quiz quiz = quizRepository.findByIdQuiz(formalitesDTO.getQuizId());
        if (formalitesDTO.getQuizId()!=null){
            formalites.setQuiz(formalites.getQuiz());
        }else {
            formalites.setQuiz(quiz);
        }
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
        if (list_dto!=null){
            List<Acces> List_acces = accesRepository.findAllById(list_dto);
            formalites.setAcces(List_acces);
            formalites.setAsAccess(true);
        }else{
            formalites.setAcces(formalites.getAcces());
        }
        //kit
        List<Long> list_dto2= formalitesDTO.getKitId();
        if (list_dto2!=null){
            List<Kit> List_kit = kitRepository.findAllById(list_dto2);
            formalites.setKit(List_kit);
            formalites.setAsKit(true);
        }else{
            formalites.setKit(formalites.getKit());
        }
        //outils informatique
        List<Long> list_dto3= formalitesDTO.getOutilsInformatiqueId();
        if (list_dto3!=null){
            List<OutilsInformatique> List_outils = outilsInformatiqueReposittory.findAllById(list_dto3);
            formalites.setOutilsInformatique(List_outils);
            formalites.setAsOutilsInfo(true);
        }else{
            formalites.setOutilsInformatique(formalites.getOutilsInformatique());
        }
        //visited
        if (formalitesDTO.getAsVisited()!=null){
            formalites.setAsVisited(formalitesDTO.getAsVisited());
        }
        // mise a jour
        if (formalites.getAsKit() && formalites.getAsAccess() && formalites.getAsVisited() && formalites.getAsOutilsInfo() && formalites.getAsPoste()){
            formalites.setIsFinished(true);
            // si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(formalitesDTO.getUserId());
            users.setIsEtapes1Done(true);
            userRepository.save(users);
        }
    }
    @Override
    public Formalites findFormalitesById(Long id) {
        return formalitesRepository.findByIdFormalites(id);
    }

    @Override
    public Formalites findFormalitesByIdUsers(Long id) {
//        Users users = userRepository.findByIdUser(id);
//        if (users == null)
//            throw new ApiRequestException("User non trouvé");
        return formalitesRepository.findFormaliteByIdUsers(id);
    }

    @Override
    public Formalites findFormalitesByIdQuiz(Long id) {
//        Quiz quiz = quizRepository.findByIdQuiz(id);
//        if (quiz == null)
//            throw new ApiRequestException("Quiz non trouvé");
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
