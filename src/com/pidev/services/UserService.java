package com.pidev.services;

import com.pidev.entities.User;
import com.pidev.utils.DatabaseConnection;

import de.mkammerer.argon2.Argon2Factory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private static UserService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public UserService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public User checkUser(String username, String password) {

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE `username` LIKE ?");

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("bio"),
                        resultSet.getString("country"),
                        resultSet.getString("profile_image"),
                        resultSet.getString("cover_image"),
                        resultSet.getString("password"),
                        resultSet.getString("roles"),
                        resultSet.getString("api_token"),
                        resultSet.getBoolean("is_verified")
                        
                );

                if (checkPassword(password, user.getPassword())) {
                    return user;
                }

            }
        } catch (SQLException e) {
            System.out.println("Aucun email : " + e.getMessage());
        }

        return null;
    }

    private Boolean checkPassword(String password, String passwordFromDatabase) {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id).verify(
                passwordFromDatabase,
                password.toCharArray()
        );
    }

    public String encodePassword(String password) {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id).hash(4, 65536, 1, password.toCharArray());
    }
    
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        try {
             preparedStatement = connection.prepareStatement("SELECT * FROM `user`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listUser.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("bio"),
                        resultSet.getString("country"),
                        resultSet.getString("profile_image"),
                        resultSet.getString("cover_image"),
                        resultSet.getString("password"),
                        resultSet.getString("roles"),
                        resultSet.getString("api_token"),
                        resultSet.getBoolean("is_verified")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) user : " + exception.getMessage());
        }
        return listUser;
    }
    
    
    public boolean checkExist(User user) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `username` = ?");

            preparedStatement.setString(1, user.getUsername());
        
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (checkExist) : " + exception.getMessage());
        }
        return false;
    }
    
    public boolean add(User user) {
        
        if (checkExist(user)) {
            return false;
        }
    
        String request = "INSERT INTO `user`(`username`, `first_name`, `last_name`, `email`, `gender`, `age`, `bio`, `country`, `profile_image`, `cover_image`, `password`, `roles`, `api_token`, `is_verified`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
             preparedStatement.setString(1, user.getUsername());
             preparedStatement.setString(2, user.getFirstName());
             preparedStatement.setString(3, user.getLastName());
             preparedStatement.setString(4, user.getEmail());
             preparedStatement.setString(5, user.getGender());
            preparedStatement.setInt(6, user.getAge());
             preparedStatement.setString(7, user.getBio());
             preparedStatement.setString(8, user.getCountry());
             preparedStatement.setString(9, user.getProfileImage());
             preparedStatement.setString(10, user.getCoverImage());
            preparedStatement.setString(11, encodePassword(user.getPassword()));
             preparedStatement.setString(12, user.getRoles());
             preparedStatement.setString(13, user.getApiToken());
            preparedStatement.setBoolean(14, user.getIsVerified());
            
            preparedStatement.executeUpdate();
            System.out.println("User added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) user : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(User user) {
        
        String request = "UPDATE `user` SET `username` = ?, `first_name` = ?, `last_name` = ?, `email` = ?, `gender` = ?, `age` = ?, `bio` = ?, `country` = ?, `profile_image` = ?, `cover_image` = ?, `roles` = ?, `api_token` = ?, `is_verified` = ? WHERE `id`=" + user.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getBio());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getProfileImage());
            preparedStatement.setString(10, user.getCoverImage());
            
            preparedStatement.setString(11, user.getRoles());
            preparedStatement.setString(12, user.getApiToken());
            preparedStatement.setBoolean(13, user.getIsVerified());
            
            preparedStatement.executeUpdate();
            System.out.println("User edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) user : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("User deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) user : " + exception.getMessage());
        }
        return false;
    }
    public void modifierClientReset(String email,String code) {

        Statement stm;
        try {
            stm = connection.createStatement();

            String query = "UPDATE user SET password='" + code +"' WHERE email='" + email + "'";
            System.out.println(query);
            stm.executeUpdate(query);
            System.out.println(query);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
