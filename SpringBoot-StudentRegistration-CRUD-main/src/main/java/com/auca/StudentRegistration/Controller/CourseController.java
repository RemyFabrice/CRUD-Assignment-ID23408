package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.*;
import com.auca.StudentRegistration.Service.CourseService;
import com.auca.StudentRegistration.Service.SemesterService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/course" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class CourseController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SemesterService semesterService;
    //creating
    @PostMapping(value = "/saveCourse")
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        if(course != null ){
            String message = courseService.saveCourse(course);
            if(message != null){
                return new ResponseEntity<>("Course Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Course Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listCourse")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.listCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    //update
    /*@PutMapping(value = "/updateCourse/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody Course course) {
        if (course != null) {
            String message = courseService.updateCourse(id, course);
            if (message != null) {
                return new ResponseEntity<>("Course Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }*/
    @PutMapping(value = "/updateCourse/{code}")
    public ResponseEntity<String> updateStudent(@PathVariable CourseDefinition code, @RequestBody Course course) {
        if (course != null) {
            String message = courseService.updateCourse(code, course);
            if (message != null) {
                return new ResponseEntity<>("Course Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        if (id != null) {
            String message = courseService.deleteCourse(id);
            if (message != null) {
                return new ResponseEntity<>("Course Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<Course>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<Course> crs = courseService.getCoursesByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
