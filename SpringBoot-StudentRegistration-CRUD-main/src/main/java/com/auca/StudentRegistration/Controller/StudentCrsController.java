package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.*;
import com.auca.StudentRegistration.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/StudCourse" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class StudentCrsController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudCourseService studCrsService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private StudRegistrationService regService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDefService defService;
    //creating
    @PostMapping(value = "/saveStudCourse")
    public ResponseEntity<?> createStudCrs(@RequestBody StudentCourse studCrs){
        if(studCrs != null ){
            String message = studCrsService.saveStudCourse(studCrs);
            if(message != null){
                return new ResponseEntity<>("Student Course Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Student Course Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listStudCourse")
    public ResponseEntity<List<StudentCourse>> listStudCrs() {
        List<StudentCourse> studCrs = studCrsService.listStudentsCourse();
        return new ResponseEntity<>(studCrs, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateStudCourse/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            String message = studCrsService.updateStudCourse(id, studCrs);
            if (message != null) {
                return new ResponseEntity<>("Student Course Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Course Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    /*@PutMapping(value = "/updateStudCourse/{studentId}/{course}")
    public ResponseEntity<String> updateStudent(@PathVariable String studentId, @PathVariable Course course, @RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            String message = studCrsService.updateStudCourse(studentId,course, studCrs);
            if (message != null) {
                return new ResponseEntity<>("Student Course Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Course Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }*/
    //delete
    @DeleteMapping(value = "/deleteStudCourse/{id}")
    public ResponseEntity<String> deleteStudCrs(@PathVariable Integer id) {
        if (id != null) {
            String message = studCrsService.deleteStudCourse(id);
            if (message != null) {
                return new ResponseEntity<>("Student Course Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Course Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listByCourse/{studentId}")
    public ResponseEntity<List<StudentCourse>> listByCourse(@PathVariable String studentId){
        StudentRegistration stud = regService.getRegistrationByStudentId(studentId);

        if (stud != null) {
            List<StudentCourse> crs = studCrsService.getCoursesByStudentId(studentId);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listByCourseAndSemester/{courseCode}/{semesterId}")
    public ResponseEntity<List<StudentCourse>> listByCourseAndSemester(@PathVariable Integer courseCode, @PathVariable String semesterId) {
        Course course = courseService.getCourseById(courseCode);

        if (course != null) {
            List<StudentCourse> crs = studCrsService.getStudentByCourseAndSemester(course, semesterId);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
