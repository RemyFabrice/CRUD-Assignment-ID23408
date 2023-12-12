package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Model.Teacher;
import com.auca.StudentRegistration.Repository.StudentRepo;
import com.auca.StudentRegistration.Repository.TeacherRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    private TeacherRepo teacherRepo;
    public String saveTeacher(Teacher teacher){
        if (teacher != null) {
            if (isTeacherExists(teacher.getTr_code())) {
                return "Teacher already exists";
            } else {
                teacherRepo.save(teacher);
                return "Teacher created successfully";
            }
        } else {
            return null;
        }
    }
    public boolean isTeacherExists(String tr_code) {
        return teacherRepo.existsById(tr_code);
    }

    public List<Teacher> listTeacher() {
        return teacherRepo.findAll();
    }

    public String updateTeacher(String tr_code, Teacher teacher) {
        logger.info("Updating teacher with code: {}", tr_code);
        try {
            if (teacher != null) {
                if (isTeacherExists(tr_code)) {
                    teacherRepo.save(teacher);
                    logger.info("Teacher updated successfully");
                    return "Teacher updated successfully";
                } else {
                    return "Teacher not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update Teacher", ex);
            return "Teacher not updated successfully";
        }
    }

    public String deleteTeacher(String tr_code) {
        logger.info("Deleting Teacher with code: {}", tr_code);
        try {
            if (tr_code != null) {
                if (isTeacherExists(tr_code)) {
                    teacherRepo.deleteById(tr_code);
                    logger.info("Teacher deleted successfully");
                    return "Teacher deleted successfully";
                } else {
                    return "Teacher not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Teacher", e);
            return "Teacher not deleted";
        }
    }
}
