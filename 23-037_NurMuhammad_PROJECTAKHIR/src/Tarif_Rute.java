
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
public class Tarif_Rute {
    private int id_tarif;
    private String tempat_asal;
    private String tempat_tujuan;
    private String jenis_kendaraan;
    private int harga;
    
    public Tarif_Rute(int id_tarif, String tempat_asal, String tempat_tujuan, String jenis_kendaraan, int harga) {
        this.id_tarif = id_tarif;
        this.tempat_asal = tempat_asal;
        this.tempat_tujuan = tempat_tujuan;
        this.jenis_kendaraan = jenis_kendaraan;
        this.harga = harga;
    }
    
    public static boolean addTK(String asal, String tujuan, String jenis, int harga) {
        String sql = "INSERT INTO tarif_rute VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setNull(1, 0);
            ps.setString(2, asal);
            ps.setString(3, tujuan);
            ps.setString(4, jenis);
            ps.setInt(5, harga);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean updateTK(int id, String asal, String tujuan, String jenis, int harga) {
        String sql = "UPDATE tarif_rute SET tempat_asal = ?, tempat_tujuan = ?, jenis_kendaraan = ?, harga = ? WHERE id_tarif = ?";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, asal);
            ps.setString(2, tujuan);
            ps.setString(3, jenis);
            ps.setInt(4, harga);
            ps.setInt(5, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Edit Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTK(int id) {
        String sql = "DELETE FROM tarif_rute WHERE id_tarif = ?";
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

    public static List<Tarif_Rute> getAll() {
        List<Tarif_Rute> list = new ArrayList<>();
        String query = "SELECT * FROM tarif_rute ORDER BY id_tarif DESC";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Tarif_Rute(
                    rs.getInt("id_tarif"),
                    rs.getString("tempat_asal"),
                    rs.getString("tempat_tujuan"),
                    rs.getString("jenis_kendaraan"),
                    rs.getInt("harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static List<Tarif_Rute> getAllTempatAsal() {
        List<Tarif_Rute> list = new ArrayList<>();
        String query = "SELECT tempat_asal FROM tarif_rute GROUP BY tempat_asal ORDER BY tempat_asal";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Tarif_Rute(
                    0,
                    rs.getString("tempat_asal"),
                    "",
                    "",
                    0
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static List<Tarif_Rute> getAllTempatTujuan() {
        List<Tarif_Rute> list = new ArrayList<>();
        String query = "SELECT tempat_tujuan FROM tarif_rute GROUP BY tempat_tujuan ORDER BY tempat_tujuan";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Tarif_Rute(
                    0,
                    "",
                    rs.getString("tempat_tujuan"),
                    "",
                    0
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static List<Tarif_Rute> getAllJenisKendaraan() {
        List<Tarif_Rute> list = new ArrayList<>();
        String query = "SELECT jenis_kendaraan FROM tarif_rute GROUP BY jenis_kendaraan ORDER BY jenis_kendaraan";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Tarif_Rute(
                    0,
                    "",
                    "",
                    rs.getString("jenis_kendaraan"),
                    0
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static List<Tarif_Rute> search(String tempat_asal, String tempat_tujuan, String jenis_kendaraan) {
        List<Tarif_Rute> tarif_rute = new ArrayList<>();
        String query = "SELECT * FROM tarif_rute WHERE ";
        if (!tempat_asal.equals("-")) {
            query += "tempat_asal = ? ";
        }
        if (!tempat_tujuan.equals("-")) {
            if (!tempat_asal.equals("-")) {
                query += "AND tempat_tujuan = ? ";
            } else {
                query += "tempat_tujuan = ? ";
            }
        }
        if (!jenis_kendaraan.equals("-")) {
            if (!tempat_asal.equals("-") || !tempat_tujuan.equals("-")) {
                query += "AND jenis_kendaraan = ? ";
            } else {
                query += "jenis_kendaraan = ? ";
            }
        }
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            if (!tempat_asal.equals("-")) {
                ps.setString(1, tempat_asal);
            }
            if (!tempat_tujuan.equals("-")) {
                if (!tempat_asal.equals("-")) {
                    ps.setString(2, tempat_tujuan);
                } else {
                    ps.setString(1, tempat_tujuan);
                }
            }
            if (!jenis_kendaraan.equals("-")) {
                if (!tempat_asal.equals("-")) {
                    if (!tempat_tujuan.equals("-")) {
                        ps.setString(3, jenis_kendaraan);
                    } else {
                        ps.setString(2, jenis_kendaraan);
                    }
                } else {
                    if (!tempat_tujuan.equals("-")) {
                        ps.setString(2, jenis_kendaraan);
                    } else {
                        ps.setString(1, jenis_kendaraan);
                    }
                }
            }
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                tarif_rute.add(new Tarif_Rute(
                    rs.getInt("id_tarif"),
                    rs.getString("tempat_asal"),
                    rs.getString("tempat_tujuan"),
                    rs.getString("jenis_kendaraan"),
                    rs.getInt("harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarif_rute;
    }
    public static Tarif_Rute getHargaTiket(String tempat_asal, String tempat_tujuan, String jenis_kendaraan) {
        Tarif_Rute tarif_rute = null;
        String query = "SELECT * FROM tarif_rute WHERE tempat_asal = ? AND tempat_tujuan = ? AND jenis_kendaraan = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, tempat_asal);
            ps.setString(2, tempat_tujuan);
            ps.setString(3, jenis_kendaraan);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tarif_rute = new Tarif_Rute(
                    rs.getInt("id_tarif"),
                    rs.getString("tempat_asal"),
                    rs.getString("tempat_tujuan"),
                    rs.getString("jenis_kendaraan"),
                    rs.getInt("harga")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarif_rute;
    }
    public static Tarif_Rute getById(int id_tarif) {
        Tarif_Rute tarif_rute = null;
        String query = "SELECT * FROM tarif_rute WHERE id_tarif = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id_tarif);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tarif_rute = new Tarif_Rute(
                    rs.getInt("id_tarif"),
                    rs.getString("tempat_asal"),
                    rs.getString("tempat_tujuan"),
                    rs.getString("jenis_kendaraan"),
                    rs.getInt("harga")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarif_rute;
    }
    
    public int getId_tarif() {
        return id_tarif;
    }
    public String getTempat_asal() {
        return tempat_asal;
    }
    public String getTempat_tujuan() {
        return tempat_tujuan;
    }
    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }
    public int getHarga() {
        return harga;
    }
}
