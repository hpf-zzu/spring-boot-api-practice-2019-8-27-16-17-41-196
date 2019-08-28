package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
//@RequestMapping("/hello")
@RequestMapping("/companies")
public class HelloResource {


    private static List<Employee> employees = new ArrayList<Employee>(){{
       add(new Employee(4,"alibaba1",20,"male",6000));
       add(new Employee(11,"tengxun2",19,"female",7000));
       add(new Employee(6,"alibab3",19,"male",8000));
    }};

    private static List<Company> Companies = new ArrayList<Company>(){{
        add(new Company(1,"alibaba",200,employees));
    }};


//    @GetMapping(path = "/{userName}")
//    public ResponseEntity<String> getAll(@PathVariable String userName) {
//
//        return ResponseEntity.ok("Hello:" + userName);
//    }
   // @GetMapping("/companies")
    @GetMapping
    public  ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(Companies);

    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getOneCompany(@PathVariable int id){
        for(int i = 0; i < Companies.size(); i ++){
            if(Companies.get(i).getId() == id){
                return ResponseEntity.ok(Companies.get(i));
            }
        }
        return null;
    }
    @GetMapping(path = "/{id}/{employees}")
    public ResponseEntity<List<Employee>> getCompanyEmployees(@PathVariable("id") int id,@PathVariable("employees") String employees){
        for(int i = 0; i < Companies.size(); i ++){
            if(Companies.get(i).getId() == id){
                if(employees.equals("employees")) {
                    return ResponseEntity.ok(Companies.get(i).getEmployees());
                }

            }
        }
        return null;
    }
    @PostMapping(consumes = "application/json")
    //constructor default
    public ResponseEntity<Company> addCompany(@RequestBody Company CompanyInput){
        Companies.add(CompanyInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }




}
