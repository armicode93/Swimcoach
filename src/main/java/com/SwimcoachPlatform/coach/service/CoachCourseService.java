package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.entity.CoachCourse;
import com.SwimcoachPlatform.coach.repository.CoachCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachCourseService {

    private final CoachCourseRepository coachCourseRepository;

    @Autowired
    public CoachCourseService(CoachCourseRepository coachCourseRepository) {
        this.coachCourseRepository = coachCourseRepository;

    }

    public List<CoachCourse> findAll() {
        return coachCourseRepository.findAll();
    }

    public Optional<CoachCourse> findById(Long id) {
        return coachCourseRepository.findById(id);
    }

    public CoachCourse save(CoachCourse coachCourse) {
        return coachCourseRepository.save(coachCourse);
    }

    public void delete(Long id) {
        coachCourseRepository.deleteById(id);
    }
}



