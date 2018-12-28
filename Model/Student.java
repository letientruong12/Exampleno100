package Model;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Student {
	private String STT;
	private String Ho;
	private String Ten;
	private String Lop;
	private String Que;
	private String Ngaysinh;
	private String Ma;
	public Student() {
		
	}
	public String getSTT() {
		return STT;
	}
	public void setSTT(String sTT) {
		STT = sTT;
	}
	public String getHo() {
		return Ho;
	}
	public void setHo(String ho) {
		Ho = ho;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getLop() {
		return Lop;
	}
	public void setLop(String lop) {
		Lop = lop;
	}
	public String getQue() {
		return Que;
	}
	public void setQue(String que) {
		Que = que;
	}
	public String getNgaysinh() {
		return Ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		Ngaysinh = ngaysinh;
	}
	public String getMa() {
		return Ma;
	}
	public void setMa(String ma) {
		Ma = ma;
	}
}
