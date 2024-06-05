package int103.Connector;

public class ConnectionDB {
    // Database URL
    public String jdbcUrl ;
    // Database credentials
    public String username;
    public String password;

    public ConnectionDB(){
        this.jdbcUrl = "jdbc:mysql://localhost:3306/int103";
        this.username = "root";
        this.password = "Kira5005.";
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}