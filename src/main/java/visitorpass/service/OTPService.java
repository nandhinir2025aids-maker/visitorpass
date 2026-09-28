package visitorpass.service;

import org.springframework.stereotype.Service;

import visitorpass.entity.OTPPass;
import visitorpass.entity.VisitorPreApproval;
import visitorpass.repository.OTPPassRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OTPService {

    private final OTPPassRepository otpPassRepository;

    public OTPService(OTPPassRepository otpPassRepository) {
        this.otpPassRepository = otpPassRepository;
    }

    public OTPPass generateOTP(VisitorPreApproval approval) {

        SecureRandom random = new SecureRandom();

        int number = 100000 + random.nextInt(900000);

        String otpCode = String.valueOf(number);

        OTPPass otpPass = new OTPPass();

        otpPass.setOtpCode(otpCode);
        otpPass.setGeneratedAt(LocalDateTime.now());
        otpPass.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        otpPass.setUsed(false);
        otpPass.setApproval(approval);

        return otpPassRepository.save(otpPass);
    }

    public String verifyOTP(String otpCode) {

        Optional<OTPPass> result =
                otpPassRepository.findAll()
                        .stream()
                        .filter(otp -> otp.getOtpCode().equals(otpCode))
                        .findFirst();

        if (result.isEmpty()) {
            return "Invalid OTP";
        }

        OTPPass otp = result.get();

        if (otp.isUsed()) {
            return "OTP already used";
        }

        if (LocalDateTime.now().isAfter(otp.getExpiresAt())) {
            return "OTP expired";
        }

        if (otp.getApproval().getStatus().equals("REVOKED")) {
            return "Visitor approval has been revoked";
        }

        otp.setUsed(true);
        otp.setEntryTime(LocalDateTime.now());

        otpPassRepository.save(otp);

        return "Visitor entry approved";
    }
}