package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ShoppingListItem;


public class JDBCShoppingListItemDao implements ShoppingListItemDao {
	
	private static final String JDBC_URL = System.getenv("JDBC_URL");
	//private static final String JDBC_URL = "jdbc:sqlite:shoppingList.sqlite";
	private String URL;
	
	public JDBCShoppingListItemDao() {
		this(JDBC_URL);
	}
	
	public JDBCShoppingListItemDao(String URL) {
		this.URL = URL;
	}

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(this.URL);
    }

    @Override
    public List<ShoppingListItem> getAllItems() {
    	List<ShoppingListItem> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from ShoppingListItem");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("id");
				String nimike = tulokset.getString("Title");
				lista.add(new ShoppingListItem(id, nimike));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    @Override
    public ShoppingListItem getItem(long id) {
		ShoppingListItem tuote = null;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from ShoppingListItem where id = ?");
			kysely.setLong(1, id);
			ResultSet tulos = kysely.executeQuery();
			
			if (tulos.next()) {
				tuote = new ShoppingListItem(id, tulos.getString("title"));
			}
			tulos.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tuote;
    }
    
    public ShoppingListItem getItem(String title) {
		ShoppingListItem tuote = null;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from ShoppingListItem where title = ?");
			kysely.setString(1, title);
			ResultSet tulos = kysely.executeQuery();
			
			if (tulos.next()) {
				tuote = new ShoppingListItem(tulos.getLong("id"), title);
			}
			tulos.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tuote;
    }

    @Override
    public boolean addItem(ShoppingListItem newItem) {
    	int autoId = -1;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("insert into ShoppingListItem (title) Values (?)",
					Statement.RETURN_GENERATED_KEYS);
			kysely.setString(1, newItem.getTitle());
			
			// Asetetaan tuotteelle tietokannassa asetettu ID
			if(kysely.executeUpdate() == 1) {
				ResultSet tulos = kysely.getGeneratedKeys();
				tulos.next();
				autoId = tulos.getInt(1);
				newItem.setId(autoId);
				tulos.close();
			}

			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (autoId == -1) return false;
        return true;
    }

    @Override
    public boolean removeItem(ShoppingListItem item) {
    	int tulos = 0;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("delete from ShoppingListItem where id = ?");
			kysely.setLong(1, item.getId());
			tulos = kysely.executeUpdate();

			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (tulos == 1) return true;
        return false;
    }

}