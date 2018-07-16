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

public class Job extends Tab {
	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String jobNo, String Description, String startDate, String endDate, Integer cost, String clientNo, String staffNo, String partNo ) 
	{
		int result = connection.addJob( jobNo,
				Description, startDate, endDate, cost, clientNo, staffNo, partNo );

		if ( result == 1 ){
			AlertBox.display("Job", "Job Added!");
		}

		else {  
			AlertBox.display("Job", "Job Not Added!");
		}
	}

	private void updateButtonActionPerformed(String jobNo, String Description, String startDate, String endDate, Integer cost, String clientNo, String staffNo, String partNo) 
	{
		int result = connection.updateJob( jobNo,
				Description, startDate, endDate, cost, clientNo, staffNo, partNo );

		if ( result == 1 ){  
			AlertBox.display("Job", "Job Updated!");
		}

		else {    	  
			AlertBox.display("Job", "Job Not Updated!");
		}
	}


	public Job() {

		connection = new DB_Connection();
		setText("Job");

		BorderPane border = new BorderPane();

		Label title = new Label("Job Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label job = new Label("Job No:");
		GridPane.setConstraints(job, 1, 0); 

		TextField jobTextFld = new TextField();
		GridPane.setConstraints(jobTextFld, 2, 0);

		Label desc = new Label("Description:");
		GridPane.setConstraints(desc, 1, 1);

		TextField descTextFld = new TextField();
		GridPane.setConstraints(descTextFld, 2, 1);

		Label start = new Label("Start Date:");
		GridPane.setConstraints(start, 1, 2);

		TextField startTextFld = new TextField();
		GridPane.setConstraints(startTextFld, 2, 2);

		Label end = new Label("End Date:");
		GridPane.setConstraints(end, 1, 3);

		TextField endTextFld = new TextField();
		GridPane.setConstraints(endTextFld, 2, 3);

		Label price = new Label("Cost:");
		GridPane.setConstraints(price, 1, 4);

		TextField costTextFld = new TextField();
		GridPane.setConstraints(costTextFld, 2, 4);

		Label client = new Label("Client No:");
		GridPane.setConstraints(client, 1, 5);

		TextField clientTextFld = new TextField();
		GridPane.setConstraints(clientTextFld, 2, 5);

		Label staff = new Label("Staff No:");
		GridPane.setConstraints(staff, 1, 6);

		TextField staffTextFld = new TextField();
		GridPane.setConstraints(staffTextFld, 2, 6);

		Label part = new Label("Part No:");
		GridPane.setConstraints(part, 1, 7);

		TextField partTextFld = new TextField();
		GridPane.setConstraints(partTextFld, 2, 7);

		grid.getChildren().addAll(job, jobTextFld, desc, descTextFld, start, startTextFld, end, endTextFld, price, costTextFld,
				client, clientTextFld, staff, staffTextFld, part, partTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			String jobNo = jobTextFld.getText();

			if(jobNo.equals(""))
			{
				AlertBox.display("Error", "Job No can not be blank!");
			}

			else{
				String Description = descTextFld.getText();
				String startDate = startTextFld.getText();
				String endDate = endTextFld.getText();
				Integer cost = Integer.valueOf(costTextFld.getText());
				String clientNo = clientTextFld.getText();
				String staffNo = staffTextFld.getText();
				String partNo = partTextFld.getText();

				createButtonActionPerformed(jobNo, Description, startDate, endDate, cost, clientNo, staffNo, partNo);
			}
		});

		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Job you want to remove!", "Job No");

		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{

			String jobNo = jobTextFld.getText();

			if(jobNo.equals(""))
			{
				AlertBox.display("Error", "Job No can not be blank!");
			}
			
			else{
			String Description = descTextFld.getText();
			String startDate = startTextFld.getText();
			String endDate = endTextFld.getText();
			Integer cost = Integer.valueOf(costTextFld.getText());
			String clientNo = clientTextFld.getText();
			String staffNo = staffTextFld.getText();
			String partNo = partTextFld.getText();

			updateButtonActionPerformed(jobNo, Description, startDate, endDate, cost, clientNo, staffNo, partNo);
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