package plus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/plus")
public class Plus extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int a = (int)req.getAttribute("a");
		int b = (int)req.getAttribute("b");
		
		int result = a+b;
		
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("result.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
		int a = Integer.parseInt(req.getParameter("a")); 
		int b = Integer.parseInt(req.getParameter("b")); 
		
		int result = a+b;
		
		resp.getWriter().println("<h1> 결과는 : " +result +"</h1>" );
		
		
	}
	
}
