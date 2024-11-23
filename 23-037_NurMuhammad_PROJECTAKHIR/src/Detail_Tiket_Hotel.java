
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
public class Detail_Tiket_Hotel {
    private int id_DTH;
    private int id_tiket_hotel;
    private int id_hotel;
    private int jumlah;
    private int total_harga;
            
    public Detail_Tiket_Hotel(int id_DTH, int id_tiket_hotel, int id_hotel, int jumlah, int total_harga) {
        this.id_DTH = id_DTH;
        this.id_tiket_hotel = id_tiket_hotel;
        this.id_hotel = id_hotel;
        this.jumlah = jumlah;
        this.total_harga = total_harga;
    }
    
    public static List<Detail_Tiket_Hotel> getById_tiket_hotel(int id_tiket_hotel) {
        List<Detail_Tiket_Hotel> list = new ArrayList<>();
        String query = "SELECT * FROM detail_tiket_hotel WHERE id_tiket_hotel = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id_tiket_hotel);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Detail_Tiket_Hotel(
                    rs.getInt("id_DTH"),
                    rs.getInt("id_tiket_hotel"),
                    rs.getInt("id_hotel"),
                    rs.getInt("jumlah_kamar"),
                    rs.getInt("total_harga")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public static int cekKamar(Data_Hotel data_hotel, Date tgl_cekin, Date tgl_cekout) {
        String query = 
            "SELECT jumlah_kamar " +
            "FROM detail_tiket_hotel " +
            "LEFT JOIN tiket_hotel ON tiket_hotel.id_tiket_hotel = detail_tiket_hotel.id_tiket_hotel " +
            "LEFT JOIN data_hotel ON data_hotel.id_hotel = detail_tiket_hotel.id_hotel " +
            "WHERE detail_tiket_hotel.id_hotel = ? " +
            "AND (" +
            "    (tiket_hotel.tgl_cekout >= ? AND tiket_hotel.tgl_cekout <= ?)" +
            "    OR (tiket_hotel.tgl_cekin >= ? AND tiket_hotel.tgl_cekin <= ?)" +
            ")";
        int id_hotel = data_hotel.getId_hotel();
        java.sql.Timestamp sqlTgl_cekin = new java.sql.Timestamp(tgl_cekin.getTime());
        java.sql.Timestamp sqlTgl_cekout = new java.sql.Timestamp(tgl_cekout.getTime());

        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setInt(1, id_hotel);
            ps.setTimestamp(2, sqlTgl_cekin);
            ps.setTimestamp(3, sqlTgl_cekout);
            ps.setTimestamp(4, sqlTgl_cekin);
            ps.setTimestamp(5, sqlTgl_cekout);
            ResultSet rs = ps.executeQuery();
            
            int total = 0;
            while (rs.next()) {
                total += rs.getInt("jumlah_kamar");
            }
            rs.close();
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    public static int cekKamarUpdate(Data_Hotel data_hotel, Date tgl_cekin, Date tgl_cekout, int id_tiket_hotel) {
        String query = 
            "SELECT jumlah_kamar " +
            "FROM detail_tiket_hotel " +
            "LEFT JOIN tiket_hotel ON tiket_hotel.id_tiket_hotel = detail_tiket_hotel.id_tiket_hotel " +
            "LEFT JOIN data_hotel ON data_hotel.id_hotel = detail_tiket_hotel.id_hotel " +
            "WHERE detail_tiket_hotel.id_hotel = ? " +
            "AND (" +
            "    (tiket_hotel.tgl_cekout >= ? AND tiket_hotel.tgl_cekout <= ?)" +
            "    OR (tiket_hotel.tgl_cekin >= ? AND tiket_hotel.tgl_cekin <= ?)" +
            ")"
            + "AND detail_tiket_hotel.id_tiket_hotel != ?";
        int id_hotel = data_hotel.getId_hotel();
        java.sql.Timestamp sqlTgl_cekin = new java.sql.Timestamp(tgl_cekin.getTime());
        java.sql.Timestamp sqlTgl_cekout = new java.sql.Timestamp(tgl_cekout.getTime());

        try (
            Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setInt(1, id_hotel);
            ps.setTimestamp(2, sqlTgl_cekin);
            ps.setTimestamp(3, sqlTgl_cekout);
            ps.setTimestamp(4, sqlTgl_cekin);
            ps.setTimestamp(5, sqlTgl_cekout);
            ps.setInt(6, id_tiket_hotel);
            ResultSet rs = ps.executeQuery();
            
            int total = 0;
            while (rs.next()) {
                total += rs.getInt("jumlah_kamar");
            }
            rs.close();
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public static boolean addKI(int id_tiket_hotel, int id_hotel, int jumlah, int total_harga) {
        String sql = "INSERT INTO detail_tiket_hotel (id_tiket_hotel, id_hotel, jumlah_kamar, total_harga) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id_tiket_hotel);
            ps.setInt(2, id_hotel);
            ps.setInt(3, jumlah);
            ps.setInt(4, total_harga);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    public static boolean deleteKIById_tiket_hotel(int id_tiket_hotel) {
        String sql = "DELETE FROM detail_tiket_hotel WHERE id_tiket_hotel = ?";
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

    public int getId_DTH() {
        return id_DTH;
    }
    public int getId_tiket_hotel() {
        return id_tiket_hotel;
    }
    public int getId_hotel() {
        return id_hotel;
    }
    public int getJumlah() {
        return jumlah;
    }
    public int getTotal_harga() {
        return total_harga;
    }
}
