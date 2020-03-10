import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite3
{
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
