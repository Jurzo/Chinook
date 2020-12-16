package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ChinookDao;

@WebServlet("/albums")
public class Albums extends HttpServlet {
	private ChinookDao db = new ChinookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int artistId = Integer.valueOf(req.getParameter("ArtistId"));
    	
    	req.setAttribute("artist", db.getArtist(artistId));
    	req.setAttribute("albums", db.getAlbumsByArtist(artistId));

        req.getRequestDispatcher("/WEB-INF/albums.jsp").forward(req, resp);
    }
    
}
