package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeacherService;

@RestController
public class TeachersController {

	@Autowired // Inyectar el servicio
	private TeacherService teacherService;

	@RequestMapping("/teacher/edit/{dni}")
	public String editTeacher(@PathVariable String dni) {
		return teacherService.editTeacher(dni);
	}

	@RequestMapping(value="/teacher/add", method=RequestMethod.POST)
	public String addTeacher(@ModelAttribute Teacher teacher) {
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
