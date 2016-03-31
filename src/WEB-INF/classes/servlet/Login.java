package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "HELLO THERE";

        final PrintWriter writerA = response.getWriter();
        final PrintWriter writerB = response.getWriter();
        writerA.println("A1");
        writerB.println("B1");

//        request.setAttribute("result", result);
//        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request, response);
    }

}