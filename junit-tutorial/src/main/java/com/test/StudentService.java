package com.test;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        // Logic to retrieve all students
        return this.students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

}
