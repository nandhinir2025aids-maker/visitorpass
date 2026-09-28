package visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import visitorpass.entity.OTPPass;

public interface OTPPassRepository extends JpaRepository<OTPPass, Long> {

}