package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/christmas")
public class ChristmasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // pass the time string to the JSP page as an attribute
    	LocalDate d = LocalDate.of(2020, 12, 25);
    	
        req.setAttribute("days", getDays(d));

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/daysUntilChristmas.jsp").forward(req, resp);
    }
    
    private long getDays(LocalDate d) {
    	LocalDate now = LocalDate.now();
    	return ChronoUnit.DAYS.between(now, d);
    }
}
