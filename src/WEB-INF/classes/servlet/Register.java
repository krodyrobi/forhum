package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

import db.models.User;
import db.repositories.UserRepository;

public class Register extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    request.setAttribute("errors", "");
    request.setAttribute("username", "");
    
    if (session == null) {
      request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
      return;
    }
    
    User user = (User) session.getAttribute("sessionUser");
    if (user != null) {
      response.sendRedirect(request.getContextPath() + "/topics");
      return;
    }
    
    request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
  }
  
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    User user = null;
    
    List<String> errors = new ArrayList<String>();
    
    if ( username == null || username.equals("")) {
      username = "";
      errors.add("Username not set");
    }
    
     
    if ( password == null || password.equals("")) {
      password = "";
      errors.add("Password not set");
    }
    
    Connection connection = (Connection) getServletContext().getAttribute("con");
    UserRepository userRepository = new UserRepository(connection);
    
    if (errors.isEmpty()) {
      user = userRepository.create(username, password);
      if (user == null) {
        errors.add("Couldn't create user.");
      }
    }
    
    if (errors.isEmpty()) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    
    request.setAttribute("errors", errors);
    request.setAttribute("username", username);
    request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
  }
}
