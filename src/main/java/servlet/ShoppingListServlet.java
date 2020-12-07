package servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/list")
public class ShoppingListServlet extends HttpServlet {
	private JDBCShoppingListItemDao db = new JDBCShoppingListItemDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	req.setAttribute("list", getItems());

        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String title = req.getParameter("title");
    	db.addItem(new ShoppingListItem(1, title));
    	resp.sendRedirect("/list");
    }
    
    private List<String> getItems() {
    	return this.db.getAllItems().stream().map(item -> item.getTitle()).collect(Collectors.toList());
    }
    
}
