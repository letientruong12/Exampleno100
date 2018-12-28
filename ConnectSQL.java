package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Student;
import javafx.stage.Stage;

public class ConnectSQL {
	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=SudentManagent";
	public static final String user = "sa";
	public static final String pass ="1234567890";
	public Connection getMySqlConnect() {
		Connection connect =null;
		try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 connect = DriverManager.getConnection(url, user, pass);
			 System.out.println("Connect thanh cong");
		} catch(Exception ex) {
			System.out.println("Connect that bai");
		}
		return connect;	
	}
	private Student createStudent(ResultSet rs) {
		Student st = new Student();
		try {
			st.setSTT(rs.getString("STT"));
			st.setHo(rs.getString("Ho"));
			st.setTen(rs.getString("Ten"));
			st.setLop(rs.getString("Lop"));
			st.setQue(rs.getString("Que"));
			st.setNgaysinh(rs.getString("NgaySinh"));
			st.setMa(rs.getString("Ma"));
				
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Loi");
		}
		return st;
	}
	public List<Student> getDocGia() {
		String sql = "SELECT * FROM Student ORDER BY STT";
		List<Student> list = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = getMySqlConnect();
			Statement stt = conn.createStatement();
			ResultSet rs = stt.executeQuery(sql);
			while(rs.next()) {
				Student student = createStudent(rs);
				list.add(student);
			}
			rs.close();
			conn.close();
		}catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("Khong the hien thi danh sach");
		}
		return list;
	}
	public void Edit(Student st,Student dtOld) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connect = DriverManager.getConnection(url, user, pass);
			String sql = "UPDATE Student set Ho=?,Ten=?,Lop=?,NgaySinh=?,Que=?,Ma=? where Ma="+dtOld.getMa();
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, st.getHo());
			pst.setString(2, st.getTen());
			pst.setString(3, st.getLop());
			pst.setString(4, st.getNgaysinh());
			pst.setString(5, st.getQue());
			pst.setString(6, st.getMa());
//			pst.setString(7, st.getMa());
			int rs =pst.executeUpdate();
			if(rs>0) {
				System.out.println("Cap nhat thanh cong");
			}
			else {
				System.out.println("Cap nhat loi");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Khong the cap nhat");
		}
		
	}
}
