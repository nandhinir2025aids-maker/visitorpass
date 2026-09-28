package visitorpass.controller;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import visitorpass.entity.VisitorPreApproval;
import visitorpass.service.VisitorPreApprovalService;
import java.util.List;

@RestController
@RequestMapping("/api/approvals")
public class VisitorPreApprovalController {

    private final VisitorPreApprovalService approvalService;

    public VisitorPreApprovalController(
            VisitorPreApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @PostMapping
    public VisitorPreApproval createApproval(
            @Valid @RequestBody VisitorPreApproval approval) {

        return approvalService.createApproval(approval);
    }

    @GetMapping
    public List<VisitorPreApproval> getAllApprovals() {
        return approvalService.getAllApprovals();
    }

    @GetMapping("/{id}")
    public VisitorPreApproval getApprovalById(
            @PathVariable Long id) {

        return approvalService.getApprovalById(id)
                .orElseThrow(() ->
                        new RuntimeException("Approval not found"));
    }

    @PutMapping("/{id}/revoke")
    public String revokeApproval(@PathVariable Long id) {

        approvalService.revokeApproval(id);

        return "Visitor approval revoked successfully";
    }
}

