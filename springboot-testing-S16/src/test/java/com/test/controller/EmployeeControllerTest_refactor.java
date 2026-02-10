package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Employee;
import com.test.service.EmployeeService;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@WebMvcTest
public class EmployeeControllerTest_refactor {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    //Junit test for save employee operation
    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();
        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        //then - verify the output
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())));

    }

    //Junit test for Get All Employee
    @Test
    public void givenListOfEmployee_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        //given - precondition or setup
        //need to stub getAllEmployees()
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("pradeep").lastName("kumar")
                .email("pradeep@gmail.com").build());
        listOfEmployees.add(Employee.builder().firstName("punnu").lastName("chowdary")
                .email("punnu@gmail.com").build());
        given(employeeService.getAllEmployees())
                .willReturn(listOfEmployees);
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees"));
        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    //Junit test for Get Employee By Id(Positive scenario)
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        //given - precondition or setup
        long empId = 1L;
        Employee employee = Employee.builder()
                //.id(1L)
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        given(employeeService.getEmployeeById(empId))
                .willReturn(Optional.of(employee));
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", empId));
        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    //Junit test for Get Employee By Id(Negative scenario)
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject_negative() throws Exception {
        //given - precondition or setup
        long empId = 1L;
        Employee employee = Employee.builder()
                //.id(1L)
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        given(employeeService.getEmployeeById(empId))
                .willReturn(Optional.empty());
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", empId));
        //then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    //Junit test for update employee REST API positive scenario
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        //given - precondition or setup
        Long empId = 1L;
        Employee savedEmployee = Employee.builder()
                //.id(1L)
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        Employee updatedEmployee = Employee.builder()
                //.id(1L)
                .firstName("pradeep kumar")
                .lastName("kandyala")
                .email("pradeepkumar@gmail.com")
                .build();

        /*need to stub for getEmployeeById & updateEmployee methods*/
        given(employeeService.getEmployeeById(empId)).willReturn(Optional.of(savedEmployee));
        given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", empId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
    }

    //Junit test for update employee REST API Negative scenario
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee_Negative() throws Exception {
        //given - precondition or setup
        Long empId = 1L;
        Employee savedEmployee = Employee.builder()
                //.id(1L)
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        Employee updatedEmployee = Employee.builder()
                //.id(1L)
                .firstName("pradeep kumar")
                .lastName("kandyala")
                .email("pradeepkumar@gmail.com")
                .build();

        /*need to stub for getEmployeeById & updateEmployee methods*/
        given(employeeService.getEmployeeById(empId)).willReturn(Optional.empty());
        given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", empId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    //Junit test for delete employee rest api
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
        //given - precondition or setup
        Long empId = 1L;
        willDoNothing().given(employeeService).deleteEmployee(empId);

        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", empId));

        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
