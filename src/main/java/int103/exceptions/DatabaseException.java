package int103.exceptions;

import java.sql.SQLException;

public class DatabaseException extends SQLException {
    public DatabaseException (String message) {
        super(message);
    }
}