package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import cas.xb3.proj.NodeT;
import cas.xb3.proj.alg.SQLHandler;

public class TestSQLHandler {

    @Test
    public void getName() throws SQLException{
        assertEquals("Chiasmal syndrome", SQLHandler.getPageName(0));
    }

    @Test
    public void getID() throws SQLException{
        assertEquals(0, SQLHandler.getPageId("Chiasmal syndrome"));
    }
    
    @Test
    public void getCategories() throws SQLException
    {
    	ArrayList<String> categories = SQLHandler.getCategories(4);
    	assertEquals(categories.get(0), "Mathematical_theorems");
    	assertEquals(categories.get(1), "Algebraic_geometry");
    }
    
    @Test
    public void getNode() throws SQLException
    {
    	NodeT node = SQLHandler.getNode(1);
    	assertTrue(node.getNeighbours().contains(2));
    	assertTrue(node.getNeighbours().contains(170193));
    	assertTrue(node.getNeighbours().contains(598775));
    	assertEquals(node.getPageName(), SQLHandler.getPageName(1));
    	assertEquals(node.getPageNumber(), 1);
    }
}

