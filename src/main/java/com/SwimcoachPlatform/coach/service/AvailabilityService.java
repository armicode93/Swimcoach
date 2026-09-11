package com.SwimcoachPlatform.coach.service;

import com.SwimcoachPlatform.coach.entity.Availability;
import com.SwimcoachPlatform.coach.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    private AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }
    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id).orElse(null);
    }
    public Availability addAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
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
