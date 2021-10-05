package com.example.springhibernate001.service;

import com.example.springhibernate001.entity.Employee;
import com.example.springhibernate001.exception.EntityNotFoundException;
import com.example.springhibernate001.mapper.EmployeeMapper;
import com.example.springhibernate001.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService implements CrudService<Employee, Long> {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    private final String EMPLOYEE_NOT_FOUND_MESSAGE = "Could not find employee with id %s";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Set<Employee> findAll() {
        return new HashSet<>(employeeRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE, id)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee update(Long id, Employee entity) {
        boolean isExists = employeeRepository.existsById(id);
        if (isExists) {
            Employee employee = findById(id);
            employeeMapper.map(entity, employee);
            employeeRepository.save(employee);
            return employee;
        }
        throw new EntityNotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE, id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
