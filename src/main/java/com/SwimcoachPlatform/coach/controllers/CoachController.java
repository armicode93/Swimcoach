package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService)
    { this.coachService = coachService; }

    // GET: recupera tutti i coach
    @GetMapping
    public List<Coach> findAllCoaches() { return coachService.findAllCoaches(); }

    // GET: recupera un coach tramite ID
     @GetMapping("/{id}")
     public Coach findCoachById(@PathVariable Long id)
     { return coachService.findCoachById(id); }

    // POST: crea un nuovo coach
     @PostMapping
     public Coach addCoach(@RequestBody Coach coach)
     { return coachService.addCoach(coach); }

    // PUT: modifica un coach
    @PutMapping("/{id}")
    public Coach updateCoach(@PathVariable Long id, @RequestBody Coach coach)
    { coach.setId(id); return coachService.UpdateCoach(coach); }

    // DELETE: elimina un coach
    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable Long id)
    { coachService.deleteCoach(id); }


}
