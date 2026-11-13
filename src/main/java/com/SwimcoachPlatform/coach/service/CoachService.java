package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.CoachDTO;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SwimcoachPlatform.coach.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final UserRepository userRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository, UserRepository userRepository) {
        this.coachRepository = coachRepository;
        this.userRepository = userRepository;
    }

    public List<Coach> findAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach addCoach(CoachDTO coachDTO) {

        Coach  coach = new Coach();
        coach.setFirstName(coachDTO.getFirstName());
        coach.setLastname(coachDTO.getLastname());
        return coachRepository.save(coach);
    }
    public Coach findCoachByEmail(String email) {

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return findCoachByUserId(user.getId());
    }


    public Coach updateCoach(Coach coach) {return coachRepository.save(coach);}

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }

    public Coach findCoachByUserId(Long userId) {
        return coachRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Coach profile not found."));
    }
}
