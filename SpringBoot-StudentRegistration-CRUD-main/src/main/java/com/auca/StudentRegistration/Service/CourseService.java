package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Course;
import com.auca.StudentRegistration.Model.CourseDefinition;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Repository.CourseDefRepo;
import com.auca.StudentRegistration.Repository.CourseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepo crsRepo;
    @Autowired
    private CourseDefRepo crsDefRepo;
    public String saveCourse(Course course) {
        if (course != null) {
            if (isCourseExists(course.getDepartment(), course.getSemester())) {
                return "Course already exists in the department and semester.";
            } else {
                crsRepo.save(course);
                return "Course created successfully.";
            }
        } else {
            return null;
        }
    }

    public boolean isCourseExists(AcademicUnit department, Semester semester) {
        return crsRepo.existsByDepartmentAndSemester(department, semester);
    }
    public boolean isCourseIdExists(Integer id) {
        return crsRepo.existsById(id);
    }
    public List<Course> listCourses() {
        return crsRepo.findAll();
    }
    public Course getCourseById(Integer id) {
        logger.info("Course with id: {}", id);
        return crsRepo.findById(id).orElse(null);
    }

    public String updateCourse(CourseDefinition crsDef, Course course) {
        logger.info("Updating Course with Code: {}", crsDef);
        try {
            if (course != null) {
                if (isCourseDefinitionExists(crsDef)) {
                    crsRepo.save(course);
                    logger.info("Course updated successfully");
                    return "Course updated successfully";
                } else {
                    return "Course not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to Course student", ex);
            return "Course not updated";
        }
    }
    /*public String updateCourse(Integer id, Course course) {
        logger.info("Updating Course with Id: {}", id);
        try {
            if (course != null) {
                if (isCourseIdExists(id)) {
                    crsRepo.save(course);
                    logger.info("Course updated successfully");
                    return "Course updated successfully";
                } else {
                    return "Course not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to Course student", ex);
            return "Course not updated";
        }
    }*/

    public boolean isCourseDefinitionExists(CourseDefinition courseDefinition) {
        return crsRepo.existsByCourseDefinition(courseDefinition);
    }

    public String deleteCourse(Integer id) {
        logger.info("Deleting Course with id: {}", id);
        try {
            if (id != null) {
                if (isCourseIdExists(id)) {
                    crsRepo.deleteById(id);
                    logger.info("Course deleted successfully");
                    return "Course deleted successfully";
                } else {
                    return "Course not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Course", e);
            return "Course was not deleted successfully";
        }
    }
    public List<Course> getCoursesByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return crsRepo.findByDepartmentAndSemester(department, semester);
    }
}
