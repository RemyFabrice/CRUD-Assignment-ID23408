package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Model.StudentRegistration;
import com.auca.StudentRegistration.Repository.StudentRegistrationRepo;
import com.auca.StudentRegistration.Repository.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepo studRepo;
    @Autowired
    private StudentRegistrationRepo regRepo;

    public String saveStudent(Student student){
        if (student != null) {
            // Check if a student with the same registration number already exists
            if (isStudentExists(student.getRegistrationNumber())) {
                return "Student with the same registration number already exists";
            } else {
                studRepo.save(student);
                return "Student created successfully";
            }
        } else {
            return null;
        }
    }
    public boolean isStudentExists(String registrationNumber) {
        return studRepo.existsById(registrationNumber);
    }

    public List<Student> listStudents() {
        return studRepo.findAll();
    }

    public String updateStudent(String registrationNumber, Student student) {
        logger.info("Updating student with registration number: {}", registrationNumber);
        try {
            if (student != null) {
                if (isStudentExists(registrationNumber)) {
                    // Update the student information here
                    studRepo.save(student);
                    logger.info("Student updated successfully");
                    return "Student updated successfully";
                } else {
                    return "Student not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update student", ex);
            return "Student not updated successfully";
        }
    }

    public String deleteStudent(String registrationNumber) {
        logger.info("Deleting student with registration number: {}", registrationNumber);
        try {
            if (registrationNumber != null) {
                if (isStudentExists(registrationNumber)) {
                    studRepo.deleteById(registrationNumber);
                    logger.info("Student deleted successfully");
                    return "Student deleted successfully";
                } else {
                    return "Student not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete student", e);
            return "Student not deleted successfully";
        }
    }
    public List<StudentRegistration> getStudentsBySemester(Semester semester) {
        logger.info("Student in Semester: {}", semester);
        return regRepo.findBySemester(semester);
    }
    public List<StudentRegistration> getStudentsByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return regRepo.findByDepartmentAndSemester(department, semester);
    }
}
