package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Course;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.StudentRegistration;
import com.auca.StudentRegistration.Repository.StudentRegistrationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(StudRegistrationService.class);

    @Autowired
    private StudentRegistrationRepo regRepo;
    public String saveRegistration(StudentRegistration studRegistration) {
        if (studRegistration != null) {
            if (regRepo.existsByStudentId(studRegistration.getStudentId())) {
                return "Student with ID " + studRegistration.getStudentId() + " is already registered.";
            } else {
                // Save the registration if not already present
                regRepo.save(studRegistration);
                return "Student Registration created successfully";
            }
        } else {
            return null;
        }
    }
    public boolean isRegistrationExists(AcademicUnit department, Semester semester) {
        return regRepo.existsByDepartmentAndSemester(department, semester);
    }
    public boolean isStudentRegExists(Integer id) {
        return regRepo.existsById(id);
    }
    public List<StudentRegistration> listStudentsReg() {
        return regRepo.findAll();
    }

    public String updateStudentReg(Integer id, StudentRegistration studReg) {
        logger.info("Updating student registration with id: {}", id);
        try {
            if (studReg != null) {
                if (isStudentRegExists(id)) {
                    regRepo.save(studReg);
                    logger.info("Student Registration updated successfully");
                    return "Student Registration updated successfully";
                } else {
                    return "Student Registration not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update student Registration", ex);
            return "Student Registration not updated";
        }
    }

    public String deleteStudentReg(Integer id) {
        logger.info("Deleting student with id: {}", id);
        try {
            if (id != null) {
                if (isStudentRegExists(id)) {
                    regRepo.deleteById(id);
                    logger.info("Student Registration deleted successfully");
                    return "Student Registration deleted successfully";
                } else {
                    return "Student Registration not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete student Registration", e);
            return "Student Registration not deleted successfully";
        }
    }
    public List<StudentRegistration> getRegistrationsByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return regRepo.findByDepartmentAndSemester(department, semester);
    }
    public List<StudentRegistration> getRegistrationsBySemester(Semester semester) {
        return regRepo.findBySemester(semester);
    }
    public StudentRegistration getRegistrationBySemesterId(String semesterId) {
        return regRepo.findByStudentId(semesterId);
    }
    public StudentRegistration getRegistrationByStudentId(String studentId) {
        return regRepo.findByStudentId(studentId);
    }
    public StudentRegistration getRegistrationById(Integer id) {
        return regRepo.findById(id).orElse(null);
    }
}
