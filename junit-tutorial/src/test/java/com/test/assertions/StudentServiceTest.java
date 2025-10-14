package com.test.assertions;

import com.test.Student;
import com.test.StudentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    public void getAllStudentsTest() {
        StudentService studentService = new StudentService();

        Student student = new Student(491, "pradeep");
        studentService.addStudent(student);
        List<Student> listOfStudents = studentService.getAllStudents();
        boolean actualResult = listOfStudents.isEmpty();

        //assertTrue(actualResult);

        //assertTrue(()->actualResult);

        //assertTrue(actualResult, "List of students should be Empty");

        //assertTrue(()->actualResult, "List of students should be Empty");

        //assertTrue(actualResult, "List of students should be Empty");

        assertTrue(() -> actualResult, "List of students should be Empty");
    }

    @Test
    public void getAllStudentsTest_using_assertFalse() {
        StudentService studentService = new StudentService();
        Student student = new Student(491, "pradeep");
        studentService.addStudent(student);
        List<Student> listOfStudents = studentService.getAllStudents();
        boolean actualResult = listOfStudents.isEmpty();
        //assertFalse(actualResult);

        //assertFalse(actualResult, "List of students should not be Empty");

        //assertFalse(() -> actualResult);

        //assertFalse(() -> actualResult,"List of students should not be Empty");

        //assertFalse(actualResult, ()->"List of students should not be Empty");

        assertFalse(()->actualResult,()->"List of students should not be Empty");
    }
}