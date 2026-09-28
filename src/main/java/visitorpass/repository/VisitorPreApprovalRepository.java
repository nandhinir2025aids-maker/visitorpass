package visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import visitorpass.entity.VisitorPreApproval;

public interface VisitorPreApprovalRepository
        extends JpaRepository<VisitorPreApproval, Long> {

}