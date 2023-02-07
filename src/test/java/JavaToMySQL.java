import java.sql.*;

public class JavaToMySQL {
    private static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String pass = "7VCovz0X3fyi";

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

  static {
        try {conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    static {
      try{
          stmt = conn.createStatement();
      } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
      }

    }




}

