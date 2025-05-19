package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FaqDTO;
import com.singfusion.singfusion.entity.Faq;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.FaqRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentService;
    @Autowired
    FaqRepository faqRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public Faq saveFaq(FaqDTO faqDTO) {
        currentdate = new Date(currentTimeInMillis);
        Faq faq = modelMapper.map(faqDTO, Faq.class);
        faq.setDate(currentdate);
        updateForeignKeyUsers(faqDTO, faq);
        return faqRepository.save(faq);
    }

    @Override
    public Faq updateFaq(Long id, FaqDTO faqDTO) {
        Faq faqToUpdate = faqRepository.findByIdFaq(id);
        currentdate = new Date(currentTimeInMillis);
        if (faqToUpdate == null)
            throw new ApiRequestException("Faq ID non trouvé");
        Faq faq = modelMapper.map(faqDTO, Faq.class);
        faq.setId(faqToUpdate.getId());
        // MAJ id users
        updateForeignKeyUsers(faqDTO, faq);
        return faqRepository.save(faq);
    }

    private void updateForeignKeyUsers(FaqDTO faqDTO, Faq faq) {
        // mettre a jour id users si pas null
        if (faqDTO.getUserId() != null)
            faq.setUsers(userService.getUserById(faqDTO.getUserId()));
    }

    @Override
    public Faq findFaqById(Long id) {
        return faqRepository.findByIdFaq(id);
    }

    @Override
    public Faq findFaqByIdUser(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return faqRepository.findByIdFaq(id);
    }

    @Override
    public List<Faq> listFaqs() {
        return faqRepository.findAll();
    }

    @Override
    public List<Faq> findFaqBytacorgie(String categorie) {
        return faqRepository.findFaqByCategorie(categorie);
    }

    @Override
    public void deleteFaqById(Long id) {
        Faq faq = faqRepository.findByIdFaq(id);
        if (faq==null)
            throw new ApiRequestException("ID Faq non trouve");
        faqRepository.deleteById(id);
    }
}
