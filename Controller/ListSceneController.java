package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Model.Student;
import application.ConnectSQL;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListSceneController implements Initializable {
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
	private TableColumn<Student, String> NgaySinhColumn;
	@FXML
	private TableColumn<Student, String> MaColumn;
	@FXML
	private Label HoTenLabel;
	@FXML
	private Label LopLabel;
	@FXML
	private Label QueLabel;
	@FXML
	private Label NgaySinhLabel;
	@FXML
	private Label MaLabel;
	@FXML
	private Button btThem;
	@FXML
	private Button btEdit;
	public static Stage NewStage;
	public static Stage EditStage;
	private Student st;
	private static Student StSelect;
	public static void setStudentSelect(Student st) {
		StSelect = st;
	}
	public ListSceneController() {
	}
	ConnectSQL contact = new ConnectSQL();
	public List<Student> getDocGia(){
		List<Student> list = contact.getDocGia();
		ObservableList<Student> docgia = FXCollections.observableArrayList(list);
		return list;
	}
	private void showStudentDetails(Student st) {
		if(st!=null) {
			HoTenLabel.setText(st.getHo()+" "+st.getTen());
			LopLabel.setText(st.getLop());
			QueLabel.setText(st.getQue());
			NgaySinhLabel.setText(st.getNgaysinh());
			MaLabel.setText(st.getMa());
		}
		else {
			HoTenLabel.setText("");
			LopLabel.setText("");
			QueLabel.setText("");
			NgaySinhLabel.setText("");
			MaLabel.setText("");
		}
	}
	public void Them(ActionEvent event) throws IOException{
		Parent NewScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("View/ThemStudent.fxml"));
		Scene NewScene = new Scene(NewScenefxml);
		Stage NewStage = new Stage();
		NewStage.setScene(NewScene);
		NewStage.setTitle("Tạo người đọc");
		
		NewStage.initModality(Modality.WINDOW_MODAL);
		NewStage.initOwner(Main.primaryStage);
		
		this.NewStage = NewStage;
		
		NewStage.show();
	}
	public boolean Edit11(Student st) {
		try {
			FXMLLoader loader = new FXMLLoader();	
			int selectIndex = ListTable.getSelectionModel().getSelectedIndex();
			if(selectIndex>=0) {
			loader.setLocation(Main.class.getResource("../View/EditStudent.fxml"));
			AnchorPane 	page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			Stage EditStage = new Stage();
			EditStage.setScene(scene);
			EditStage.setTitle("Edit Student");
			
			EditStage.initModality(Modality.WINDOW_MODAL);
			EditStage.initOwner(Main.primaryStage);
			
			EditController controller = loader.getController();
			controller.setDialogStage(EditStage);
			EditController.setStudentSelect(ListTable.getSelectionModel().getSelectedItem());
			controller.ShowDetails(ListTable.getSelectionModel().getSelectedItem());
			this.EditStage = EditStage;
	
			EditStage.show();
			
		}
		else {
			 Alert alert = new Alert(AlertType.WARNING);
//		     alert.initOwner(mainApp.getPrimaryStage());
		     alert.setTitle("No Selection");
		     alert.setHeaderText("No Person Selected");
		     alert.setContentText("Please select a person in the table.");

		     alert.showAndWait();
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
}
	public void Edit(ActionEvent event) throws IOException{
		Edit11(st);
	}
	public void Xoa() {
			int select = ListTable.getSelectionModel().getSelectedIndex();
			if(select>=0) {
				Student st = new Student();
				st = ListTable.getItems().remove(select);
				ConnectSQL connect = new ConnectSQL();
				connect.Xoa(st);
			}
			else {
				 Alert alert = new Alert(AlertType.WARNING);
			     alert.setTitle("No Selection");
			     alert.setHeaderText("No Person Selected");
			     alert.setContentText("Please select a person in the table.");

			     alert.showAndWait();
			}
	}
	
	
	public void initialize(URL location, ResourceBundle resource) {
		NoColumn.setCellValueFactory(new PropertyValueFactory<>("STT"));
		HoColumn.setCellValueFactory(new PropertyValueFactory<>("Ho"));
		TenColumn.setCellValueFactory(new PropertyValueFactory<>("Ten"));
//		LopColumn.setCellValueFactory(new PropertyValueFactory<>("Lop"));
//		QueColumn.setCellValueFactory(new PropertyValueFactory<>("Que"));
		ListTable.getItems().setAll(getDocGia());
		showStudentDetails(null);
		ListTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->showStudentDetails(newValue));
	}
}
