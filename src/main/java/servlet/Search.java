package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ChinookDao;

@WebServlet("/searchResults")
public class Search extends HttpServlet {
	private ChinookDao db = new ChinookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String searchTerm = req.getParameter("s");
    	
    	req.setAttribute("artists", db.getArtistsByName(searchTerm));
    	req.setAttribute("albums", db.getAlbumsByTitle(searchTerm));

        req.getRequestDispatcher("/WEB-INF/search.jsp").forward(req, resp);
    }
    
}
