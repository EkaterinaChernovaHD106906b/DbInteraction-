
import org.apache.commons.dbutils.StatementConfiguration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestDB {
    @Test
    void testSelectQuery() throws SQLException, IOException {
        DbInteraction db = new DbInteraction();
      db.stubTest();
    }
}
