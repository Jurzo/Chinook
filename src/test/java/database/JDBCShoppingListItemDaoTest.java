package database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ShoppingListItem;

class JDBCShoppingListItemDaoTest {

    private static final String TEST_URL = System.getenv("TEST");
    private JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao(TEST_URL);
    private ShoppingListItem milk = new ShoppingListItem(1, "Milk");
    private ShoppingListItem eggs = new ShoppingListItem(2, "Eggs");
    private ShoppingListItem bread = new ShoppingListItem(3, "Bread");

    /**
     * This method clears the test database and inserts two rows directly in the
     * database before each test with a delete statement.
     * 
     * This way every time the tests are executed they have exactly the same data to
     * work with.
     * 
     * !! Make sure to always use a different database environment variable for each
     * execution environment to prevent data loss or corruption !!
     */
    @BeforeEach
    public void setUp() throws Exception {
    	dao.getAllItems().forEach(item -> dao.removeItem(item));
        dao.addItem(milk);
        dao.addItem(eggs);
        dao.addItem(bread);
    }
    
    @Test
    public void testItemAdded1() {
    	assertTrue(dao.getItem(milk.getId()).equals(milk));
    }
    
    @Test
    public void testItemAdded2() {
    	assertTrue(dao.getItem(eggs.getId()).equals(eggs));
    }
    
    @Test
    public void testItemAdded3() {
    	assertTrue(dao.getItem(bread.getId()).equals(bread));
    }
    
    @Test
    public void testMultipleItemsAdded() {
    	dao.getAllItems().forEach(item -> dao.removeItem(item));
    	dao.addItem(bread);
    	dao.addItem(milk);
    	assertTrue(dao.getItem(bread.getId()).equals(bread) && dao.getItem(milk.getId()).equals(milk));
    }
    
    @Test
    public void removeItem() {
    	dao.getAllItems().forEach(item -> dao.removeItem(item));
    	dao.addItem(bread);
    	assertTrue(dao.getItem(bread.getId()).equals(bread));
    	dao.removeItem(bread);
    	assertTrue(dao.getItem(bread.getId()) == null);
    }
    
    @Test
    public void listItems() {
    	dao.getAllItems().forEach(item -> dao.removeItem(item));
    	ShoppingListItem[] items = {milk, eggs, bread};
    	
    	for (ShoppingListItem item : items) {
    		dao.addItem(item);
    	}
    	
    	for (ShoppingListItem item : items) {
    		assertTrue(dao.getItem(item.getId()).equals(item));
    	}
    }
}
