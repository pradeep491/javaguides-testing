package com.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Employee;
import com.test.repo.EmployeeRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        //then - verify the output
        response.andDo(MockMvcResultHandlers.print()).
                andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));

    }
    @Test
    public void givenListOfEmployee_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        //given - precondition or setup
        //need to stub getAllEmployees()
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("pradeep").lastName("kumar")
                .email("pradeep@gmail.com").build());
        listOfEmployees.add(Employee.builder().firstName("punnu").lastName("chowdary")
                .email("punnu@gmail.com").build());

        employeeRepository.saveAll(listOfEmployees);
        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees"));
        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.
                        jsonPath("$.size()", CoreMatchers.is(2)));
    }

    //Junit test for Get Employee By Id(Negative scenario)
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        employeeRepository.save(employee);

        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employee.getId()));
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

        employeeRepository.save(employee);
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
        Employee savedEmployee = Employee.builder()
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        employeeRepository.save(savedEmployee);

        Employee updatedEmployee = Employee.builder()
                .firstName("pradeep kumar")
                .lastName("kandyala")
                .email("pradeepkumar@gmail.com")
                .build();

        /*need to stub for getEmployeeById & updateEmployee methods*/

       //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", savedEmployee.getId())
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
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        employeeRepository.save(savedEmployee);

        Employee updatedEmployee = Employee.builder()
                .firstName("pradeep kumar")
                .lastName("kandyala")
                .email("pradeepkumar@gmail.com")
                .build();

        /*need to stub for getEmployeeById & updateEmployee methods*/
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
        Employee savedEmployee = Employee.builder()
                .firstName("pradeep")
                .lastName("kumar")
                .email("pradeep@gmail.com")
                .build();

        employeeRepository.save(savedEmployee);

        //When - The behaviour that we are going to test
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", savedEmployee.getId()));

        //then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
