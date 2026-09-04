package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SwimcoachPlatform.coach.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> findAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach addCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach updateCoach(Coach coach) {return coachRepository.save(coach);}

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}
