package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.JDBCShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/list")
public class ShoppingListServlet extends HttpServlet {
	private JDBCShoppingListItemDao db = new JDBCShoppingListItemDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	req.setAttribute("items", getItems());

        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String title = req.getParameter("title");
    	db.addItem(new ShoppingListItem(1, title));
    	resp.sendRedirect("/list");
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.valueOf(req.getParameter("id"));
        ShoppingListItem item = db.getItem(id);
        
        if (item != null) db.removeItem(item);
        
        String json = new Gson().toJson(item);
        
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);
    }
    
    private List<ShoppingListItem> getItems() {
    	return this.db.getAllItems();
    }
    
}
