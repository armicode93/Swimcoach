package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Availability;
import com.SwimcoachPlatform.coach.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    // GET: recupera tutte le disponibilità
    @GetMapping
    public List<Availability> findAllAvailabilities() {
        return availabilityService.getAllAvailability();
    }

    // GET: recupera una disponibilità tramite ID
    @GetMapping("/{id}")
    public Availability findAvailabilityById(@PathVariable Long id) {
        return availabilityService.getAvailabilityById(id);
    }

    // POST: crea una nuova disponibilità
    @PostMapping
    public Availability addAvailability(@RequestBody Availability availability) {
        return availabilityService.addAvailability(availability);
    }

    // PUT: modifica una disponibilità
    @PutMapping("/{id}")
    public Availability updateAvailability(@PathVariable Long id,
                                           @RequestBody Availability availability) {

        return availabilityService.updateAvailability(id, availability);
    }

    // DELETE: elimina una disponibilità
    @DeleteMapping("/{id}")
    public void deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
    }
}