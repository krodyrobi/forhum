import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBServletListener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    ServletContext sc = event.getServletContext();
    String url = sc.getInitParameter("db_url");
    
    Connection connection = null;
    
    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());
      connection = DriverManager.getConnection(url);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    sc.setAttribute("con", connection);
  }

  public void contextDestroyed(ServletContextEvent arg0) {}

}
