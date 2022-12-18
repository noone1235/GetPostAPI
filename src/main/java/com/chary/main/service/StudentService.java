package com.chary.main.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.chary.main.model.Student;
import com.chary.main.repository.StudentRepo;

public class StudentService {

	StudentRepo studentRepo = new StudentRepo();

	public Connection getConnection() throws SQLException {
		return studentRepo.establishConnection();
	}

	public List<Student> getAllStudents() throws SQLException, IllegalArgumentException, IllegalAccessException {
		return studentRepo.getJsonFromDb();
	}

	public List<Student> getStudentByName(String name)
			throws SQLException, IllegalArgumentException, IllegalAccessException {
		return studentRepo.getJsonFromDbByName(name);
	}

	public List<Integer> postObjectsToDb(List<Student> _students) throws Exception {

		List<Integer> values = new ArrayList<>();

		for (Student student : _students) {
			if (student.getId() == 0) {
				//System.out.println("first if"+studentRepo.save(student));
				values.add(studentRepo.save(student));
			} else {
				 //System.out.println(studentRepo.get(student.getId()).getMarks());

				if (studentRepo.get(student.getId()) != null) {
					//System.out.println("yes"+studentRepo.save(student));
					values.add(studentRepo.update(student));
				} else {
					//System.out.println("Error");
					throw new RuntimeException("Enter a valid Id");
				}
			}
			// System.out.println(msg);
		}
		return values;
	}

	
	public List<Integer> postJsonStudents(String jsonData) throws SQLException, IOException, ParseException{
		return studentRepo.saveJson(jsonData);
	}
	
	public void getCsv() throws ParseException, SQLException, IOException {
		studentRepo.saveToCsv2();
	}

	public void post() throws IOException, InterruptedException, SQLException {
		studentRepo.save();
	}
}
