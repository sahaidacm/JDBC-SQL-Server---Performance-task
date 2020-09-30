import java.sql.*;

public class ConnectDB {

    private static final String SELECT = "SELECT first_name, last_name FROM sales.staffs";
    private static final String UPDATE = "UPDATE production.products SET model_year = ? WHERE product_id = ? ";
    private static final String DELETE = "DELETE FROM sales.stores WHERE store_id = 4";
    private static final String INSERT = "INSERT INTO sales.stores(store_name, phone, email, street, city, state, zip_code) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";
    private static final String BULK_INSERT = "BULK INSERT production.brands FROM 'C:/Users/msahaidac/Downloads/my-file.csv' WITH (FORMAT='CSV');";


    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        connectDB.bulkInsert();
    }

    public void select() {

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(SELECT)) {

            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                System.out.println(first_name + " " + last_name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE)) {

            updateStatement.setString(1, "2020");
            updateStatement.setInt(2, 4);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        System.out.println(DELETE);

        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {

            int result = statement.executeUpdate(DELETE);
            System.out.println("Number of records affected : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT)) {
            insertStatement.setString(1, "Mark Store");
            insertStatement.setString(2, "456 789 777");
            insertStatement.setString(3, "mark@bikes.store");
            insertStatement.setString(4, "234 Oxford");
            insertStatement.setString(5, "New York");
            insertStatement.setString(6, "NY");
            insertStatement.setString(7, "34567");

            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsert() {

        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {

            int result = statement.executeUpdate(BULK_INSERT);
            System.out.println("Number of records affected : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
