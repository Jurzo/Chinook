package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.ChinookDao;
import model.Artist;

@WebServlet("")
public class ArtistList extends HttpServlet {
	private ChinookDao db = new ChinookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	req.setAttribute("artists", db.getAllArtists());

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nimi = req.getParameter("name");
    	Artist artisti = new Artist(1, nimi);
    	
    	String json = "{}";
    	if (db.addArtist(artisti)) {
    		json = new Gson().toJson(artisti);
    	}
        
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);
        resp.sendRedirect("/");
    }
    
}
