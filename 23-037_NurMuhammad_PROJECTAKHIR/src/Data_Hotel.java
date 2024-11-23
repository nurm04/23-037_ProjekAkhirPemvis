
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
public class Data_Hotel {
    private int id_hotel;
    private String nama_hotel;
    private String kota;
    private String jenis_kamar;
    private int harga;
    private int jumlah_kamar_tersedia;
    
    public Data_Hotel(int id_hotel, String nama_hotel, String kota, String jenis_kamar, int harga, int jumlah_kamar_tersedia) {
        this.id_hotel = id_hotel;
        this.nama_hotel = nama_hotel;
        this.kota = kota;
        this.jenis_kamar = jenis_kamar;
        this.harga = harga;
        this.jumlah_kamar_tersedia = jumlah_kamar_tersedia;
    }
    
    public static boolean addTH(String nama_hotel, String kota, String jenis_kamar, int harga, int jumlah_kamar_tersedia) {
        String sql = "INSERT INTO data_hotel VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setNull(1, 0);
            ps.setString(2, nama_hotel);
            ps.setString(3, kota);
            ps.setString(4, jenis_kamar);
            ps.setInt(5, harga);
            ps.setInt(6, jumlah_kamar_tersedia);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateTH(int id_hotel, String nama_hotel, String kota, String jenis_kamar, int harga, int jumlah_kamar_tersedia) {
        String sql = "UPDATE data_hotel SET nama_hotel = ?, kota = ?, jenis_kamar = ?, harga = ?, jumlah_kamar_tersedia = ? WHERE id_hotel = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, nama_hotel);
            ps.setString(2, kota);
            ps.setString(3, jenis_kamar);
            ps.setInt(4, harga);
            ps.setInt(5, jumlah_kamar_tersedia);
            ps.setInt(6, id_hotel);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Edit Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTH(int id_hotel) {
        String sql = "DELETE FROM data_hotel WHERE id_hotel = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_hotel);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Hapus Data di:" + e.getMessage());
            return false;
        }
    }

    public static List<Data_Hotel> getAll() {
        List<Data_Hotel> data_hotel_list = new ArrayList<>();
        String query = "SELECT * FROM data_hotel ORDER BY id_hotel DESC";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data_hotel_list.add(new Data_Hotel(
                    rs.getInt("id_hotel"),
                    rs.getString("nama_hotel"),
                    rs.getString("kota"),
                    rs.getString("jenis_kamar"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah_kamar_tersedia")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_hotel_list;
    }
    public static List<Data_Hotel> search(String cari) {
        List<Data_Hotel> data_hotel_list = new ArrayList<>();
        String query = "SELECT * FROM data_hotel WHERE nama_hotel LIKE ? OR kota LIKE ? OR jenis_kamar LIKE ? OR harga LIKE ? OR jumlah_kamar_tersedia LIKE ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            cari = "%" + cari + "%";
            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ps.setString(4, cari);
            ps.setString(5, cari);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data_hotel_list.add(new Data_Hotel(
                    rs.getInt("id_hotel"),
                    rs.getString("nama_hotel"),
                    rs.getString("kota"),
                    rs.getString("jenis_kamar"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah_kamar_tersedia")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_hotel_list;
    }
    public static List<Data_Hotel> getHotelByKota(String kota) {
        List<Data_Hotel> data_hotel_list = new ArrayList<>();
        String query = "SELECT nama_hotel FROM data_hotel WHERE kota = ? GROUP BY nama_hotel ORDER BY nama_hotel";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kota);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data_hotel_list.add(new Data_Hotel(0,rs.getString("nama_hotel"),"","",0,0));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_hotel_list;
    }   
    public static Data_Hotel getHargaTiket(String nama_hotel, String jenis_kamar) {
        Data_Hotel data_hotel = null;
        String query = "SELECT * FROM data_hotel WHERE nama_hotel = ? AND jenis_kamar = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nama_hotel);
            ps.setString(2, jenis_kamar);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                data_hotel = new Data_Hotel(
                    rs.getInt("id_hotel"),
                    rs.getString("nama_hotel"),
                    rs.getString("kota"),
                    rs.getString("jenis_kamar"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah_kamar_tersedia")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_hotel;
    }  
    public static Data_Hotel getById(int id_hotel) {
        Data_Hotel data_hotel = null;
        String query = "SELECT * FROM data_hotel WHERE id_hotel = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id_hotel);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                data_hotel = new Data_Hotel(
                    rs.getInt("id_hotel"),
                    rs.getString("nama_hotel"),
                    rs.getString("kota"),
                    rs.getString("jenis_kamar"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah_kamar_tersedia")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_hotel;
    }

    public int getId_hotel() {
        return id_hotel;
    }
    public String getNama_hotel() {
        return nama_hotel;
    }
    public String getKota() {
        return kota;
    }
    public String getJenis_kamar() {
        return jenis_kamar;
    }
    public int getHarga() {
        return harga;
    }
    public int getJumlah_kamar_tersedia() {
        return jumlah_kamar_tersedia;
    }
}
