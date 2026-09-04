package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Course;
import com.SwimcoachPlatform.coach.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET: recupera tutti i corsi
    @GetMapping
    public List<Course> findAllCourses() {
        return courseService.getAllCourses();
    }

    // GET: recupera un corso tramite ID
    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // POST: crea un nuovo corso
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // PUT: modifica un corso
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        return courseService.updateCourse(course);
    }

    // DELETE: elimina un corso
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}