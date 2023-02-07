import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class DbInteraction {
    //  @SneakyThrows
    public void executeQuery() throws SQLException {
        var query = "SELECT city FROM sakila.city WHERE city_id=2;";
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "7VCovz0X3fyi");
             var selectCity = conn.prepareStatement(query);) {
            try
                    (var rs = selectCity.executeQuery()) {
                while (rs.next()) {
                    var city = rs.getString("city");
                    String expected = "Abha";
                    String actual = city;
                    Assertions.assertEquals(expected, actual);
                }
            }
        }
    }

    @SneakyThrows
    public void stubTest() throws SQLException {
        var countSQL = "SELECT COUNT(city) FROM sakila.city;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "7VCovz0X3fyi");
                var countStmt = conn.createStatement();
        ) {
            try (var rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    var count = rs.getInt(1);
                    int expected = 600;
                    int actual = count;
                    Assertions.assertEquals(expected, actual);
                }
            }
        }
    }

    public void insertInto() throws SQLException {
        var insertSQL = "INSERT INTO sakila.city(city, country_id) VALUES('Los Angeles', 103);";
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "7VCovz0X3fyi");
             var stmt = conn.createStatement();) {
            stmt.executeUpdate(insertSQL);
            var rs = stmt.executeQuery("SELECT country_id FROM sakila.city WHERE city = 'Los Angeles'");
            if (rs.next()) {
                var id = rs.getInt(1);
                int expected = 103;
                int actual = id;
                Assertions.assertEquals(expected, actual);
            }
        }


    }
}












