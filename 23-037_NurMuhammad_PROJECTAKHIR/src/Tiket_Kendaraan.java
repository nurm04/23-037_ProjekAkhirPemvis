
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
public class Tiket_Kendaraan {
    private int id_tiket_kendaraan;
    private int id_tarif;
    private Date waktu;
    private int jumlah;
    private int total_harga;
    private int id_user;
    private String status;
    
    public Tiket_Kendaraan(int id_tiket_kendaraan, int id_tarif, Date waktu, int jumlah, int total_harga, int id_user, String status) {
        this.id_tiket_kendaraan = id_tiket_kendaraan;
        this.id_tarif = id_tarif;
        this.waktu = waktu;
        this.jumlah = jumlah;
        this.total_harga = total_harga;
        this.id_user = id_user;
        this.status = status;
    }
    
    public static boolean addTK( int id_tarif, Date waktu, int jumlah, int total_harga, int id_user) {
        String sql = "INSERT INTO tiket_kendaraan (id_tarif, waktu, jumlah, total_harga, id_user) VALUES (?, ?, ?, ?, ?)";
        java.sql.Timestamp sqlWaktu = new java.sql.Timestamp(waktu.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tarif);
            ps.setTimestamp(2, sqlWaktu);
            ps.setInt(3, jumlah);
            ps.setInt(4, total_harga);
            ps.setInt(5, id_user);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean batalTK(int id_tiket_kendaraan) {
        String sql = "UPDATE tiket_kendaraan SET status = 'Batal' WHERE id_tiket_kendaraan = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tiket_kendaraan);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateTK(int id_tiket_kendaraan, int id_tarif, Date waktu, int jumlah, int total_harga, int id_user) {
        String sql = "UPDATE tiket_kendaraan SET id_tarif = ?, waktu = ?, jumlah = ?, total_harga = ?, id_user = ? WHERE id_tiket_kendaraan = ?";
        java.sql.Timestamp sqlWaktu = new java.sql.Timestamp(waktu.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tarif);
            ps.setTimestamp(2, sqlWaktu);
            ps.setInt(3, jumlah);
            ps.setInt(4, total_harga);
            ps.setInt(5, id_user);
            ps.setInt(6, id_tiket_kendaraan);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }

    public static Tiket_Kendaraan getById(int id) {
        Tiket_Kendaraan TK = null;
        String query = "SELECT * FROM tiket_kendaraan WHERE id_tiket_kendaraan = ?";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TK = new Tiket_Kendaraan(
                    rs.getInt("id_tiket_kendaraan"), 
                    rs.getInt("id_tarif"), 
                    rs.getTimestamp("waktu"), 
                    rs.getInt("jumlah"), 
                    rs.getInt("total_harga"), 
                    rs.getInt("id_user"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return TK;
    }
    
    public static List<Tiket_Kendaraan> getAll() {
        List<Tiket_Kendaraan> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_kendaraan";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Kendaraan(
                        rs.getInt("id_tiket_kendaraan"), 
                        rs.getInt("id_tarif"), 
                        rs.getTimestamp("waktu"), 
                        rs.getInt("jumlah"), 
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
    public static List<Tiket_Kendaraan> getAllDataByUser(int user) {
        List<Tiket_Kendaraan> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_kendaraan WHERE id_user = ?";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setInt(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Kendaraan(
                        rs.getInt("id_tiket_kendaraan"), 
                        rs.getInt("id_tarif"), 
                        rs.getTimestamp("waktu"), 
                        rs.getInt("jumlah"), 
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
    
    public int getId_tiket_kendaraan() {
        return id_tiket_kendaraan;
    }
    public int getId_tarif() {
        return id_tarif;
    }
    public Date getWaktu() {
        return waktu;
    }
    public int getJumlah() {
        return jumlah;
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
