import com.google.protobuf.Enum;

import javax.xml.crypto.Data;
import java.net.ConnectException;
import java.sql.*;

public class TransactionDao {

    public void add(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO transaction(type, description, amount, date) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setString(3, String.valueOf(transaction.getAmount()));
            preparedStatement.setString(4, String.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nie dodano transakcji do bazy danych" + e.getMessage());
        }

        closeConnection(connection);
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/tranaction?serverTimezone=UTC$characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Transaction findByType(Type type) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        try {
            String sql = "SELECT * FROM transaction WHERE type = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                Date data = resultSet.getDate("date");

                Transaction transaction = new Transaction(id, type, description, amount, data);
                return transaction;
            }
        } catch (SQLException e) {
            System.out.println("Nie znaleziono transakcji" + e.getMessage());
        }

        closeConnection(connection);
        return  null;

    }

    public void update(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE transaction SET type= ?, description= ?, amount= ?, date= ? WHERE id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setString(3, String.valueOf(transaction.getAmount()));
            preparedStatement.setString(4, String.valueOf(transaction.getDate()));
            preparedStatement.setString(5, String.valueOf(transaction.getId()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nie dodano transakcji do bazy danych" + e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteById(int id) {

        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM transaction WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nie nie została usunięta" + e.getMessage());
        }

        closeConnection(connection);
    }

}

