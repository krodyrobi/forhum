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

public class Login extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("errors", "");
    request.setAttribute("username", "");
    
    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
  }
  
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    User user;
    
    List<String> errors = new ArrayList<String>();
    
    if ( username == null ) {
      username = "";
      errors.add("Username not set");
    }
    
    Connection connection = (Connection) getServletContext().getAttribute("con");
    UserRepository userRepository = new UserRepository(connection);
    
    user = userRepository.get(username, password);
    if (user == null) {
      errors.add("Username or password is incorrect.");
    }
    
    if (!errors.isEmpty()) {
      request.setAttribute("errors", errors);
      request.setAttribute("username", username);
      
      request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
      
      return;
    }
    
    
    HttpSession session = request.getSession(true);	    
    session.setAttribute("sessionUser", user); 
    response.sendRedirect(request.getContextPath() + "/topics");
  }
}
