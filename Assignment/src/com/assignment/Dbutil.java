package com.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;




public class Dbutil {
	
	private DataSource dataSource;
	
	public Dbutil(DataSource ds) {
		dataSource = ds;
	}

public List<Employee> getEmployee() throws Exception {
		
		List<Employee> emp = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			String sql = "select * from employee order by employee_name";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
		
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String password = myRs.getString("password");
				String email = myRs.getString("email");
				String dob = myRs.getString("dob");
				String country = myRs.getString("country");
				String gender = myRs.getString("gender");
				
				Employee empdetail = new Employee(id, firstName, password , email , dob ,gender ,country);
				
				
				emp.add(empdetail);				
			}
			
			return emp;		
		}
		finally {
			
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addEmployee(Employee emp) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			System.out.println("connect!!!");
	
			myConn = dataSource.getConnection();	
			System.out.println("connecteed!!!");
			
			String sql = "insert into employee "
					   + "(employee_name,employee_password,employee_email,employee_dob,employee_country,employee_gender) "
					   + "values (?, ?, ?, ?, ?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setString(1, emp.getFirstName());
			myStmt.setString(2, emp.getPassword());
			myStmt.setString(3, emp.getEmail());
			myStmt.setString(4, emp.getDOB());
			myStmt.setString(5, emp.getCountry());
			myStmt.setString(6, emp.getGender());
		
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Employee getEmployee(String id) throws Exception {

		Employee emp = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int empId;
		
		try {
	
			empId = Integer.parseInt(id);
		
			myConn = dataSource.getConnection();
			
			String sql = "select * from employee where idemployee =?";
			
			myStmt = myConn.prepareStatement(sql);
		
			myStmt.setInt(1, empId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String password = myRs.getString("password");
				String email = myRs.getString("email");
				String dob = myRs.getString("dob");
				String country = myRs.getString("country");
				String gender = myRs.getString("gender");
				// use the studentId during construction
				emp = new Employee(empId, firstName, password, email, dob, country, gender);
			}
			else {
				throw new Exception("Could not find student id: " + empId);
			}				
			
			return emp;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
		public void updateEmployee(Employee emp) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "update employee "
							+ " employee_name=? ,employee_password =?,employee_email =? ,employee_dob =?,employee_country = ?,employee_gender=? "
							+ "where idemployee=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, emp.getFirstName());
				myStmt.setString(2, emp.getPassword());
				myStmt.setString(3, emp.getEmail());
				myStmt.setString(4, emp.getDOB());
				myStmt.setString(5, emp.getCountry());
				myStmt.setString(6, emp.getGender());
				myStmt.setInt(7, emp.getId());
				
				// execute SQL statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}

		public void deleteEmployee(String Id) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// convert student id to int
				int empId = Integer.parseInt(Id);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to delete student
				String sql = "delete from employee where idemployee=?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, empId);
				
				// execute sql statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC code
				close(myConn, myStmt, null);
			}	
	}

}
