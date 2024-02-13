package Servlets;

import models.DatabaseConnector;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//this servlet is mapped to the URL pattern "/allStudents"
@WebServlet("/allStudents")
public class AllStudentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Connect to the database using JDBC
        try {
            Connection connection = DatabaseConnector.connect();

            // Execute SQL query to get all students
            String query = "SELECT * FROM students";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Generate HTML response dynamically
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Grit Academy</title><link rel='stylesheet' type='text/css' href='/css/styles.css'></head>");
            out.println("<body>");
            out.println("<div class='navbar'>");
            out.println("<a href='/allStudents' class='active'>All Students</a>");
            out.println("<a href='/allCourses'>All Courses</a>");
            out.println("<a href='/studentCourses'>All Students with Courses</a>");
            out.println("<a href='/statistics'>Statistics</a>");
            out.println("</div>");

            out.println("<h2>Grit Academy Students</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>City</th><th>Interests</th></tr>");

            // Loop through the result set and print data in the table
            while (resultSet.next()) {
                out.println("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getString("Fname") + "</td><td>" + resultSet.getString("Lname") + "</td><td>" + resultSet.getString("ort") + "</td><td>" + resultSet.getString("intressen") + "</td></tr>");
            }


            out.println("</table></body></html>");
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

