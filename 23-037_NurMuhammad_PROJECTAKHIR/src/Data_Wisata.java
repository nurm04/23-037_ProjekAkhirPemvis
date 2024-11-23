
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
public class Data_Wisata {
    private int id_wisata;
    private String nama_wisata;
    private String kota;
    private int harga;
    
    public Data_Wisata(int id_wisata, String nama_wisata, String kota, int harga) {
        this.id_wisata = id_wisata;
        this.nama_wisata = nama_wisata;
        this.kota = kota;
        this.harga = harga;
    }
    
    public static boolean addTW(String wisata, String kota, int harga) {
        String sql = "INSERT INTO data_wisata VALUES (?, ?, ?, ?)";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setNull(1, 0);
            ps.setString(2, wisata);
            ps.setString(3, kota);
            ps.setInt(4, harga);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateTW(int id, String wisata, String kota, int harga) {
        String sql = "UPDATE data_wisata SET nama_wisata = ?, kota = ?, harga = ? WHERE id_wisata = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, wisata);
            ps.setString(2, kota);
            ps.setInt(3, harga);
            ps.setInt(4, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Edit Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTW(int id) {
        String sql = "DELETE FROM data_wisata WHERE id_wisata = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Hapus Data di:" + e.getMessage());
            return false;
        }
    }

    public static List<Data_Wisata> getAll() {
        List<Data_Wisata> data = new ArrayList<>();
        String query = "SELECT * FROM data_wisata ORDER BY id_wisata DESC";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data.add(new Data_Wisata(
                    rs.getInt("id_wisata"),
                    rs.getString("nama_wisata"),
                    rs.getString("kota"),
                    rs.getInt("harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data;
    }
    public static List<Data_Wisata> search(String cari) {
        List<Data_Wisata> data = new ArrayList<>();
        String query = "SELECT * FROM data_wisata WHERE nama_wisata LIKE ? OR kota LIKE ? OR harga LIKE ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            cari = "%" + cari + "%";
            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data.add(new Data_Wisata(
                    rs.getInt("id_wisata"),
                    rs.getString("nama_wisata"),
                    rs.getString("kota"),
                    rs.getInt("harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data;
    }
    public static Data_Wisata getById(int id_wisata) {
        Data_Wisata data = null;
        String query = "SELECT * FROM data_wisata WHERE id_wisata = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id_wisata);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                data = new Data_Wisata(
                    rs.getInt("id_wisata"),
                    rs.getString("nama_wisata"),
                    rs.getString("kota"),
                    rs.getInt("harga")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data;
    }
    public static List<Data_Wisata> getWisataByKota(String kota) {
        List<Data_Wisata> data_wisata_list = new ArrayList<>();
        String query = "SELECT * FROM data_wisata WHERE kota = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kota);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                data_wisata_list.add(new Data_Wisata(
                    rs.getInt("id_wisata"),
                    rs.getString("nama_wisata"),
                    rs.getString("kota"),
                    rs.getInt("harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_wisata_list;
    }
    public static Data_Wisata getHargaTiket(String nama_wisata) {
        Data_Wisata data_wisata = null;
        String query = "SELECT * FROM data_wisata WHERE nama_wisata = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nama_wisata);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                data_wisata = new Data_Wisata(
                    rs.getInt("id_wisata"),
                    rs.getString("nama_wisata"),
                    rs.getString("kota"),
                    rs.getInt("harga")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return data_wisata;
    }

    public int getId_wisata() {
        return id_wisata;
    }
    public String getNama_wisata() {
        return nama_wisata;
    }
    public String getKota() {
        return kota;
    }
    public int getHarga() {
        return harga;
    }
}
