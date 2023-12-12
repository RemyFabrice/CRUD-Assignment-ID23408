package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Model.StudentRegistration;
import com.auca.StudentRegistration.Service.SemesterService;
import com.auca.StudentRegistration.Service.StudentService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.servlet.function.EntityResponse;

import java.awt.*;

@RestController
@RequestMapping(value = "/student" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class StudentController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private UnitService academicUnitService;
    //creating
    @PostMapping(value = "/saveStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        if(student != null ){
            String message = studentService.saveStudent(student);
            if(message != null){
                return new ResponseEntity<>("Student Saved Successfully",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Student Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listStudents")
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentService.listStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateStudent/{registrationNumber}")
    public ResponseEntity<String> updateStudent(@PathVariable String registrationNumber, @RequestBody Student student) {
        if (student != null) {
            String message = studentService.updateStudent(registrationNumber, student);
            if (message != null) {
                return new ResponseEntity<>("Student Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteStudent/{registrationNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable String registrationNumber) {
        if (registrationNumber != null) {
            String message = studentService.deleteStudent(registrationNumber);
            if (message != null) {
                return new ResponseEntity<>("Student Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    // List students by semester
    @GetMapping(value = "/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsBySemester(@PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> students = studentService.getStudentsBySemester(semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> students = studentService.getStudentsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
