package visitorpass.service;

import org.springframework.stereotype.Service;

import visitorpass.entity.Flat;
import visitorpass.repository.FlatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlatService {

    private final FlatRepository flatRepository;

    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    public Flat createFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    public Optional<Flat> getFlatById(Long id) {
        return flatRepository.findById(id);
    }

    public void deleteFlat(Long id) {
        flatRepository.deleteById(id);
    }
}