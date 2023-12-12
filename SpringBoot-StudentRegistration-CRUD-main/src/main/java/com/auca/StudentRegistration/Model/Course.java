package com.auca.StudentRegistration.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "academic_unit_code")
    private AcademicUnit department;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<StudentCourse> studentCourses;

    @ManyToOne
    @JoinColumn(name = "course_definition_id")
    private CourseDefinition courseDefinition;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    public Course() {
    }

    public Course(int id, AcademicUnit department, List<StudentCourse> studentCourses, CourseDefinition courseDefinition, Semester semester) {
        this.id = id;
        this.department = department;
        this.studentCourses = studentCourses;
        this.courseDefinition = courseDefinition;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AcademicUnit getDepartment() {
        return department;
    }

    public void setDepartment(AcademicUnit department) {
        this.department = department;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public CourseDefinition getCourseDefinition() {
        return courseDefinition;
    }

    public void setCourseDefinition(CourseDefinition courseDefinition) {
        this.courseDefinition = courseDefinition;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
