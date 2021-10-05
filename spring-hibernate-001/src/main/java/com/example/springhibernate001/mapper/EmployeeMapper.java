package com.example.springhibernate001.mapper;

import com.example.springhibernate001.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements Mapper<Employee> {

    public void map(Employee from, Employee to) {
        to.setName(from.getName());
        to.setPosition(from.getPosition());
        to.setBirthday(from.getBirthday());
        to.setDepartment(from.getDepartment());
        to.setGender(from.getGender());
    }
}
