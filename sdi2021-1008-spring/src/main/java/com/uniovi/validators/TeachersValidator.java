package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.TeacherService;

@Component
public class TeachersValidator implements Validator {
	@Autowired
	private TeacherService teachersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Teacher.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;
		if (teacher.getDni().length() < 9 || teacher.getDni().length() > 9 || !Character.isLetter(teacher.getDni().charAt(8))) {
			errors.rejectValue("dni", "Error.teacher.dni.length");
		}
		if (teachersService.getTeacherByDni(teacher.getDni()) != null) {
			errors.rejectValue("dni", "Error.teacher.dni.duplicate");
		}
	}
}