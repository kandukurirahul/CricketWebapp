package com.Cricket;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterPlayer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String teamName="";
        String name = request.getParameter("name");
        Integer employeeId=Integer.parseInt(request.getParameter("employeeid"));
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String gender[]=request.getParameterValues("gender");
        Integer mobileno=Integer.parseInt(request.getParameter("mobileno"));
        String checkedSkills[]=request.getParameterValues("skills");
        Integer rating=Integer.parseInt("0");
        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testdb","testuser","password");

            PreparedStatement ps = con.prepareStatement
                    ("insert into employee_info values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,teamName);
            ps.setString(2, name);
            ps.setInt(3,employeeId);
            ps.setString(4, email);
            ps.setString(5, pass);
            String genderValue="";
            for(int i=0;i<gender.length;i++){
                genderValue+=gender[i]+"";
            }
            ps.setString(6,genderValue);
            ps.setInt(7,mobileno);
            String skillValues="";
            for(int i=0;i<checkedSkills.length;i++){
                skillValues+=checkedSkills[i]+" ";
            }
            ps.setString(8,skillValues);
            ps.setInt(9,rating);
            ps.setString(10,"Player");
            int i = ps.executeUpdate();

            if(i > 0) {
                out.println(name+" sucessfully registered");
            }
        }
        catch(Exception se) {
            se.printStackTrace();
        }
    }
}