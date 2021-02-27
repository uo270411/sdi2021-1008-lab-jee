package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.repositories.MarksRepository;

@Service
public class MarksService {

	@Autowired
	private MarksRepository marksRepository;
	private List<Mark> marksList = new LinkedList<Mark>();
	
	@Autowired
	private HttpSession httpSession;

	@PostConstruct
	public void init() {
		marksList.add(new Mark(1L, "Ejercicio 1", 10.0));
		marksList.add(new Mark(2L, "Ejercicio 2", 9.0));
	}

	public List<Mark> getMarks() {
		List<Mark> marks = new ArrayList<Mark>();
		marksRepository.findAll().forEach(marks::add);
		return marks;

	}

	public void addMark(Mark mark) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		marksRepository.save(mark);
		}
	
	public Mark getMark(Long id){
		Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");
		if ( consultedList == null ) {
		consultedList = new HashSet<Mark>();
		}
		Mark obtainedmark = marksRepository.findById(id).get();
		consultedList.add(obtainedmark);
		httpSession.setAttribute("consultedList", consultedList);
		return obtainedmark;
		}



	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}
}
