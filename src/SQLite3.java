import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Set;

public class SQLite3
{

  Connection conn = null;
  String path = "";

  public SQLite3(String db_path)
  {
    path = db_path;
  }

  public boolean open()
  {
    try
    {
      conn = DriverManager.getConnection("jdbc:sqlite:" + path);
      return true;
    }
    catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
    return false;
  }

  public int getPageId(String name)
  {
    // select id from page where name like %name% 
    return 0;
  }

  public Set<Integer> getNeighbours(int start)
  {
    // set of end points from given start node
    return null;
  }

  public Set<String> getCategories(int page_id)
  {
    // return set of categories for page_id
    return null;
  }

  public boolean close()
  {
    try
    {
      if(conn != null) conn.close();
      return true;
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
    }
    return false;
  }

  // EXAMPLE
	public static void main(String[] args)
	{
		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:../data/database.db3");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

      System.out.println("Connected");
			
      ResultSet rs = statement.executeQuery("select p.id, p.name from page p inner join category_page cp on p.id = cp.page_id inner join category c on c.id = cp.category_id where c.id = 1 group by p.id, p.name");
		  
      while(rs.next())
			{
				// read the result set
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
}
