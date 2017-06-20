package com.assignment;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private Dbutil empDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
    
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			empDbUtil = new Dbutil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			System.out.println(theCommand);
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
//			case "LIST":
//				listEmployee(request, response);
//				break;
				
			case "ADD":
				addEmployee(request, response);
				break;
//				
//			case "LOAD":
//				loadEmployee(request, response);
//				break;
//				
//			case "UPDATE":
//				updateEmployee(request, response);
//				break;
//			
//			case "DELETE":
//				deleteEmployee(request, response);
//				break;
//				
//			default:
//				listEmployee(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

//	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//
//		// read student id from form data
//		String Id = request.getParameter("studentId");
//		
//		// delete student from database
//		empDbUtil.deleteEmployee(Id);
//		
//		// send them back to "list students" page
//		listEmployee(request, response);
//	}
//
//	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//
//		// read student info from form data
//		int id = Integer.parseInt(request.getParameter("studentId"));
//		String firstName = request.getParameter("firstName");
//		String password = request.getParameter("password");
//		String email = request.getParameter("email");
//		String dob = request.getParameter("dob");
//		String country = request.getParameter("country");
//		String gender = request.getParameter("gender");
//		
//		// create a new student object
//		Employee emp = new Employee(firstName, password, email, dob, country, gender);
//		
//		// perform update on database
//		empDbUtil.updateEmployee(emp);
//		
//		// send them back to the "list students" page
//		listEmployee(request, response);
//		
//	}
//
//	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) 
//		throws Exception {
//
//		// read student id from form data
//		String id = request.getParameter("empId");
//		
//		// get student from database (db util)
//		Employee emp = empDbUtil.getEmployee(id);
//		
//		// place student in the request attribute
//		request.setAttribute("THE_STUDENT", emp);
//		
//		// send to jsp page: update-student-form.jsp
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("/update-student-form.jsp");
//		dispatcher.forward(request, response);		
//	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String firstName = request.getParameter("firstName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String country = request.getParameter("country");
		String gender = request.getParameter("gender");
		
		// create a new student object
		Employee empdata = new Employee(firstName, password, email, dob, country, gender);
				
		// add the student to the database
		empDbUtil.addEmployee(empdata);
				
		// send back to main page (the student list)
	//	listEmployee(request, response);
	}

//	private void listEmployee(HttpServletRequest request, HttpServletResponse response) 
//		throws Exception {
//
//		// get students from db util
//		List<Employee> emp = empDbUtil.getEmployee();
//		
//		// add students to the request
//		//request.setAttribute("STUDENT_LIST", emp);
//				
//		// send to JSP page (view)
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
//		dispatcher.forward(request, response);
//	}
//	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
