package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDBByMySQL;

public class DAO_TaiKhoan {
	public static String authenticateUser(String username, String password) {
        try {
            ConnectDBByMySQL.getInstance().connect();
            Connection con = ConnectDBByMySQL.getConnection();
            String query = "SELECT * FROM TaiKhoan WHERE taiKhoan=? AND matKhau=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("loaiTaiKhoan");
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
