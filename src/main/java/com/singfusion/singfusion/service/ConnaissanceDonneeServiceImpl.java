package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ConnaissanceDonneeDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.ConnaissanceDonneeRepository;
import com.singfusion.singfusion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ConnaissanceDonneeServiceImpl implements ConnaissanceDonneeService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentService;
    @Autowired
    QuizService quizService;
    @Autowired
    private ConnaissanceDonneeRepository connaissanceDonneeRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    UserRepository userRepository;
    @Override
    public ConnaissanceDonnee saveConnaissanceDonnee(ConnaissanceDonneeDTO connaissanceDonneeDTO) {
        ConnaissanceDonnee connaissanceDonnee = modelMapper.map(connaissanceDonneeDTO, ConnaissanceDonnee.class);
        if (connaissanceDonneeDTO.getUserId() != null){
            connaissanceDonnee.setUsers(userService.getUserById(connaissanceDonneeDTO.getUserId()));
        }
        currentdate = new Date(currentTimeInMillis);
        connaissanceDonnee.setDateAjout(currentdate);
        connaissanceDonnee.setTitre("Etapes Connaissance des donnéés, utilisateur "+ connaissanceDonneeDTO.getUserId());
        return connaissanceDonneeRepository.save(connaissanceDonnee);
    }

    @Override
    public ConnaissanceDonnee updateConnaissanceDonnee(Long id, ConnaissanceDonneeDTO connaissanceDonneeDTO) {
        ConnaissanceDonnee connaissanceDonneeToUpdate = connaissanceDonneeRepository.findByIdConnaissanceDonnee(id);
        currentdate = new Date(currentTimeInMillis);
        if (connaissanceDonneeToUpdate == null)
            throw new ApiRequestException("Connaissance ID non trouvé");
        ConnaissanceDonnee connaissanceDonnee = modelMapper.map(connaissanceDonneeDTO, ConnaissanceDonnee.class);
        connaissanceDonnee.setId(connaissanceDonneeToUpdate.getId());

        // si tout est okay on met a jour l'etat
        Users users = userRepository.findByIdUser(connaissanceDonneeDTO.getUserId());
        users.setIsEtapes4done(true);
        userRepository.save(users);
        // MAJ id users
        updateForeignKeyUsersDocument(connaissanceDonneeDTO, connaissanceDonnee);
        return connaissanceDonneeRepository.save(connaissanceDonnee);
    }

    private void updateForeignKeyUsersDocument(ConnaissanceDonneeDTO connaissanceDonneeDTO, ConnaissanceDonnee connaissanceDonnee) {
        // mettre a jour id Document si pas null
        if (connaissanceDonneeDTO.getDocumentId() != null )
            connaissanceDonnee.setDocument(documentService.findDocumentById(connaissanceDonneeDTO.getDocumentId()));
        // mettre a jour id users si pas null
        if (connaissanceDonneeDTO.getUserId() != null)
            connaissanceDonnee.setUsers(userService.getUserById(connaissanceDonneeDTO.getUserId()));
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeById(Long id) {
        return connaissanceDonneeRepository.findByIdConnaissanceDonnee(id);
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return connaissanceDonneeRepository.findConnaissanceDonneeByIdUsers(id);
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeByIdDocument(Long id) {
        Document document = documentService.findDocumentById(id);
        if (document == null)
            throw new ApiRequestException("Document non trouvé");
        return connaissanceDonneeRepository.findConnaissanceDonneeByIdDocument(id);
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeByIdQuiz(Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null)
            throw new ApiRequestException("Quiz non trouvé");
        return connaissanceDonneeRepository.findConnaissanceDonneeByIdDocument(id);
    }

    @Override
    public List<ConnaissanceDonnee> listConnaissanceDonnee() {
        return connaissanceDonneeRepository.findAll();
    }

    @Override
    public void deleteConnaissanceDonneeById(Long id) {
        ConnaissanceDonnee connaissanceDonnee = connaissanceDonneeRepository.findByIdConnaissanceDonnee(id);
        if (connaissanceDonnee==null)
            throw new ApiRequestException("ID Connaissance non trouve");
        connaissanceDonneeRepository.deleteById(id);
    }
}
