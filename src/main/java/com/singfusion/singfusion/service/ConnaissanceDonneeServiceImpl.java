package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ConnaissanceDonneeDTO;
import com.singfusion.singfusion.entity.ConnaissanceDonnee;
import com.singfusion.singfusion.repository.ProjetRepository;
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
   // Connais projetRepository;

    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public ConnaissanceDonnee saveConnaissanceDonnee(ConnaissanceDonneeDTO connaissanceDonneeDTO) {
        return null;
    }

    @Override
    public ConnaissanceDonnee updateConnaissanceDonnee(Long id, ConnaissanceDonneeDTO connaissanceDonneeDTO) {
        return null;
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeById(Long id) {
        return null;
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeByIdUsers(Long id) {
        return null;
    }

    @Override
    public ConnaissanceDonnee findConnaissanceDonneeByIdDocument(Long id) {
        return null;
    }

    @Override
    public List<ConnaissanceDonnee> listConnaissanceDonnee() {
        return null;
    }

    @Override
    public void deleteConnaissanceDonneeById(Long id) {

    }
}
