package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.ProjetDTO;
import com.singfusion.singfusion.entity.Document;
import com.singfusion.singfusion.entity.Projet;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.ProjetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ProjetServiceImpl implements ProjetService{

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentService;
    @Autowired
    ProjetRepository projetRepository;

    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();

    @Override
    public Projet saveProjet(ProjetDTO projetDTO) {
        Projet projet = modelMapper.map(projetDTO, Projet.class);
        return projetRepository.save(projet);
    }

    @Override
    public Projet updateProjet(Long id, ProjetDTO projetDTO) {
        Projet projetToUpdate = projetRepository.findByIdProjet(id);
        currentdate = new Date(currentTimeInMillis);
        if (projetToUpdate == null)
            throw new ApiRequestException("Projet ID non trouvé");
        Projet projet = modelMapper.map(projetDTO, Projet.class);
        projet.setId(projetToUpdate.getId());
        //projet.setDatedebut(currentdate);
        // MAJ id users
        updateForeignKeyUsersDocument(projetDTO, projet);
        return projetRepository.save(projet);
    }

    private void updateForeignKeyUsersDocument(ProjetDTO projetDTO, Projet projet) {
        // mettre a jour id Document si pas null
        if (projetDTO.getDocumentId() != null )
            projet.setDocument(documentService.findDocumentById(projetDTO.getDocumentId()));
        // mettre a jour id users si pas null
        if (projetDTO.getUserId() != null)
            projet.setUsers(userService.getUserById(projetDTO.getUserId()));
    }
    @Override
    public Projet findProjetById(Long id) {
        return projetRepository.findByIdProjet(id);
    }

    @Override
    public Projet findProjetByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return projetRepository.findProjetByIdUsers(id);
    }

    @Override
    public Projet findProjetByIdDocument(Long id) {
        Document document = documentService.findDocumentById(id);
        if (document == null)
            throw new ApiRequestException("Document non trouvé");
        return projetRepository.findProjetByIdDocument(id);
    }

    @Override
    public List<Projet> listProjet() {
        return projetRepository.findAll();
    }

    @Override
    public void deleteProjetById(Long id) {
        Projet projet = projetRepository.findByIdProjet(id);
        if (projet==null)
            throw new ApiRequestException("ID Projet non trouve");
        projetRepository.deleteById(id);
    }
}
