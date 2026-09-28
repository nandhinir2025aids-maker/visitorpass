package visitorpass.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import visitorpass.entity.Flat;
import visitorpass.service.FlatService;

import java.util.List;

@RestController
@RequestMapping("/api/flats")
public class FlatController {

    private final FlatService flatService;

    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    @PostMapping
    public Flat createFlat(@Valid @RequestBody Flat flat) {
        return flatService.createFlat(flat);
    }

    @GetMapping
    public List<Flat> getAllFlats() {
        return flatService.getAllFlats();
    }

    @GetMapping("/{id}")
    public Flat getFlatById(@PathVariable Long id) {

        return flatService.getFlatById(id)
                .orElseThrow(() ->
                        new RuntimeException("Flat not found"));
    }

    @DeleteMapping("/{id}")
    public String deleteFlat(@PathVariable Long id) {

        flatService.deleteFlat(id);

        return "Flat deleted successfully";
    }
}