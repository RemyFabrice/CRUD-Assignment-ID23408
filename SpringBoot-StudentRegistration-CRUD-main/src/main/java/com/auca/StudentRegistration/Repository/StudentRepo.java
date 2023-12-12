package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
}
