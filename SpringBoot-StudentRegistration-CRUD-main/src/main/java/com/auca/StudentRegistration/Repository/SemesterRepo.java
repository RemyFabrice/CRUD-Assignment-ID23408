package com.auca.StudentRegistration.Repository;

import com.auca.StudentRegistration.Model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, String> {
}
