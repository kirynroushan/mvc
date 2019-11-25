/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;
import mvc.controller.interMaulana;
import mvc.model.Maulana;
import mvc.koneksi.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author maulana
 */
public class controllerMaulana implements interMaulana { 
    public Maulana insert(Maulana o) throws SQLException {//untuk insert ke database
        java.sql.Connection conn = new DBConnection().connect();
        PreparedStatement st = conn.prepareStatement("INSERT INTO maulana (nim, nama, tanggallahir, jeniskelamin, jurusan, profilegambar, nilaiuts, nilaiuas, agama) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        st.setString(1, o.getNIM());
        st.setString(2, o.getNama());
        st.setString(3, o.getTanggalLahir());
        st.setString(4, o.getJenisKelamin());
        st.setString(5, o.getJurusan());
        st.setString(6, o.getProfileGambar());
        st.setString(7, String.valueOf((float) o.getNilaiUTS()));
        st.setString(8, String.valueOf((float) o.getNilaiUAS()));
        st.setString(9, o.getAgama());
        st.executeUpdate();
        return o;
    }
    public void update(Maulana o) throws SQLException {//untuk update ke database
        java.sql.Connection conn = new DBConnection().connect();
        
        PreparedStatement st = conn.prepareStatement("update maulana set nim=?, nama=?, tanggallahir=?, jeniskelamin=?, jurusan=?, profilegambar=?, nilaiuts=?, nilaiuas=?, agama=? where id=?");
        
        st.setString(1, o.getNIM());
        st.setString(2, o.getNama());
        st.setString(3, o.getTanggalLahir());
        st.setString(4, o.getJenisKelamin());
        st.setString(5, o.getJurusan());
        st.setString(6, o.getProfileGambar());
        st.setString(7, String.valueOf((float) o.getNilaiUTS()));
        st.setString(8, String.valueOf((float) o.getNilaiUAS()));
        st.setString(9, o.getAgama());
        st.setInt(10, o.getId());
        st.executeUpdate();
    }

    public void delete(int ids) throws SQLException {// untuk delete berdasarkan nim
        java.sql.Connection conn = new DBConnection().connect();
        PreparedStatement st = conn.prepareStatement("delete from maulana where id=?");
        st.setInt(1, ids);
        st.executeUpdate();
    }
    
    public Maulana find(int ids) throws SQLException {// untuk delete berdasarkan nim
        java.sql.Connection conn = new DBConnection().connect();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM maulana WHERE id = ?");
        ps.setInt(1, ids);
        ResultSet rs = ps.executeQuery();
        Maulana wako = null;
        while(rs.next()){
            wako = new Maulana(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("tanggallahir"),
                    rs.getString("jeniskelamin"),
                    rs.getString("jurusan"),
                    rs.getString("profilegambar"),
                    rs.getFloat("nilaiuts"),
                    rs.getFloat("nilaiuas"),
                    rs.getString("agama")
            );
        }
        return wako;
    }

    public List<Maulana> getAll(String nama) throws SQLException { // untuk read all, jadi semua data diambil dan ditampilkan
        java.sql.Connection conn = new DBConnection().connect();
        ResultSet rs = null;
        if ("".equals(nama)) {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from maulana");
        } else {
            String sqls = "SELECT * FROM maulana WHERE nama LIKE '%"+nama+"%'";
            PreparedStatement ps = conn.prepareStatement(sqls);
            rs = ps.executeQuery();
        }

        List<Maulana> list = new ArrayList();
        while(rs.next()){
            Maulana mhs = new Maulana(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("tanggallahir"),
                    rs.getString("jeniskelamin"),
                    rs.getString("jurusan"),
                    rs.getString("profilegambar"),
                    rs.getFloat("nilaiuts"),
                    rs.getFloat("nilaiuas"),
                    rs.getString("agama")
                    
            );
            list.add(mhs);
        }
        return list;
    }
}
