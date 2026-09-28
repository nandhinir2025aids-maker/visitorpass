package visitorpass.controller;

import org.springframework.web.bind.annotation.*;

import visitorpass.entity.OTPPass;
import visitorpass.entity.VisitorPreApproval;
import visitorpass.service.OTPService;
import visitorpass.service.VisitorPreApprovalService;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    private final OTPService otpService;
    private final VisitorPreApprovalService approvalService;

    public OTPController(
            OTPService otpService,
            VisitorPreApprovalService approvalService) {

        this.otpService = otpService;
        this.approvalService = approvalService;
    }

    @PostMapping("/generate/{approvalId}")
    public OTPPass generateOTP(
            @PathVariable Long approvalId) {

        VisitorPreApproval approval =
                approvalService.getApprovalById(approvalId)
                        .orElseThrow(() ->
                                new RuntimeException("Approval not found"));

        return otpService.generateOTP(approval);
    }

    @PostMapping("/verify")
    public String verifyOTP(
            @RequestParam String otpCode) {

        return otpService.verifyOTP(otpCode);
    }
}