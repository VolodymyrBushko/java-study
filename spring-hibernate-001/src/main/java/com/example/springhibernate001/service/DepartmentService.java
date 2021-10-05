package com.example.springhibernate001.service;

import com.example.springhibernate001.entity.Department;
import com.example.springhibernate001.exception.EntityNotFoundException;
import com.example.springhibernate001.mapper.DepartmentMapper;
import com.example.springhibernate001.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentService implements CrudService<Department, Long> {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    private final String DEPARTMENT_NOT_FOUND_MESSAGE = "Could not find department with id %s";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Set<Department> findAll() {
        return new HashSet<>(departmentRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(DEPARTMENT_NOT_FOUND_MESSAGE, id)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Department save(Department entity) {
        return departmentRepository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Department update(Long id, Department entity) {
        boolean isExists = departmentRepository.existsById(id);
        if (isExists) {
            Department department = findById(id);
            departmentMapper.map(entity, department);
            departmentRepository.save(department);
            return department;
        }
        throw new EntityNotFoundException(String.format(DEPARTMENT_NOT_FOUND_MESSAGE, id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
