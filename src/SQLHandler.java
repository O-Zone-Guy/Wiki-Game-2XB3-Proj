import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

public class SQLHandler
{
    private Connection conn = null;
    private String path = "";
    private Statement statement = null;

    public SQLHandler(String db_path)
    {
        path = db_path;
    }

    public void open() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean close() {
        try {
            if(conn != null) conn.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    private ResultSet query(String s) throws SQLException {
        return statement.executeQuery(s);
    }

    public String getList(int id) throws SQLException {
        ResultSet result = query("SELECT list FROM edgelist WHERE id = " + id + ";");
        if (!result.isClosed()){
            return result.getString("list");
        }
        return null;
    }

    public int getPageId(String name) throws SQLException {
        ResultSet result = query("SELECT id FROM page WHERE name LIKE '" + name +"' LIMIT 1;");
        if (!result.isClosed()){
            return result.getInt("id");
        }
        return -1;
    }

    public String getName(int id) throws SQLException {
        ResultSet result = query("SELECT name FROM page WHERE id=" + id + ";");
        if (!result.isClosed()){
            return result.getString("name");
        }
        return null;
    }

    public int countEdges() throws SQLException {
        ResultSet result = query("SELECT COUNT(*) as count FROM edgelist;");
        if (!result.isClosed()){
            return result.getInt("count");
        }
        return -1;
    }

    public ArrayList<String> getCategories(int id) throws SQLException {
        ResultSet result = query("SELECT p.name FROM page p INNER JOIN category_page cp ON p.id = cp.page_id INNER JOIN category c ON c.id = cp.category_id WHERE c.id = " + id + " GROUP BY p.id, p.name");
        ArrayList<String> cats = new ArrayList<>();
        while (result.next())
            cats.add(result.getString("name"));
        return cats;
    }

    public ArrayList<Integer> bfs(int start, int end) throws SQLException{
        boolean[] visited = new boolean[countEdges()];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();

        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()) {
            if (visited[end]) {
                path.add(end);
                return path;
            }

            int next = queue.peek();
            queue.remove();

            String list = getList(next);
            path.add(next);
            for (String part : list.split(" ")) {
                int node = Integer.parseInt(part);

                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
        return null;
    }

    public ArrayList<Integer> dfs(int start, int end) throws SQLException {
        boolean[] visited = new boolean[countEdges()];
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> path = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()){
            int next = stack.pop();
            if (!visited[next]) {
                visited[next] = true;

                String list = getList(next);
                path.add(next);
                for (String part : list.split(" ")){
                    int node = Integer.parseInt(part);
                    if (node == end) {
                        path.add(end);
                        return path;
                    }
                    stack.push(node);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        SQLHandler sql = new SQLHandler("data/database.db3");
        sql.open();

        System.out.print(sql.getPageId("Guerrilla gig") + " to ");
        System.out.println(sql.getPageId("Antithesis"));

        ArrayList<Integer> path = sql.bfs(69,64323);

        for(int node : path)
        {
            System.out.println(sql.getName(node));
        }

        //System.out.println(sql.getName(1));
        //System.out.println(sql.getCategories(1));
    }
}
