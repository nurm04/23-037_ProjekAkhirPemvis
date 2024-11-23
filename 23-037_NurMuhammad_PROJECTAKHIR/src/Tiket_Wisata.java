
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
public class Tiket_Wisata {
    private int id_tiket_wisata;
    private int id_wisata;
    private Date waktu;
    private int jumlah;
    private int total_harga;
    private int id_user;
    private String status;
    
    public Tiket_Wisata(int id_tiket_wisata, int id_wisata, Date waktu, int jumlah, int total_harga, int id_user, String status) {
        this.id_tiket_wisata = id_tiket_wisata;
        this.id_wisata = id_wisata;
        this.waktu = waktu;
        this.jumlah = jumlah;
        this.total_harga = total_harga;
        this.id_user = id_user;
        this.status = status;
    }
    
    public static boolean addTW( int id_wisata, Date waktu, int jumlah, int total_harga, int id_user) {
        String sql = "INSERT INTO tiket_wisata (id_wisata, waktu, jumlah_tiket, total_harga, id_user) VALUES (?, ?, ?, ?, ?)";
        java.sql.Timestamp sqlWaktu = new java.sql.Timestamp(waktu.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_wisata);
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
    
    public static boolean updateTW(int id_tiket_wisata, int id_wisata, Date waktu, int jumlah, int total_harga, int id_user) {
        String sql = "UPDATE tiket_wisata SET id_wisata = ?, waktu = ?, jumlah_tiket = ?, total_harga = ?, id_user = ? WHERE id_tiket_wisata = ?";
        java.sql.Timestamp sqlWaktu = new java.sql.Timestamp(waktu.getTime());
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_wisata);
            ps.setTimestamp(2, sqlWaktu);
            ps.setInt(3, jumlah);
            ps.setInt(4, total_harga);
            ps.setInt(5, id_user);
            ps.setInt(6, id_tiket_wisata);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }   
    public static boolean batalTW(int id_tiket_wisata) {
        String sql = "UPDATE tiket_wisata SET status = 'Batal' WHERE id_tiket_wisata = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tiket_wisata);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }

    public static Tiket_Wisata getById(int id) {
        Tiket_Wisata tw = null;
        String query = "SELECT * FROM tiket_wisata WHERE id_tiket_wisata = ?";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tw = new Tiket_Wisata(
                    rs.getInt("id_tiket_wisata"), 
                    rs.getInt("id_wisata"), 
                    rs.getTimestamp("waktu"), 
                    rs.getInt("jumlah_tiket"), 
                    rs.getInt("total_harga"), 
                    rs.getInt("id_user"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tw;
    }
    public static List<Tiket_Wisata> getAll() {
        List<Tiket_Wisata> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_wisata";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Wisata(
                        rs.getInt("id_tiket_wisata"), 
                        rs.getInt("id_wisata"), 
                        rs.getTimestamp("waktu"), 
                        rs.getInt("jumlah_tiket"), 
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
    public static List<Tiket_Wisata> getAllDataByUser(int user) {
        List<Tiket_Wisata> list = new ArrayList<>();
        String query = "SELECT * FROM tiket_wisata WHERE id_user = ?";
        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setInt(1, user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(
                    new Tiket_Wisata(
                        rs.getInt("id_tiket_wisata"), 
                        rs.getInt("id_wisata"), 
                        rs.getTimestamp("waktu"), 
                        rs.getInt("jumlah_tiket"), 
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
    
    public int getId_tiket_wisata() {
        return id_tiket_wisata;
    }
    public int getid_wisata() {
        return id_wisata;
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
