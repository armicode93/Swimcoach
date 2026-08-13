package com.SwimcoachPlatform.coach.repository;

import com.SwimcoachPlatform.coach.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
