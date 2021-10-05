package com.example.springhibernate001.mapper;

import com.example.springhibernate001.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements Mapper<Department> {

    public void map(Department from, Department to) {
        to.setTitle(from.getTitle());
    }
}
