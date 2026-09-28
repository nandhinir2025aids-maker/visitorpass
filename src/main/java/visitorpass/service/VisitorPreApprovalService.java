package visitorpass.service;

import org.springframework.stereotype.Service;

import visitorpass.entity.VisitorPreApproval;
import visitorpass.repository.VisitorPreApprovalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorPreApprovalService {

    private final VisitorPreApprovalRepository approvalRepository;

    public VisitorPreApprovalService(
            VisitorPreApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
    }

    public VisitorPreApproval createApproval(
            VisitorPreApproval approval) {

        approval.setStatus("APPROVED");

        return approvalRepository.save(approval);
    }

    public List<VisitorPreApproval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    public Optional<VisitorPreApproval> getApprovalById(Long id) {
        return approvalRepository.findById(id);
    }

    public void revokeApproval(Long id) {

        Optional<VisitorPreApproval> approval =
                approvalRepository.findById(id);

        if (approval.isPresent()) {
            VisitorPreApproval existingApproval =
                    approval.get();

            existingApproval.setStatus("REVOKED");

            approvalRepository.save(existingApproval);
        }
    }
}