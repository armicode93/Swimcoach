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

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public void delete(Long id) {
        coachRepository.deleteById(id);
    }
}
