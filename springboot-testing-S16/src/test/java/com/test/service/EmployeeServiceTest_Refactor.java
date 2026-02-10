package com.test.service;

import com.test.exception.ResourceNotFoundException;
import com.test.model.Employee;
import com.test.repo.EmployeeRepository;
import com.test.service.impl.EmployeeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest_Refactor {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();
    }

    //Junit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given - precondition or setup
       /* Employee employee = Employee.builder()
                .id(1L)
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();*/

        //stubbing the methods
        //stubbing means we are mocking the behaviour of a method
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println("Employee Object: " + savedEmployee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //Junit test for save employee operation
    @DisplayName("JUnit test for save employee operation which throws Exception")
    @Test
    public void givenExistingEmail_whenSave_thenThrowsException() {
        //given - precondition or setup

        //stubbing the methods
        //stubbing means we are mocking the behaviour of a method
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        //if we dont comment the below line then it will call the save method and test case will fail
        /*given(employeeRepository.save(employee))
                .willReturn(employee);*/

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //When - The behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        //then - verify the output
        verify(employeeRepository, Mockito.never()).save(Mockito.any(Employee.class));
    }

    //Junit test for testing getAll Employees
    @DisplayName("JUnit test for testing getAll Employees")
    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {
        //given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("jyo")
                .lastName("deepu")
                .email("jyo@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,
                employee1));

        //When - The behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //Junit test for testing getAll Employees Negative Scenario
    @DisplayName("JUnit test for testing getAll Employees(Negative Scenario)")
    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList_Negative() {
        //given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("jyo")
                .lastName("deepu")
                .email("jyo@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //When - The behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        //then - verify the output
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    //Junit test for Get employee by id operation
    @DisplayName("JUnit test for Get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.of(employee));
        //When - The behaviour that we are going to test

        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Pradeep");
    }

    //Junit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        //given - precondition or setup
        //stubbing part
        given(employeeRepository.save(employee))
                .willReturn(employee);
        employee.setEmail("pradeepk@gmail.com");
        employee.setFirstName("Pradeep kumar");
        employee.setLastName("Kandyala");

        //When - The behaviour that we are going to test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        //then - verify the output
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("pradeepk@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Pradeep kumar");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Kandyala");
    }

    //Junit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenNothing() {

        //given - precondition or setup
        Long empId = 1L;
        willDoNothing().given(employeeRepository).deleteById(empId);

        //When - The behaviour that we are going to test
        employeeService.deleteEmployee(empId);

        //then - verify the output
        verify(employeeRepository, Mockito.times(1)).deleteById(empId);
    }
}
