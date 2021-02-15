package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;
import com.uniovi.repositories.TeachersRepository;

@Service
public class TeacherService {
	@Autowired
	private TeachersRepository teachersRepository;
	

	public Teacher getTeacher(String dni) {
		return teachersRepository.findById(dni).get();
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
