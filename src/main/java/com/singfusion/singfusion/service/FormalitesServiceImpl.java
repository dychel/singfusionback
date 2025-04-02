package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.entity.Formalites;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.FormalitesRepository;
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
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public Formalites saveFormalites(FormalitesDTO formalitesDTO) {
        Formalites formalites = modelMapper.map(formalitesDTO, Formalites.class);
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
        // MAJ id users
        updateForeignKeyQuizUsers(formalitesDTO, formalites);
        return formalitesRepository.save(formalites);
    }

    private void updateForeignKeyQuizUsers(FormalitesDTO formalitesDTO, Formalites formalites) {
        // mettre a jour id users si pas null
        if (formalitesDTO.getQuizId() != null )
            formalites.setUsers(userService.getUserById(formalitesDTO.getUserId()));
        // mettre a jour id quiz si pas null
        if (formalitesDTO.getQuizId() != null)
            formalites.setQuiz(quizService.findQuizById(formalitesDTO.getQuizId()));
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
