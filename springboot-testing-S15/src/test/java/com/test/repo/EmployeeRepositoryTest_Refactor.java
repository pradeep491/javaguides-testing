package com.test.repo;

import com.test.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class EmployeeRepositoryTest_Refactor {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();
    }
    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/

        //when - The behavious that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //Junit test for Get All employees operation
    @Test
    @DisplayName("JUnit test for Get All employees operation")
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        Employee employee2 = Employee.builder()
                .firstName("jyo")
                .lastName("deepu")
                .email("jyo@gmail.com")
                .build();

        employeeRepository.saveAll(List.of(employee, employee2));
        //When - The behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();
        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //Junit test for Get employee by id operation
    @DisplayName("JUnit test for Get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //When - The behaviour that we are going to test
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        //then - verify the output
        assertThat(employeeOptional).isPresent();
        assertThat(employeeOptional.get()).isNotNull();
    }

    //Junit test for Get employee by email operation
    @Test
    @DisplayName("JUnit test for Get employee by email operation")
    public void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);

        //When - The behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).orElse(null);

        //then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getEmail()).isEqualTo("pradeep@gmail.com");
    }

    //Junit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
       /* Employee employee= Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);

        //When - The behaviour that we are going to test
        Employee employeeToUpdate = employeeRepository.findById(employee.getId()).get();
        employeeToUpdate.setEmail("pradeepkumar@gmail.com");
        employeeToUpdate.setFirstName("Pradeep Kandyala");
        //then - verify the output
        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);

        assertThat(updatedEmployee.getEmail()).isEqualTo("pradeepkumar@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Pradeep Kandyala");
    }

    //Junit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given - precondition or setup
       /* Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //When - The behaviour that we are going to test
        employeeRepository.deleteById(employee.getId());
        //then - verify the output
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        assertThat(employeeOptional).isNotPresent();
        assertThat(employeeOptional).isEmpty();
    }

    //Junit test for custom query using JPQL with index parameters
    @DisplayName("JPQL test for custom query with index parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByFirstNameAndLastName_thenReturnEmployeeObject() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        String firstName = "Pradeep";
        String lastName = "kumar";
        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    //Junit test for custom query using JPQL with Named parameters
    @DisplayName("JPQL test for custom query with Named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByFirstNameAndLastName_thenReturnEmployeeObject_named() {
        //given - precondition or setup
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        String firstName = "Pradeep";
        String lastName = "kumar";
        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByFirstNameAndLastNameUsingNamedParam(firstName, lastName);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Native SQL test for custom query with index parameters")
    //Junit test for custom query using Native SQL with index parameters
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    //Junit test for custom query using Native SQL with Named parameters
    @DisplayName("Native SQL test for custom query with Named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject_named() {
        /*Employee employee = Employee.builder()
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/
        employeeRepository.save(employee);
        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLUsingNamedParam(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }
}
