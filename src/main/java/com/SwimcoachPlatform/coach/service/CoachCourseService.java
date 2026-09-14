package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.CoachCourseDTO;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.CoachCourse;
import com.SwimcoachPlatform.coach.entity.Course;
import com.SwimcoachPlatform.coach.repository.CoachCourseRepository;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import com.SwimcoachPlatform.coach.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachCourseService {

    private final CoachCourseRepository coachCourseRepository;
    private final CoachRepository coachRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public CoachCourseService(CoachCourseRepository coachCourseRepository, CoachRepository coachRepository, CourseRepository courseRepository) {
        this.coachCourseRepository = coachCourseRepository;

        this.coachRepository = coachRepository;
        this.courseRepository = courseRepository;
    }

    public List<CoachCourse> findAllCoachCourses() {
        return coachCourseRepository.findAll();
    }

    public CoachCourse findCoachCorsesById(Long id) {
        return coachCourseRepository.findById(id).orElse(null);
    }

    public CoachCourse addCoachCourse(CoachCourseDTO coachCourseDTO) {

        Coach coach = coachRepository.findById(coachCourseDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(coachCourseDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CoachCourse coachCourse = new CoachCourse();

        coachCourse.setCoach(coach);
        coachCourse.setCourse(course);
        coachCourse.setPrice(coachCourseDTO.getPrice());
        coachCourse.setDuration(coachCourseDTO.getDuration());

        return coachCourseRepository.save(coachCourse);
    }
    public CoachCourse updateCoachCourse(Long id, CoachCourseDTO coachCourseDTO) {

        CoachCourse existingCoachCourse = coachCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoachCourse not found"));

        Coach coach = coachRepository.findById(coachCourseDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(coachCourseDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCoachCourse.setCoach(coach);
        existingCoachCourse.setCourse(course);
        existingCoachCourse.setPrice(coachCourseDTO.getPrice());
        existingCoachCourse.setDuration(coachCourseDTO.getDuration());

        return coachCourseRepository.save(existingCoachCourse);
    }


    public void deleteCoachCourses(Long id) {
        coachCourseRepository.deleteById(id);
    }
}



