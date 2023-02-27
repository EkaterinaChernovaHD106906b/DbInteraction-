import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbInteraction {
    //  @SneakyThrows
    public void executeQuery() throws SQLException, IOException {
        var query = "SELECT city FROM sakila.city WHERE city_id=2;";
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/test/resources/db.properties");
        property.load(fis);
        try (var conn = DriverManager.getConnection(property.getProperty("db.host"), property.getProperty("db.user"), property.getProperty("db.password"));
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
    public void stubTest() throws SQLException, IOException {
        var countSQL = "SELECT COUNT(city) FROM sakila.city;";
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/test/resources/db.properties");
        property.load(fis);
        try (
                var conn = DriverManager.getConnection(property.getProperty("db.host"), property.getProperty("db.user"), property.getProperty("db.password"));
                var countStmt = conn.createStatement();
        ) {
            try (var rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    var count = rs.getInt(1);
                    int expected = 614;
                    int actual = count;
                    Assertions.assertEquals(expected, actual);
                }
            }
        }
    }

    public void insertInto() throws SQLException, IOException {
        var insertSQL = "INSERT INTO sakila.city(city, country_id) VALUES('Los Angeles', 103);";
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/test/resources/db.properties");
        property.load(fis);
        try (var conn = DriverManager.getConnection(property.getProperty("db.host"), property.getProperty("db.user"), property.getProperty("db.password"));
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












