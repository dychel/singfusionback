package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.PresentationGeneraleDTO;
import com.singfusion.singfusion.entity.Contenus;
import com.singfusion.singfusion.entity.PresentationGenerale;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.PresentationGeneraleRepository;
import com.singfusion.singfusion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class PresentationGeneraleServiceImpl implements PresentationGeneraleService {

    @Autowired
    PresentationGeneraleRepository presentationGeneraleRepository;
    @Autowired
    UserService userService;
    @Autowired
    ContenusService contenusService;
    @Autowired
    QuizService quizService;
    @Autowired
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    UserRepository userRepository;
    @Override
    public PresentationGenerale savePresentationGenerale(PresentationGeneraleDTO presentationGeneraleDTO) {
        PresentationGenerale presentationGenerale = modelMapper.map(presentationGeneraleDTO, PresentationGenerale.class);
        if (presentationGeneraleDTO.getUserId() != null){
            presentationGenerale.setUsers(userService.getUserById(presentationGeneraleDTO.getUserId()));
        }
        currentdate = new Date(currentTimeInMillis);
        presentationGenerale.setDateAjout(currentdate);
        presentationGenerale.setTitre("Etapes Presentation Generale utilisateur"+ presentationGeneraleDTO.getUserId());
        return presentationGeneraleRepository.save(presentationGenerale);
    }

    @Override
    public PresentationGenerale updatePresentationGenerale(Long id, PresentationGeneraleDTO presentationGeneraleDTO) {
        PresentationGenerale presentationGeneraleToUpdate = presentationGeneraleRepository.findByIdPresentationGenerale(id);
        currentdate = new Date(currentTimeInMillis);
        if (presentationGeneraleToUpdate == null)
            throw new ApiRequestException("PresentationGenerale ID non trouvé");
        PresentationGenerale presentationGenerale = modelMapper.map(presentationGeneraleDTO, PresentationGenerale.class);
        presentationGenerale.setId(presentationGeneraleToUpdate.getId());
        presentationGenerale.setQuiz((presentationGeneraleToUpdate.getQuiz()));
        presentationGenerale.setTitre((presentationGeneraleToUpdate.getTitre()));
        //
        presentationGenerale.setIsPowerPointRead(presentationGenerale.getIsPowerPointRead());
        presentationGenerale.setIsPowerPointRead2(presentationGenerale.getIsPowerPointRead2());
        presentationGenerale.setIsPowerPointRead3(presentationGenerale.getIsPowerPointRead3());
        presentationGenerale.setIsPowerPointRead4(presentationGenerale.getIsPowerPointRead4());

        presentationGenerale.setIsVideoWatched(presentationGenerale.getIsVideoWatched());
        presentationGenerale.setIsVideoWatched2(presentationGenerale.getIsVideoWatched2());
        presentationGenerale.setIsVideoWatched3(presentationGenerale.getIsVideoWatched3());
        presentationGenerale.setIsVideoWatched4(presentationGenerale.getIsVideoWatched4());
        //
        presentationGenerale.setDateAjout(presentationGeneraleToUpdate.getDateAjout());
        presentationGenerale.setDateMaj(currentdate);
        // MAJ id users
        if (presentationGenerale.getIsPowerPointRead() && presentationGenerale.getIsVideoWatched()){
            presentationGenerale.setIsFinished(true);

            // si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(presentationGeneraleDTO.getUserId());
            users.setIsEtapes2Done(true);
            userRepository.save(users);
        }
        updateForeignKeyUsersContenus(presentationGeneraleDTO, presentationGenerale);
        return presentationGeneraleRepository.save(presentationGenerale);
    }

    private void updateForeignKeyUsersContenus(PresentationGeneraleDTO presentationGeneraleDTO, PresentationGenerale presentationGenerale) {
        // mettre a jour id contenus si pas null
        if (presentationGeneraleDTO.getContenusId() != null )
            presentationGenerale.setContenus(contenusService.findContenusById(presentationGeneraleDTO.getContenusId()));
        // mettre a jour id users si pas null
        if (presentationGeneraleDTO.getUserId() != null)
            presentationGenerale.setUsers(userService.getUserById(presentationGeneraleDTO.getUserId()));
        // mettre a jour id quiz si pas null
        if (presentationGeneraleDTO.getQuizId() != null)
            presentationGenerale.setQuiz(quizService.findQuizById(presentationGeneraleDTO.getQuizId()));
    }

    @Override
    public PresentationGenerale findPresentationGeneraleById(Long id) {
        return presentationGeneraleRepository.findByIdPresentationGenerale(id);
    }

    @Override
    public PresentationGenerale findPresentationGeneraleByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return presentationGeneraleRepository.findPresentationGeneraleByIdUsers(id);
    }

    @Override
    public PresentationGenerale findPresentationGeneraleByIdQuiz(Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null)
            throw new ApiRequestException("Quiz non trouvé");
        return presentationGeneraleRepository.findPresentationGeneraleByIdQuiz(id);
    }

    @Override
    public PresentationGenerale findPresentationGeneraleByIdContenus(Long id) {
        Contenus contenus = contenusService.findContenusById(id);
        if (contenus == null)
            throw new ApiRequestException("Contenu non trouvé");
        return presentationGeneraleRepository.findPresentationGeneraleByIdContenus(id);
    }

    @Override
    public List<PresentationGenerale> listPresentationGenerale() {
        return presentationGeneraleRepository.findAll();
    }

    @Override
    public void deletePresentationGeneraleById(Long id) {
        PresentationGenerale presentationGenerale = presentationGeneraleRepository.findByIdPresentationGenerale(id);
        if (presentationGenerale==null)
            throw new ApiRequestException("ID presentationGenerale non trouve");
        presentationGeneraleRepository.deleteById(id);
    }
}
