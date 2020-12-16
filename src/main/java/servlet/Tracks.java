package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ChinookDao;

@WebServlet("/tracks")
public class Tracks extends HttpServlet {
	private ChinookDao db = new ChinookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int AlbumId = Integer.valueOf(req.getParameter("AlbumId"));
    	
    	req.setAttribute("album", db.getAlbum(AlbumId));
    	req.setAttribute("tracks", db.getTracksByAlbum(AlbumId));

        req.getRequestDispatcher("/WEB-INF/tracks.jsp").forward(req, resp);
    }
    
}
