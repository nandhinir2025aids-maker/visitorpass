package visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import visitorpass.entity.Flat;

public interface FlatRepository extends JpaRepository<Flat, Long> {

}