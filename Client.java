package Application;

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

public class Client extends Tab {

	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String clientNo, String firstName, String lastName, String address, String telNo ) 
	{
		int result = connection.addClient( clientNo, firstName, lastName, address, telNo );

		if ( result == 1 ){
			AlertBox.display("Client", "Client Added!");
		}

		else {  
			AlertBox.display("Client", "Client Not Added!");
		}
	}

	private void updateButtonActionPerformed(String clientNo, String firstName, String lastName, String address, String telNo ) 
	{
		int result = connection.updateClient( clientNo, firstName, lastName, address, telNo );

		if ( result == 1 ){  
			AlertBox.display("Client", "Client Updated!");
		}

		else {    	  
			AlertBox.display("Client", "Client Not Updated!");
		}
	}

	public Client() {

		connection = new DB_Connection();

		setText("Client");

		BorderPane border = new BorderPane();

		Label title = new Label("Client Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label client = new Label("Client No:");
		GridPane.setConstraints(client, 1, 0); 

		TextField clientTextFld = new TextField();
		GridPane.setConstraints(clientTextFld, 2, 0);

		Label first = new Label("First Name:");
		GridPane.setConstraints(first, 1, 1);

		TextField firstTextFld = new TextField();
		GridPane.setConstraints(firstTextFld, 2, 1);

		Label last = new Label("Last Name:");
		GridPane.setConstraints(last, 1, 2);

		TextField lastTextFld = new TextField();
		GridPane.setConstraints(lastTextFld, 2, 2);

		Label add = new Label("Address:");
		GridPane.setConstraints(add, 1, 3);

		TextField addressTextFld = new TextField();
		GridPane.setConstraints(addressTextFld, 2, 3);

		Label tel = new Label("Tel No:");
		GridPane.setConstraints(tel, 1, 4);

		TextField telTextFld = new TextField();
		GridPane.setConstraints(telTextFld, 2, 4);

		grid.getChildren().addAll(client, clientTextFld, first, firstTextFld, last, lastTextFld, add, addressTextFld, tel, telTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			String clientNo = clientTextFld.getText();

			if(clientNo.equals(""))
			{
				AlertBox.display("Error", "Client No can no be blank!");
			}

			else{
				String firstName = firstTextFld.getText();
				String lastName = lastTextFld.getText();
				String address = addressTextFld.getText();
				String telNo = telTextFld.getText();

				createButtonActionPerformed(clientNo, firstName, lastName, address, telNo);
			}
		});

		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Client you want to remove!", "Client No");

		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{

			String clientNo = clientTextFld.getText();

			if(clientNo.equals(""))
			{
				AlertBox.display("Error", "Client No can no be blank!");
			}
			
			else{
			String firstName = firstTextFld.getText();
			String lastName = lastTextFld.getText();
			String address = addressTextFld.getText();
			String telNo = telTextFld.getText();

			updateButtonActionPerformed(clientNo, firstName, lastName, address, telNo);
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