package com.chary.main.controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chary.main.model.Student;
import com.chary.main.service.StudentService;

@RestController
@RequestMapping("/student")
public class Controller {

	private final StudentService studentService = new StudentService();

	@GetMapping("/getStudents")
	public List<Student> getStudents() throws SQLException, IllegalArgumentException, IllegalAccessException {
		return studentService.getAllStudents();
	}

	@GetMapping("/getStudents/")
	public List<Student> getStudentByName(@RequestBody String name)
			throws SQLException, IllegalArgumentException, IllegalAccessException {
		return studentService.getStudentByName(name);
	}

	@PostMapping("/postStudents")
	public JSONObject postStudents(@RequestBody List<Student> _students) throws Exception {
		try {
			JSONObject jObj=new JSONObject();
			jObj.put("Id's", studentService.postObjectsToDb(_students).toString());
			//System.out.println(jObj);
			return jObj;
		} catch (Exception e) {
			JSONObject jObj=new JSONObject();
			System.out.println("Caught"+e);
			jObj.put("Error", e.getMessage());
			//System.out.println(jObj);
			return jObj;
		}
	}
	
	@PostMapping("/post")
	public String postJsonStudents(@RequestBody String jsonData) throws SQLException, IOException, ParseException {
		return studentService.postJsonStudents(jsonData).toString();
	}
	
	@RequestMapping("/get")
	public void get() throws ParseException, SQLException, IOException {
		studentService.getCsv();
	}
	
	@RequestMapping("/post2")
	public void post() throws IOException, InterruptedException, SQLException {
		studentService.post();
	}
	
}

//@RestController
//@RequestMapping(path="/studentJson")
//class ControllerJson{
//	
//	StudentService studentService=new StudentService();
//	
//	
//}
