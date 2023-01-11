package com.example.EAD2CW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.EAD2CW.model.Student;
import com.example.EAD2CW.service.StudentService;

@Controller
public class StudentController 
{
	//Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.
	@Autowired
	private StudentService studentService;
	//Display List of Employees
	@GetMapping("/users")
	public String viewHomePage(Model model)
	{
		model.addAttribute("listStudent", studentService.getAllStudents());
		return "index";
	}

	//Annotation for mapping HTTP GET requests onto specific handler methods.
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model)
	{
		//Create model attribute to bind from data
		Student student = new Student();
		model.addAttribute("student", student);
		return "new_student";
	}

	//Annotation for mapping HTTP POST requests onto specific handler methods.
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student)
	{
		//Save Student to Database
		studentService.saveStudent(student);
		return "redirect:/users";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model)
	{
		//Get Employee from the Service
		Student student = studentService.getStudentById(id);
		
		//set Student as a model attribute to pre-poputate the form
		
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id)
	{
		//Call Delete Student Method
		this.studentService.deleteStudentById(id);
		return "redirect:/users";
	}
}
