package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepo extends JpaRepository<StudentRegistration,Integer> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    boolean existsByStudentId(String studentId);
    StudentRegistration findByStudentId(String studentId);

    List<StudentRegistration> findBySemester(Semester semester);
    List<StudentRegistration> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
    List<StudentRegistration> findBySemesterId(String semesterId);

}
