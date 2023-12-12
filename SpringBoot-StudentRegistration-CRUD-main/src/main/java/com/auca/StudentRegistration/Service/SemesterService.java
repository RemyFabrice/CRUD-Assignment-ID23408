package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Repository.SemesterRepo;
import com.auca.StudentRegistration.Repository.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    private static final Logger logger = LoggerFactory.getLogger(SemesterService.class);

    @Autowired
    private SemesterRepo semRepo;
    public String saveSemester(Semester semester){
        if (semester != null) {
            if (isSemesterExists(semester.getId())) {
                return "Semester already exists";
            } else {
                semRepo.save(semester);
                return "semester created successfully";
            }
        } else {
            return null;
        }
    }
    public boolean isSemesterExists(String id) {
        return semRepo.existsById(id);
    }

    public List<Semester> listSemesters() {
        return semRepo.findAll();
    }

    public String updateSemester(String id, Semester semester) {
        logger.info("Updating semester with id: {}", id);
        try {
            if (semester != null) {
                if (isSemesterExists(id)) {
                    semRepo.save(semester);
                    logger.info("Semester updated successfully");
                    return "Semester updated successfully";
                } else {
                    return "Semester not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update Semester", ex);
            return "Semester not updated";
        }
    }

    public String deleteSemester(String id) {
        logger.info("Deleting Semester with id: {}", id);
        try {
            if (id != null) {
                if (isSemesterExists(id)) {
                    semRepo.deleteById(id);
                    logger.info("Semester deleted successfully");
                    return "Semester deleted successfully";
                } else {
                    return "Semester not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Semester", e);
            return "Semester not deleted";
        }
    }
    public Semester getSemesterById(String id) {
        logger.info("Semester with id: {}", id);
        return semRepo.findById(id).orElse(null);
    }
}
