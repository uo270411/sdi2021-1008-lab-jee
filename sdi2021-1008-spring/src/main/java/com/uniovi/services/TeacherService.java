package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeacherService {
	private List<Teacher> teachersList = new LinkedList<Teacher>();

	@PostConstruct
	public void init() {
		teachersList.add(new Teacher("0", "Miguel", "Rodriguez", "Ciencias"));
		teachersList.add(new Teacher("1", "Jose", "Martinez", "Ingles"));
	}

	public List<Teacher> getTeachers() {
		return teachersList;
	}

	public Teacher getTeacher(String dni) {
		return teachersList.stream().filter(teacher -> teacher.getDni().equals(dni)).findFirst().get();
	}

	
	public void addTeacher(Teacher teacher) {
		teachersList.add(teacher);
			}

	public void deleteTeacher(String dni) {
		teachersList.removeIf(teacher -> teacher.getDni().equals(dni));
	}
	
	public String editTeacher(String dni) {
		return "edited";
	}
}
