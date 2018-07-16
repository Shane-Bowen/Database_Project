package View;

import Connection.DB_Connection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Staff extends Tab {
	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String staffNo, String firstName, String lastName, String address, String gender, String dateOfBirth, Integer salary ) 
	{
		int result = connection.addStaff( staffNo,
				firstName, lastName, address, gender, dateOfBirth, salary );

		if ( result == 1 ){  
			AlertBox.display("Staff", "Staff Added!");
		}

		else {    	  
			AlertBox.display("Staff", "Staff Not Added!");
		}
	}

	private void updateButtonActionPerformed(String staffNo, String firstName, String lastName, String address, String gender, String dateOfBirth, Integer salary ) 
	{
		int result = connection.updateStaff( staffNo,
				firstName, lastName, address, gender, dateOfBirth, salary );

		if ( result == 1 ){  
			AlertBox.display("Staff", "Staff Updated!");
		}

		else {    	  
			AlertBox.display("Staff", "Staff Not Updated!");
		}
	}

	public Staff() {

		connection = new DB_Connection();
		setText("Staff");

		BorderPane border = new BorderPane();

		Label title = new Label("Staff Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label staff = new Label("Staff No:");
		GridPane.setConstraints(staff, 1, 0); 

		TextField staffTextFld = new TextField();
		GridPane.setConstraints(staffTextFld, 2, 0);

		Label first = new Label("First Name:");
		GridPane.setConstraints(first, 1, 1);

		TextField firstTextFld = new TextField();
		GridPane.setConstraints(firstTextFld, 2, 1);

		Label last = new Label("Last Name:");
		GridPane.setConstraints(last, 1, 2);

		TextField lastTextFld = new TextField();
		GridPane.setConstraints(lastTextFld, 2, 2);

		Label sex = new Label("gender:");
		GridPane.setConstraints(sex, 1, 3);

		TextField sexTextFld = new TextField();
		GridPane.setConstraints(sexTextFld, 2, 3);

		Label dob = new Label("DOB:");
		GridPane.setConstraints(dob, 1, 4);

		TextField dobTextFld = new TextField();
		GridPane.setConstraints(dobTextFld, 2, 4);

		Label sal = new Label("Salary:");
		GridPane.setConstraints(sal, 1, 5);

		TextField salaryTextFld = new TextField();
		GridPane.setConstraints(salaryTextFld, 2, 5);

		Label add = new Label("Address:");
		GridPane.setConstraints(add, 1, 6);

		TextField addressTextFld = new TextField();
		GridPane.setConstraints(addressTextFld, 2, 6);

		grid.getChildren().addAll(staff, staffTextFld, first, firstTextFld, last, lastTextFld, sex, sexTextFld, dob, dobTextFld,
				sal, salaryTextFld, add, addressTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{
			
			String staffNo = staffTextFld.getText();
			
			if(staffNo.equals(""))
			{
				AlertBox.display("Error", "Staff No can not be blank!");
			}
			
			else{
			String firstName = firstTextFld.getText();
			String lastName = lastTextFld.getText();
			String address = addressTextFld.getText();
			String gender = sexTextFld.getText();
			String dateOfBirth = dobTextFld.getText();
			Integer salary = Integer.valueOf(salaryTextFld.getText());

			createButtonActionPerformed(staffNo, firstName, lastName, address, gender, dateOfBirth, salary);
			}
			
		});

		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Staff you want to remove!", "Staff No");

		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{
			
			String staffNo = staffTextFld.getText();
			
			if(staffNo.equals(""))
			{
				AlertBox.display("Error", "Staff No can not be blank!");
			}

			else{
			String firstName = firstTextFld.getText();
			String lastName = lastTextFld.getText();
			String address = addressTextFld.getText();
			String gender = sexTextFld.getText();
			String dateOfBirth = dobTextFld.getText();
			Integer salary = Integer.valueOf(salaryTextFld.getText());
			
			updateButtonActionPerformed(staffNo, firstName, lastName, address, gender, dateOfBirth, salary);
			}

		});

		Button quit = new Button ("Quit");
		quit.setOnAction(e -> closeProgram());

		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(create, delete, update, quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);

	}
}