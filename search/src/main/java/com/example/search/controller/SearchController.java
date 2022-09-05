package com.example.search.controller;

import com.example.search.exception.EmployeeNotFoundException;
import com.example.search.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final EmployeeService employeeService;

    public SearchController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/search/employees")
    @HystrixCommand(fallbackMethod = "getFallback")
    public ResponseEntity<?> getDetails(@RequestParam List<Integer> ages) {
        //TODO
        if(employeeService.fetchAllEmployeesByAges(ages) == null){
            throw new EmployeeNotFoundException();
        }
        return new ResponseEntity<>(employeeService.fetchAllEmployeesByAges(ages), HttpStatus.OK);
    }

    /** When the circuit breaks, Only call the Fallback method*/
    public ResponseEntity<?> getFallback(@RequestParam List<Integer> ages){
        return new ResponseEntity<>("Retry! Too Many Requests", HttpStatus.OK);
    }
}
