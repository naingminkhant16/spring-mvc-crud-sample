package com.moe.springmvccrud.controller;

import com.moe.springmvccrud.entity.Employee;
import com.moe.springmvccrud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employees/create";
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/edit";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employees/edit";
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
