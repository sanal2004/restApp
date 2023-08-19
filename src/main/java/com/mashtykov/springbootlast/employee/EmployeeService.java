package com.mashtykov.springbootlast.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.IllegalPathStateException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepo.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent())
            throw new IllegalStateException("This email is already exists");
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (employeeOptional.isEmpty())
            throw new IllegalStateException("there is no employee with such id");
        employeeRepo.deleteById(id);
    }


    @Transactional
    public void updateEmployee(Long id, String name, String email){
Employee employee = employeeRepo.findById(id).
        orElseThrow(()-> new IllegalStateException("there is no employee with id=" + id));
if (name!=null && name.length() > 0 && !Objects.equals(name, employee.getName())){
    employee.setName(name);
}if (email!=null && email.length() > 0 && !Objects.equals(email, employee.getEmail())){
            Optional<Employee> employeeOptional = employeeRepo.findByEmail(employee.getEmail());
            if (employeeOptional.isPresent())
                throw new IllegalStateException("This email is already exists");
    employee.setEmail(email);
}
    }
}
