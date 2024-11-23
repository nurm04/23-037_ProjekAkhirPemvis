import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Tiket_Hotel {
    private int id_tiket_hotel;
    private Date tgl_cekin;
    private Date tgl_cekout;
    private int total_harga;
    private int id_user;
    private String status;
    
    public Tiket_Hotel(int id_tiket_hotel, Date tgl_cekin, Date tgl_cekout, int total_harga, int id_user, String status) {
        this.id_tiket_hotel = id_tiket_hotel;
        this.tgl_cekin = tgl_cekin;
        this.tgl_cekout = tgl_cekout;
        this.total_harga = total_harga;
        this.id_user = id_user;
        this.status = status;
    }
    
    public static boolean addTI(Date tgl_cekin, Date tgl_cekout, int total_harga, int id_user) {
        String sql = "INSERT INTO tiket_hotel (tgl_cekin, tgl_cekout, total_harga, id_user) VALUES (?, ?, ?, ?)";
        java.sql.Timestamp sqlTgl_cekin = new java.sql.Timestamp(tgl_cekin.getTime());
        java.sql.Timestamp sqlTgl_cekout = new java.sql.Timestamp(tgl_cekout.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setTimestamp(1, sqlTgl_cekin);
            ps.setTimestamp(2, sqlTgl_cekout);
            ps.setInt(3, total_harga);
            ps.setInt(4, id_user);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateTI(int id_tiket_hotel, Date tgl_cekin, Date tgl_cekout, int total_harga, int id_user) {
        String sql = "UPDATE tiket_hotel SET tgl_cekin = ?, tgl_cekout = ?, total_harga = ?, id_user = ? WHERE id_tiket_hotel = ?";
        java.sql.Timestamp sqlTgl_cekin = new java.sql.Timestamp(tgl_cekin.getTime());
        java.sql.Timestamp sqlTgl_cekout = new java.sql.Timestamp(tgl_cekout.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setTimestamp(1, sqlTgl_cekin);
            ps.setTimestamp(2, sqlTgl_cekout);
            ps.setInt(3, total_harga);
            ps.setInt(4, id_user);
            ps.setInt(5, id_tiket_hotel);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    public static boolean batalTI(int id_tiket_hotel) {
        String sql = "UPDATE tiket_hotel SET status = 'Batal' WHERE id_tiket_hotel = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tiket_hotel);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static List<Tiket_Hotel> getAll() {
        List<Tiket_Hotel> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_hotel";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Hotel(
                        rs.getInt(
                        "id_tiket_hotel"), 
                        rs.getTimestamp("tgl_cekin"), 
                        rs.getTimestamp("tgl_cekout"), 
                        rs.getInt("total_harga"), 
                        rs.getInt("id_user"),
                        rs.getString("status")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static List<Tiket_Hotel> getAllDataByUser(int user) {
        List<Tiket_Hotel> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_hotel WHERE id_user = ?";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setInt(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Hotel(
                        rs.getInt(
                        "id_tiket_hotel"), 
                        rs.getTimestamp("tgl_cekin"), 
                        rs.getTimestamp("tgl_cekout"), 
                        rs.getInt("total_harga"), 
                        rs.getInt("id_user"),
                        rs.getString("status")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static Tiket_Hotel getData(Date tgl_cekin, Date tgl_cekout, int total_harga, int id_user) {
        Tiket_Hotel tiket_hotel = null;
        String query = "SELECT * FROM tiket_hotel WHERE tgl_cekin = ? AND tgl_cekout = ? AND total_harga = ? AND id_user = ?";
        java.sql.Timestamp sqlTgl_cekin = new java.sql.Timestamp(tgl_cekin.getTime());
        java.sql.Timestamp sqlTgl_cekout = new java.sql.Timestamp(tgl_cekout.getTime());
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setTimestamp(1, sqlTgl_cekin);
            ps.setTimestamp(2, sqlTgl_cekout);
            ps.setInt(3, total_harga);
            ps.setInt(4, id_user);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tiket_hotel = new Tiket_Hotel(
                    rs.getInt(
                    "id_tiket_hotel"), 
                    rs.getTimestamp("tgl_cekin"), 
                    rs.getTimestamp("tgl_cekout"), 
                    rs.getInt("total_harga"), 
                    rs.getInt("id_user"),
                    rs.getString("status")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tiket_hotel;
    }
    public static Tiket_Hotel getById(int id) {
        Tiket_Hotel tiket_hotel = null;
        String query = "SELECT * FROM tiket_hotel WHERE id_tiket_hotel = ?";
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tiket_hotel = new Tiket_Hotel(
                    rs.getInt(
                    "id_tiket_hotel"), 
                    rs.getTimestamp("tgl_cekin"), 
                    rs.getTimestamp("tgl_cekout"), 
                    rs.getInt("total_harga"), 
                    rs.getInt("id_user"),
                    rs.getString("status")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tiket_hotel;
    }

    public static boolean deleteKI(int id_tiket_hotel) {
        String sql = "DELETE FROM tiket_hotel WHERE id_tiket_hotel = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_tiket_hotel);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public int getId_tiket_hotel() {
        return id_tiket_hotel;
    }
    public Date getTgl_cekin() {
        return tgl_cekin;
    }
    public Date getTgl_cekout() {
        return tgl_cekout;
    }
    public int getTotal_harga() {
        return total_harga;
    }
    public int getId_user() {
        return id_user;
    }
    public String getStatus() {
        return status;
    }
}
