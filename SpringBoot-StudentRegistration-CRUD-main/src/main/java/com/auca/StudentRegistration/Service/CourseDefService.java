package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.CourseDefinition;
import com.auca.StudentRegistration.Repository.CourseDefRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseDefService {
    private static final Logger logger = LoggerFactory.getLogger(CourseDefService.class);

    @Autowired
    private CourseDefRepo defRepo;
    public String saveDef(CourseDefinition courseDefinition){
        if (courseDefinition != null) {
            if (isDefExists(courseDefinition.getCode())) {
                return "Course already exists";
            } else {
                defRepo.save(courseDefinition);
                return "Course created successfully";
            }
        } else {
            return null;
        }
    }
    public boolean isDefExists(String code) {
        return defRepo.existsById(code);
    }

    public List<CourseDefinition> listDefs() {
        return defRepo.findAll();
    }

    public String updateDef(String code, CourseDefinition courseDefinition) {
        logger.info("Updating Course with code: {}", code);
        try {
            if (courseDefinition != null) {
                if (isDefExists(code)) {
                    defRepo.save(courseDefinition);
                    logger.info("Course updated successfully");
                    return "Course updated successfully";
                } else {
                    return "Course not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update Course", ex);
            return "Course not updated";
        }
    }

    public String deleteDef(String code) {
        logger.info("Deleting course with code: {}", code);
        try {
            if (code != null) {
                if (isDefExists(code)) {
                    defRepo.deleteById(code);
                    logger.info("Course deleted successfully");
                    return "Course deleted successfully";
                } else {
                    return "Course not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete course", e);
            return "Course not deleted";
        }
    }
}
