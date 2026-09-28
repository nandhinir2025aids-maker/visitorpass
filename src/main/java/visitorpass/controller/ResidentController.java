package visitorpass.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import visitorpass.entity.Resident;
import visitorpass.service.ResidentService;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public Resident createResident(
            @Valid @RequestBody Resident resident) {

        return residentService.createResident(resident);
    }

    @GetMapping
    public List<Resident> getAllResidents() {
        return residentService.getAllResidents();
    }

    @GetMapping("/{id}")
    public Resident getResidentById(@PathVariable Long id) {

        return residentService.getResidentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Resident not found"));
    }

    @DeleteMapping("/{id}")
    public String deleteResident(@PathVariable Long id) {

        residentService.deleteResident(id);

        return "Resident deleted successfully";
    }
}