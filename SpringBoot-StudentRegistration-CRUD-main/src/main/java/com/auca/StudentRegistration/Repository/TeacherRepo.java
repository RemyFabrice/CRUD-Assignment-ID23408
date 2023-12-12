package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, String> {
}
