package int103.exceptions;

import java.sql.SQLException;

public class DatabaseException extends SQLException {
    public DatabaseException (String message, SQLException e) {
        super(message + e.getMessage());
    }
}