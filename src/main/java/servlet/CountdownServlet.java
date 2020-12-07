package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/daysUntil")
public class CountdownServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // pass the time string to the JSP page as an attribute
    	int year = Integer.valueOf(req.getParameter("year"));
    	int month = Integer.valueOf(req.getParameter("month"));
    	int day = Integer.valueOf(req.getParameter("day"));
    	
    	LocalDate d = LocalDate.of(year, month, day);
    	
        req.setAttribute("days", getDays(d));
        req.setAttribute("date", d.toString());

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/daysUntil.jsp").forward(req, resp);
    }
    
    private long getDays(LocalDate d) {
    	LocalDate now = LocalDate.now();
    	return ChronoUnit.DAYS.between(now, d);
    }
}
