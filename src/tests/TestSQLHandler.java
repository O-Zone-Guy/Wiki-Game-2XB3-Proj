package tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

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
}
