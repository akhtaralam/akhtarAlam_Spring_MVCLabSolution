package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
public class LoadingPageController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/")
	public String Loader(Model theModel) {
		List<Student> res = studentService.findAll();
		theModel.addAttribute("studentModel", res);
		return "Student-form";
	}
}
