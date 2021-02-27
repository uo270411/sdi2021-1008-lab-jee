package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
public interface TeachersRepository extends CrudRepository<Teacher, String>{

	User findByDni(String dni);
}
