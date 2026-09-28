package visitorpass.service;

import org.springframework.stereotype.Service;

import visitorpass.entity.Resident;
import visitorpass.repository.ResidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Resident createResident(Resident resident) {
        return residentRepository.save(resident);
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public Optional<Resident> getResidentById(Long id) {
        return residentRepository.findById(id);
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
    }
}