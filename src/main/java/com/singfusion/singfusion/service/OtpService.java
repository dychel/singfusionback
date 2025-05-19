package com.singfusion.singfusion.service;

import com.singfusion.singfusion.dto.OtpDTO;
import com.singfusion.singfusion.entity.OtpUsers;

public interface OtpService {

    OtpUsers getOtpByUsers(Long id);

    OtpUsers CheckOtpByUsers(OtpDTO otpDTO);

    OtpUsers saveOtp(OtpDTO otpDTO);

    OtpUsers updateOtp(OtpDTO otpDTO);

}
