package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String getAllStudents(Model theModel) {
		List<Student> res = studentService.findAll();
		theModel.addAttribute("studentModel", res);
		return "list-Students";
	}

	@RequestMapping("/add")
	public String addStudent(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student", student);
		return "Student-form";
	}

	@RequestMapping("/update")
	public String updateStudent(@RequestParam("id") int id, Model theModel) {
		// get the student from the service
		Student student = studentService.findById(id);

		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("student", student);

		// send over to our form
		return "Student-form";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int theId) {

		// delete the student
		studentService.deleteById(theId);

		// redirect to /student/list
		return "redirect:/student/list";
	}

	@PostMapping("/save")
	public String savestudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);
		} else {
			student = new Student();
		}
		student.setName(name);
		student.setDepartment(department);
		student.setCountry(country);

		// save the student
		studentService.save(student);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";
	}

}
