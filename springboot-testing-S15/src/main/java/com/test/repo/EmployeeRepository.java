package com.test.repo;

import com.test.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    //Define  Custom query using JPQL with index parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 and e.lastName = ?2")
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    //Define  Custom query using JPQL with Named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = :fname and e.lastName = :lname")
    Employee findByFirstNameAndLastNameUsingNamedParam(@Param("fname") String firstName,
                                                       @Param("lname") String lastName);


    //Define  Custom query using Native SQL with index parameters
    @Query(value="SELECT * FROM employees e WHERE e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    //Define  Custom query using Native SQL with Named parameters
    @Query(value="SELECT * FROM employees e WHERE e.first_name = :fname and e.last_name = :lname", nativeQuery = true)
    Employee findByNativeSQLUsingNamedParam(@Param("fname") String firstName,
                                               @Param("lname") String lastName);
}