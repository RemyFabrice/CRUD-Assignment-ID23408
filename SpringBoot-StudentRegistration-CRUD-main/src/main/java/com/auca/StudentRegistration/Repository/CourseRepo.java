package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Course;
import com.auca.StudentRegistration.Model.CourseDefinition;
import com.auca.StudentRegistration.Model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    public boolean existsByCourseDefinition(CourseDefinition courseDefinition);
    List<Course> findByDepartmentAndSemester(AcademicUnit department, Semester semester);

}
