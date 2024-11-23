
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class User {
    private int id_user;
    private String nama;
    private String email;
    private String password;
    private String no_telp;
    private String role;
    
    public User(int id_user, String nama, String email, String password, String no_telp, String role) {
        this.id_user = id_user;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.no_telp = no_telp;
        this.role = role;
    }
    
    public static boolean addUser(String nama, String email, String password, String no_telp) {
        String sql = "INSERT INTO user (nama, email, password, no_telp, role) VALUES (?, ?, ?, ?,'user')";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, no_telp);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }

    public static List<User> getAll() {
        List<User> user = new ArrayList<>();
        String query = "SELECT * FROM user WHERE role = 'user'";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.add(new User(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_telp"),
                    rs.getString("role")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    public static List<User> search(String cari) {
        List<User> user = new ArrayList<>();
        String query = "SELECT * FROM user WHERE nama LIKE ? OR email LIKE ? OR no_telp LIKE ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            cari = "%" + cari + "%";
            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.add(new User(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_telp"),
                    rs.getString("role")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    public static User getById(int id) {
        User user = null;
        String query = "SELECT * FROM user WHERE id_user = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_telp"),
                    rs.getString("role")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    public static User getUserLogin(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("no_telp"),
                    rs.getString("role")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }

    public int getId_user() {
        return id_user;
    }
    public String getNama() {
        return nama;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getNo_telp() {
        return no_telp;
    }
    public String getRole() {
        return role;
    }
}
