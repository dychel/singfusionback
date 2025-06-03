package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.RapportEtonnementDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.RapportEtonnementRepository;
import com.singfusion.singfusion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class RapportEtonnementServiceImpl implements RapportEtonnementService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentService;
    @Autowired
    QuizService quizService;
    @Autowired
    RapportEtonnementRepository rapportEtonnementRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();

    @Autowired
    UserRepository userRepository;

    @Override
    public RapportEtonnement saveRapportEtonnement(RapportEtonnementDTO rapportEtonnementDTO) {
        RapportEtonnement rapportEtonnement = modelMapper.map(rapportEtonnementDTO, RapportEtonnement.class);
        if (rapportEtonnementDTO.getUserId() != null){
            rapportEtonnement.setUsers(userService.getUserById(rapportEtonnementDTO.getUserId()));
        }
        currentdate = new Date(currentTimeInMillis);
        rapportEtonnement.setDateAjout(currentdate);
        rapportEtonnement.setTitre("Etape Rapport activité, utilisateur "+ rapportEtonnementDTO.getUserId());
        return rapportEtonnementRepository.save(rapportEtonnement);
    }

    @Override
    public RapportEtonnement updateRapportEtonnement(Long id, RapportEtonnementDTO rapportEtonnementDTO) {
        RapportEtonnement rapportEtonnementToUpdate = rapportEtonnementRepository.findByIdRapport(id);
        currentdate = new Date(currentTimeInMillis);
        if (rapportEtonnementToUpdate == null)
            throw new ApiRequestException("rapport Etonnement ID non trouvé");
        RapportEtonnement rapportEtonnement = modelMapper.map(rapportEtonnementDTO, RapportEtonnement.class);
        rapportEtonnement.setId(rapportEtonnementDTO.getId());
        // si tout est okay on met a jour l'etat
        Users users = userRepository.findByIdUser(rapportEtonnementDTO.getUserId());
        users.setIsEtapes5Done(true);
        userRepository.save(users);
        // MAJ id users
        updateForeignKeyUsersDocumentQuiz(rapportEtonnementDTO, rapportEtonnement);
        return rapportEtonnementRepository.save(rapportEtonnement);
    }

    private void updateForeignKeyUsersDocumentQuiz(RapportEtonnementDTO rapportEtonnementDTO, RapportEtonnement rapportEtonnement) {
        // mettre a jour id Document si pas null
        if (rapportEtonnementDTO.getDocumentId() != null )
            rapportEtonnement.setDocument(documentService.findDocumentById(rapportEtonnementDTO.getDocumentId()));
        // mettre a jour id users si pas null
        if (rapportEtonnementDTO.getUserId() != null)
            rapportEtonnement.setUsers(userService.getUserById(rapportEtonnementDTO.getUserId()));
        if (rapportEtonnementDTO.getQuizId() != null)
            rapportEtonnement.setQuiz(quizService.findQuizById(rapportEtonnementDTO.getQuizId()));
    }

    @Override
    public RapportEtonnement findRapportEtonnementById(Long id) {
        return rapportEtonnementRepository.findByIdRapport(id);
    }

    @Override
    public RapportEtonnement findRapportEtonnementByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return rapportEtonnementRepository.findRapportByIdUsers(id);
    }

    @Override
    public RapportEtonnement findRapportEtonnementByIdQuiz(Long id) {
        Document document = documentService.findDocumentById(id);
        if (document == null)
            throw new ApiRequestException("Document non trouvé");
        return rapportEtonnementRepository.findEtonnementByIdDocument(id);
    }

    @Override
    public RapportEtonnement findRapportEtonnementByIdDocument(Long id) {
        return rapportEtonnementRepository.findEtonnementByIdDocument(id);
    }

    @Override
    public List<RapportEtonnement> listRapportEtonnement() {
        return rapportEtonnementRepository.findAll();
    }

    @Override
    public void deleteRapportEtonnementById(Long id) {
        RapportEtonnement rapportEtonnement = rapportEtonnementRepository.findByIdRapport(id);
        if (rapportEtonnement==null)
            throw new ApiRequestException("Rapport Etonnement non trouve");
        rapportEtonnementRepository.deleteById(id);
    }
}
