/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class MahasiswaManager {
    Connection conn = null;
    Statement  st = null;
    String driver ="com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_unidha";
    public MahasiswaManager(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "");
            st = conn.createStatement();
        }catch (Exception e){
                    e.printStackTrace();
                    }
            
        }
    public List getMahasiswa(){
        ResultSet rs = null;
        List Mahasiswa = new ArrayList();
            try {
                rs = st.executeQuery("Select * From tbl_unidha");
                while (rs.next()) {
                    Mahasiswa m = new Mahasiswa();
                    m.setnobp(rs.getString("NoBP"));
                    m.getnama(rs.getString("Nama"));
                    m.gettmplahir(rs.getString("TmpLahir"));
                    m.gettgllahir(rs.getString("TglLahir"));
                    m.getalamat(rs.getString("Alamat"));
                    m.getphone(rs.getString("Phone"));
                    m.getasalsekolah(rs.getString("AsalSekolah"));
                    Mahasiswa.add(m);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return Mahasiswa;
    }
    public int Insert (Mahasiswa m){
        int result = 0;
        try {
            result = st.executeUpdate("insert into tbl_unidha value ('"+ m.getnobp() +"','"+ m.getnama() +"','"+ m.gettmplahir()
                    +"','"+ m.gettgllahir() +"','"+ m.getalamat() +"',"
                            + "'"+ m.getphone() +"','"+ m.getasalsekolah() +"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int Delete(Mahasiswa m) {
        int result = 0;
        try {
            result = st.executeUpdate("delete from tbl_unidha where nobp='"+ m.getnobp() +"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int Update(Mahasiswa m) {
        int result = 0;
        try { 
            result = st.executeUpdate("update tbl_unidha setnobp='"+ m.getnobp() +"',"
                    + "nama='"+ m.getnama() +"',"
                            + "tmplahir='"+ m.gettmplahir() +"',"
                                    + "tgllahir='"+ m.gettgllahir() +"',"
                                            + "alamat='"+ m.getalamat() +"',"
                                                    + "phone='"+ m.getphone() +"',"
                                                            + "asalsekolah='"+ m.getasalsekolah() +"' where nobp='"+ m.getnobp() +"'");
        } catch (Exception e) {
        }
        return result;
    }
    public void closeConnection() {
        try {
            conn.close();
            st.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
