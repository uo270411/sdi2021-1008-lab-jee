package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.services.TeacherService;
import com.uniovi.validators.TeachersValidator;

@Controller
public class TeachersController {

	@Autowired // Inyectar el servicio
	private TeacherService teacherService;

	@Autowired
	 private TeachersValidator teachersValidator;
	
	@RequestMapping("/teacher/list")
	public String getListado(Model model, @RequestParam(value="", required=false) String searchText) {
		model.addAttribute("teachersList", teacherService.getTeachers());
		return "redirect:/teacher/list";
	}
	
	@RequestMapping("/teacher/edit/{dni}")
	public String editTeacher(@PathVariable String dni) {
		return teacherService.editTeacher(dni);
	}

	@RequestMapping(value="/teacher/add", method=RequestMethod.POST)
	public String addTeacher(@Validated Teacher teacher, BindingResult result) {
		teachersValidator.validate(teacher, result);
		if(result.hasErrors()) {
			return "add";
		}
		teacherService.addTeacher(teacher);
		return "added";
	}

	@RequestMapping("/teacher/delete/{dni}")
	public String deleteTeacher(@PathVariable String dni) {
		teacherService.deleteTeacher(dni);
		return "deleted";
	}

	@RequestMapping("/teacher/details/{dni} ")
	public String getDetail(@PathVariable String dni) {
		return teacherService.getTeacher(dni).toString();
	}

}
