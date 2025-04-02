package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.PresentationGeneraleDTO;
import com.singfusion.singfusion.entity.Contenus;
import com.singfusion.singfusion.entity.PresentationGenerale;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.PresentationGeneraleRepository;
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
    ModelMapper modelMapper;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public PresentationGenerale savePresentationGenerale(PresentationGeneraleDTO presentationGeneraleDTO) {
        PresentationGenerale presentationGenerale = modelMapper.map(presentationGeneraleDTO, PresentationGenerale.class);
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
        presentationGenerale.setDateAjout(currentdate);
        // MAJ id users
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
