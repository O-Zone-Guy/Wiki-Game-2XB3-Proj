/**
 @brief A module that provides an interface to the SQLite3 database.
 @file SQLHandler.java
 @author Mike Tee - teemh
 @date 2020-03-23
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

/**
 @brief A singleton class that gets page name, id, categories and neighbours from database.
 */
public class SQLHandler
{
    private static String path = "data/database.db3";
    private static Connection conn = null;
    private static Statement statement = null;

    /* Opens database connection. */
    private static void open() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /* Check if connection is open */
    private static boolean closed()
    {
        return conn == null && statement == null;
    }

    /* Get list of neighbors from database */
    private static String getList(int id) throws SQLException {
        if (closed()) open();
        ResultSet result = statement.executeQuery("SELECT list FROM edgelist WHERE id = " + id + ";");
        return result.getString("list");
    }

    /**
     @brief Get id of page from database.
     @param name: String Name of page.
     @return Returns id of page. Returns -1 if no page with name exists found.
     */
    public static int getPageId(String name) throws SQLException {
        if (closed()) open();
        ResultSet result = statement.executeQuery("SELECT id FROM page WHERE name LIKE '" + name +"' LIMIT 1;");
        return result.getInt("id");
    }

    /**
     @brief Get page name
     @param id: int Page id.
     @return Returns name of page or null if no page was found.
     */
    public static String getPageName(int id) throws SQLException {
        if (closed()) open();
        ResultSet result = statement.executeQuery("SELECT name FROM page WHERE id=" + id + ";");
        return result.getString("name");
    }

    /**
     @brief Create new Node with from page.
     @param id: int Page id.
     @return Returns new Node.
     */
    public static NodeT getNode(int id) throws SQLException {
        ArrayList<Integer> neighbours = new ArrayList<>();
        String list = getList(id);
        for (String n : list.split(" "))
        {
            neighbours.add(Integer.parseInt(n));
        }
        return new NodeT(id, getPageName(id), neighbours);
    }

    /**
     @brief Get page categories
     @param id: int Page id.
     @return Returns list of categories.
     */
    public static ArrayList<String> getCategories(int id) throws SQLException {
        if (closed()) open();
        ResultSet result = statement.executeQuery("SELECT c.name FROM category c " +
                                                    "INNER JOIN category_page cp ON cp.category_id = c.id " +
                                                    "INNER JOIN page p ON p.id = cp.page_id " +
                                                    "WHERE p.id = " + id + " " +
                                                    "GROUP BY c.id, c.name");
        ArrayList<String> cats = new ArrayList<>();
        while (result.next())
            cats.add(result.getString("name"));
        return cats;
    }

    /* Examples */
    public static void main(String[] args) throws SQLException {

        System.out.println(SQLHandler.getPageId("Antithesis"));
        System.out.println(SQLHandler.getPageName(64323));
        System.out.println(SQLHandler.getCategories(64323));

        NodeT node = SQLHandler.getNode(64323);
        System.out.println(node.getNeighbours());

    }
}

