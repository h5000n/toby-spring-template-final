package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDao {
    private final ConnectionMaker cm;

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void add(User user) throws SQLException {
        String sql = "INSERT INTO users(id, name, password) VALUES(?, ?, ?)";

        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = cm.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setString(2, user.getId());
            ps.setString(3, user.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally {
            close(c, ps, null);
        }
    }

    public User findById(String id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = cm.getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(3));
            } else {
                throw new NoSuchElementException("not found " + id);
            }
        }catch (SQLException e) {
            throw e;
        }finally {
            close(c, ps,rs);
        }
    }
    public List<User> findAll() throws SQLException{
        String sql = "SELECT * FROM users";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            c = cm.getConnection();
            ps = c.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()){
                users.add(new User(rs.getString(1),rs.getString(2),rs.getString(2)));
            }
            return users;
        } catch (SQLException e) {
            throw e;
        }finally {
            close(c, ps, rs);
        }
    }
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM users";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = cm.getConnection();
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){
            throw e;
        } finally{
            close(c, ps, null);
        }
    }

    private void close(Connection c, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}//