package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.CoachCourse;
import com.SwimcoachPlatform.coach.service.CoachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach-courses")
public class CoachCourseController {

    private final CoachCourseService coachCourseService;

    @Autowired
    public CoachCourseController(CoachCourseService coachCourseService) {
        this.coachCourseService = coachCourseService;
    }

    // GET: recupera tutte le relazioni coach-course
    @GetMapping
    public List<CoachCourse> findAllCoachCourses() {
        return coachCourseService.findAllCoachCourses();
    }

    // GET: recupera una relazione tramite ID
    @GetMapping("/{id}")
    public CoachCourse findCoachCourseById(@PathVariable Long id) {
        return coachCourseService.findCoachCorsesById(id);
    }

    // POST: crea una nuova relazione coach-course
    @PostMapping
    public CoachCourse addCoachCourse(@RequestBody CoachCourse coachCourse) {
        return coachCourseService.addCoachCourses(coachCourse);
    }
    // PUT: modifica un coach
    @PutMapping("/{id}")
    public CoachCourse updateCoachCourse(@PathVariable Long id, @RequestBody CoachCourse coachCourse)
    { coachCourse.setId(id); return coachCourseService.updateCoachCourse(coachCourse); }

    // DELETE: elimina una relazione
    @DeleteMapping("/{id}")
    public void deleteCoachCourse(@PathVariable Long id) {
        coachCourseService.deleteCoachCourses(id);
    }
}