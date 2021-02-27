package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.repositories.TeachersRepository;
import com.uniovi.validators.SignUpFormValidator;
import com.uniovi.validators.TeachersValidator;

@Service
public class TeacherService {
	@Autowired
	private TeachersRepository teachersRepository;
	

	public Teacher getTeacher(String dni) {
		return teachersRepository.findById(dni).get();
	}
	
	public User getTeacherByDni(String dni) {
		return teachersRepository.findByDni(dni);
	}

	
	public void addTeacher(Teacher teacher) {
		teachersRepository.save(teacher);
			}

	public void deleteTeacher(String dni) {
		teachersRepository.deleteById(dni);
	}
	
	public String editTeacher(String dni) {
		return "edited";
	}
}
