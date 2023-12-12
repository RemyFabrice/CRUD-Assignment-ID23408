package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Model.Teacher;
import com.auca.StudentRegistration.Service.StudentService;
import com.auca.StudentRegistration.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/teacher" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class TeacherController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private TeacherService teacherService;
    //creating
    @PostMapping(value = "/saveTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        if(teacher != null ){
            String message = teacherService.saveTeacher(teacher);
            if(message != null){
                return new ResponseEntity<>("Teacher Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Teacher Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listTeachers")
    public ResponseEntity<List<Teacher>> listTeachers() {
        List<Teacher> teachers = teacherService.listTeacher();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateTeacher/{tr_code}")
    public ResponseEntity<String> updateTeacher(@PathVariable String tr_code, @RequestBody Teacher teacher) {
        if (teacher != null) {
            String message = teacherService.updateTeacher(tr_code, teacher);
            if (message != null) {
                return new ResponseEntity<>("Teacher Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Teacher Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteTeacher/{tr_code}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String tr_code) {
        if (tr_code != null) {
            String message = teacherService.deleteTeacher(tr_code);
            if (message != null) {
                return new ResponseEntity<>("Teacher Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Teacher Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
