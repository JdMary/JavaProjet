package core.services;

import core.models.User;
import core.utils.MyDataBase;

import java.sql.*;

public class UserService {

    public boolean createUser(User user)
    {
        String query = "INSERT INTO User " +
                "(email, roles, password, first_name, last_name,"+
                "cin, adress, phone, auth_code, reset_token, is_verified,"+
                " birth_date, joining_date, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            MyDataBase connexion=MyDataBase.getInstance();
            PreparedStatement pst=connexion.getConnection().prepareStatement(query);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getRoles());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getFirstName());
            pst.setString(5, user.getLastName());
            pst.setInt(6, user.getCin());
            pst.setString(7, user.getAddress());
            pst.setInt(8, user.getPhone());
            pst.setString(9, user.getAuthCode());
            pst.setString(10, user.getResetToken());
            pst.setBoolean(11, user.isVerified());
            pst.setDate(12, new java.sql.Date(user.getBirthDate().getTime())); // Assuming birthDate is a java.util.Date
            pst.setDate(13, new java.sql.Date(user.getJoiningDate().getTime())); // Assuming joiningDate is a java.util.Date
            pst.setString(14, user.getType());

            pst.executeUpdate();
            System.out.println("User added successfully");
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }


    }
    public User searchUser(String email)
    {
        String query="SELECT * FROM User WHERE email = ? ";

        try {
            MyDataBase connexion=MyDataBase.getInstance();
            PreparedStatement pst=connexion.getConnection().prepareStatement(query);
            pst.setString(1,email);
            ResultSet resultSet= pst.executeQuery();
            if(resultSet.next())
            {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRoles(resultSet.getString("roles"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setCin(resultSet.getInt("cin"));
                user.setAddress(resultSet.getString("adress"));
                user.setPhone(resultSet.getInt("phone"));
                user.setAuthCode(resultSet.getString("auth_code"));
                user.setAuthCode(resultSet.getString("reset_token"));
                user.setVerified(resultSet.getBoolean("is_verified"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setJoiningDate(resultSet.getDate("joining_date"));
                user.setType(resultSet.getString("type"));
                return user;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public User login(String email,String password)
    {
        User user=searchUser(email);
        if(user==null)
        {
            System.out.println("User not found");
            return null;
        }
        if(user.getPassword().matches(password))
        {
            return user;
        }
        return null;
    }
}
