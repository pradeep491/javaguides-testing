package com.test.service;

import com.test.model.Employee;
import com.test.repo.EmployeeRepository;
import com.test.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    //Junit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        //stubbing the methods
        //stubbing means we are mocking the behaviour of a method
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //When - The behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println("Employee Object: " + savedEmployee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

}
