package com.singfusion.singfusion.service;

import com.singfusion.singfusion.dto.VerificationEmailDTO;
import com.singfusion.singfusion.entity.VerificationEmail;

public interface VerificationEmailService {

    VerificationEmail getOtpByUsers(Long id);

    VerificationEmail CheckOtpByUsers(VerificationEmailDTO verificationEmailDTO);

    VerificationEmail saveOtp(VerificationEmailDTO verificationEmailDTO);

    VerificationEmail updateOtp(VerificationEmailDTO verificationEmailDTO);

}
