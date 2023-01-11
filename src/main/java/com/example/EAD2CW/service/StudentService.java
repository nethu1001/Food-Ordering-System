package com.example.EAD2CW.service;

import java.util.List;

import com.example.EAD2CW.model.Student;

public interface StudentService 
{
	List<Student> getAllStudents();
	void saveStudent(Student student);
	Student getStudentById(long id);
	void deleteStudentById(long id);
}
