package hi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/myname")
public class Myname extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html; charset=UTF-8");
	String myname = req.getParameter("name");
	
	resp.getWriter().println("<h1>" + myname + "</h1>");
}
}
