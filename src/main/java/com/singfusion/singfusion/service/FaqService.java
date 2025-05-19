package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FaqDTO;
import com.singfusion.singfusion.entity.Faq;
import java.util.List;

public interface FaqService {

    Faq saveFaq(FaqDTO faqDTO);
    Faq updateFaq(Long id, FaqDTO faqDTO);
    Faq findFaqById(Long id);
    Faq findFaqByIdUser(Long id);
    List<Faq> listFaqs();
    List<Faq> findFaqBytacorgie(String categorie);
    void deleteFaqById(Long id);
}
