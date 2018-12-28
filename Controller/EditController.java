package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Model.Student;
import application.ConnectSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditController{
	@FXML
	private TextField tfHo;
	@FXML
	private TextField tfTen;
	@FXML
	private TextField tfLop;
	@FXML
	private TextField tfQue;
	@FXML
	private DatePicker dpNgaySinh;
	@FXML
	private TextField tfMa;
	@FXML
	private TableView<Student> ListTable;
	@FXML
	private TableColumn<Student, String> NoColumn;
	@FXML
	private TableColumn<Student, String> HoColumn;
	@FXML
	private TableColumn<Student, String> TenColumn;
	@FXML
	private TableColumn<Student, String> LopColumn;
	@FXML
	private TableColumn<Student, String> QueColumn;
	@FXML
	private Label lbMa;
	private Student st;
	private static Student StSelect;
	private Stage dialogStage;
//	@FXML
//	private void initialize() {
//		
//	}
	ConnectSQL contact = new ConnectSQL();
	public List<Student> getDocGia(){
		List<Student> list = contact.getDocGia();
		ObservableList<Student> Lis = FXCollections.observableArrayList(list);
		return list;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public static void setStudentSelect(Student st) {
		StSelect = st;
	}
	public String converLocalDate(LocalDate ld) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String kq = ld.format(formatter);
		return kq;
	}
	public void ShowDetails(Student st) {
		this.st = st;
		tfHo.setText(st.getHo());
		tfTen.setText(st.getTen());
		tfLop.setText(st.getLop());
		tfQue.setText(st.getQue());
		dpNgaySinh.setPromptText(st.getNgaysinh());
		tfMa.setText(st.getMa());
		System.out.println("Ho la :"+tfHo+lbMa);
	}
	public void Edit() {
		String Ma = tfMa.getText();
		String Ho = tfHo.getText();
		String Ten = tfTen.getText();
		String Lop = tfLop.getText();
		String Que = tfQue.getText();
		LocalDate NgaySinh = dpNgaySinh.getValue();
		ConnectSQL connect = new ConnectSQL();
		Student stnew = new Student();
		stnew.setHo(Ho);
		stnew.setTen(Ten);
		stnew.setLop(Lop);
		stnew.setNgaysinh(converLocalDate(NgaySinh));
		stnew.setQue(Que);
		stnew.setMa(Ma);
		connect.Edit(stnew,StSelect);
	}
}
