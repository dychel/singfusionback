package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.dto.ReponsesDTO;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Reponses;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.QuestionsRepository;
import com.singfusion.singfusion.repository.ReponsesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReponsesServiceImpl implements ReponsesService {

    @Autowired
    ReponsesRepository reponsesRepository;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Reponses saveReponses(ReponsesDTO reponsesDTO) {
        Reponses reponses = modelMapper.map(reponsesDTO, Reponses.class);
        //Mettre à jour les infos du user
//        if (reponses.getQuestions() != null){
//            reponses.setQuestions(questionsRepository.findByIdQuestions(reponsesDTO.getQuestionId()));
//        }
        return reponsesRepository.save(reponses);
    }

    private void updateForeignKeyReponses(ReponsesDTO reponsesDTO, Reponses reponses) {
        // mettre a jour id users si pas null
//        if (reponsesDTO.getQuestionId()!= null )
//            reponses.setQuestions(questionsRepository.findByIdQuestions(reponsesDTO.getQuestionId()));
    }

    @Override
    public Reponses updateReponses(Long id, ReponsesDTO reponsesDTO) {
        Reponses reponsesToUpdate = reponsesRepository.findByIdReponses(id);
        if (reponsesToUpdate == null)
            throw new ApiRequestException("Reponses non trouvée");
        //enregister les nouvelle infos
        Reponses reponses = modelMapper.map(reponsesDTO, Reponses.class);
        reponses.setId(reponsesToUpdate.getId());
        // mettre a jour lid de la question
        updateForeignKeyReponses(reponsesDTO, reponses);
        return reponsesRepository.save(reponses);
    }

    @Override
    public Reponses findReponsesById(Long id) {
        return reponsesRepository.findByIdReponses(id);
    }

    @Override
    public List<Reponses> findReponsesByIdQuestions(Long id) {
        return null;
    }

    @Override
    public List<Reponses> listReponses() {
        return reponsesRepository.findAll();
    }

    @Override
    public void deleteReponsesById(Long id) {
        reponsesRepository.deleteById(id);
    }
}
