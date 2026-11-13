
package com.SwimcoachPlatform.coach.service;

import com.SwimcoachPlatform.coach.dto.AvailabilityDTO;
import com.SwimcoachPlatform.coach.entity.Availability;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.Course;
import com.SwimcoachPlatform.coach.repository.AvailabilityRepository;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import com.SwimcoachPlatform.coach.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final CoachRepository coachRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AvailabilityService(
            AvailabilityRepository availabilityRepository,
            CoachRepository coachRepository,
            CourseRepository courseRepository
    ) {
        this.availabilityRepository = availabilityRepository;
        this.coachRepository = coachRepository;
        this.courseRepository = courseRepository;
    }

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id).orElse(null);
    }

    public Availability addAvailability(AvailabilityDTO dto) {

        Coach coach = coachRepository.findById(dto.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Availability availability = new Availability();

        availability.setCoach(coach);
        availability.setCourse(course);
        availability.setDayOfWeek(dto.getDayOfWeek());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        availability.setActive(dto.isActive());

        return availabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    public List<Availability> getAvailabilityByCoachAndCourse(
            Long coachId,
            Long courseId
    ) {
        return availabilityRepository
                .findByCoachIdAndCourseIdAndActiveTrue(coachId, courseId);
    }
    public Availability updateAvailability(Long id, Availability updatedAvailability) {

        Availability existingAvailability = availabilityRepository
                .findById(id)
                .orElse(null);

        if (existingAvailability == null) {
            return null;
        }

        existingAvailability.setDayOfWeek(updatedAvailability.getDayOfWeek());
        existingAvailability.setStartTime(updatedAvailability.getStartTime());
        existingAvailability.setEndTime(updatedAvailability.getEndTime());
        existingAvailability.setActive(updatedAvailability.isActive());
        existingAvailability.setCoach(updatedAvailability.getCoach());
        existingAvailability.setCourse(updatedAvailability.getCourse());

        return availabilityRepository.save(existingAvailability);
    }
}
