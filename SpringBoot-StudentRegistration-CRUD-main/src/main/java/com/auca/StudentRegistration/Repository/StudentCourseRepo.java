package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Integer> {
    boolean existsByCourse(Course course);
    List<StudentCourse> findByStudentRegistration(StudentRegistration studentRegistration);
    List<StudentCourse> findByCourseAndStudentRegistration(Course course, StudentRegistration studentRegistration);
}
