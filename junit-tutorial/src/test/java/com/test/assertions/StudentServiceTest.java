package com.test.assertions;

import com.test.Student;
import com.test.StudentNotFoundException;
import com.test.StudentService;
import org.junit.jupiter.api.Test;

import java.rmi.StubNotFoundException;
import java.util.Arrays;
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

        assertFalse(() -> actualResult, () -> "List of students should not be Empty");
    }

    @Test
    public void getStudentByIdTest_using_assertNull() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep");
        studentService.addStudent(student);

        Student actualObject = studentService.getStudentById(2);

        //assertNull(actualObject);

        //assertNull(actualObject,"Student Object is not Null");
        assertNull(actualObject, () -> "Student Object is not Null");

    }

    @Test
    public void getStudentByIdTest_using_assertNotNull() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep");
        studentService.addStudent(student);

        Student actualObject = studentService.getStudentById(1);

        //assertNotNull(actualObject);
        //assertNotNull(actualObject,"Student Object is Null");
        assertNotNull(actualObject, () -> "Student Object is Null");

    }

    @Test
    public void getStudentByIdTest_using_assertEquals() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep");
        studentService.addStudent(student);

        Student actualObject = studentService.getStudentById(1);

        //assertEquals(1,actualObject.getId());
        //assertEquals(actualObject.getName(),"pradeep");

        //assertEquals(student,actualObject);
        //assertEquals(2,actualObject.getId(),"student id is not equals...!");
        assertEquals(actualObject.getName(), "pradeep1", () -> "Student name not matched");

    }

    @Test
    public void getStudentByIdTest_using_assertNotEquals() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep");
        studentService.addStudent(student);

        Student actualObject = studentService.getStudentById(1);

        //assertNotEquals(2,actualObject.getId());
        //assertNotEquals(actualObject.getName(),"pradeep1");

        //assertNotEquals(student,actualObject);
        //assertNotEquals(2,actualObject.getId(),"student id is equals...!");
        //assertNotEquals(actualObject.getName(),"pradeep",()->"Student name matched");
        assertNotEquals(actualObject, student);
    }

    @Test
    public void getStudentNamesByDepartmentTest_using_assertArrayEquals() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep", "IT");
        Student student1 = new Student(2, "kumar", "Finance");
        Student student2 = new Student(3, "punnu", "Admin");
        Student student3 = new Student(4, "jasvin", "IT");

        studentService.addStudent(student);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        String[] studentNames = studentService.getStudentNamesByDepartment("IT");

        String expectedArray[] = {"pradeep", "jasvin"};

        //assertArrayEquals(expectedArray, studentNames);
        //assertArrayEquals(expectedArray, studentNames,"student names are not equal");
        //assertArrayEquals(expectedArray, studentNames,()->"student names are not equal");

        //Integer Array Test
        Integer[] actualIds = studentService.getStudentIdsByDepartment("IT");
        Integer[] expectedIdArray = {1, 4};
        //assertArrayEquals(expectedIdArray, actualIds);
        //assertArrayEquals(expectedIdArray, actualIds,"student ID's are different");
        assertArrayEquals(expectedIdArray, actualIds, () -> "student ID's are different");
    }

    @Test
    public void getStudentNamesListByDepartmentTest_using_assertIterableEquals() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep", "IT");
        Student student1 = new Student(2, "kumar", "Finance");
        Student student2 = new Student(3, "punnu", "Admin");
        Student student3 = new Student(4, "jasvin", "IT");

        studentService.addStudent(student);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        List<String> studentNames = studentService.getStudentNamesListByDepartment("IT");

        List<String> expectedListArray = Arrays.asList("pradeep", "jasvin");

        //assertIterableEquals(expectedListArray, studentNames);

        //assertIterableEquals(expectedListArray, studentNames,"student names list is not equal");
        //assertIterableEquals(expectedListArray, studentNames,()->"student names list is not equal");

        //Integer Array Test
        List<Integer> actualIdsList = studentService.getStudentIdsListByDepartment("IT");
        List<Integer> expectedIdList = Arrays.asList(1, 4);
        //assertIterableEquals(expectedIdList, actualIdsList);
        //assertIterableEquals(expectedIdList, actualIdsList,"student ID's List is different");
        assertIterableEquals(expectedIdList, actualIdsList, () -> "student ID'sLisr is different");
    }

    @Test
    public void getStudentByNameTest_using_assertThrows() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep", "IT");
        Student student1 = new Student(2, "kumar", "Finance");
        Student student2 = new Student(3, "punnu", "Admin");
        Student student3 = new Student(4, "jasvin", "IT");

        studentService.addStudent(student);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

/*
        assertThrows(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                });

        assertThrows(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                },"student not found exception should be thrown but it was not...!");
        */
        assertThrows(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                }, ()->"student not found exception should be thrown but it was not...!");
    }
    @Test
    public void getStudentByNameTest_using_assertThrowsExactly() {
        StudentService studentService = new StudentService();
        Student student = new Student(1, "pradeep", "IT");
        Student student1 = new Student(2, "kumar", "Finance");
        Student student2 = new Student(3, "punnu", "Admin");
        Student student3 = new Student(4, "jasvin", "IT");

        studentService.addStudent(student);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

/*
        assertThrowsExactly(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                });

        assertThrowsExactly(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                },"StudentNotFoundException should be thrown but it was not...!");

        assertThrowsExactly(RuntimeException.class,
                () -> {
                    studentService.getStudentByName("test");
                }, ()->"StudentNotFoundException should be thrown but it was not...!");
*/
        StudentNotFoundException exception = assertThrowsExactly(StudentNotFoundException.class,
                () -> {
                    studentService.getStudentByName("test");
                }, ()->"StudentNotFoundException should be thrown but it was not...!");

        assertEquals("student with the name:test not found...!",exception.getMessage());
    }
}