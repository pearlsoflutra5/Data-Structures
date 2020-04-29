import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Exercise31_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    taClient.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        String text = taClient.getText().trim();
        try {
          toServer.writeUTF("C:" + text);
          toServer.flush();
          taClient.clear();
        }
        catch (IOException ex) {
          System.err.println(ex);
        }
      }
    });


    try {
      Socket socketServer = new Socket("localhost", 8000);
      fromServer = new DataInputStream(socketServer.getInputStream());
      toServer = new DataOutputStream(socketServer.getOutputStream());
      new Thread(() -> {
        try {
            while (true){
                String clientText = fromServer.readUTF().trim();
                taServer.appendText(clientText + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      }).start();
    }
    catch (IOException ex) {
      taServer.appendText(ex.toString() + '\n');
    }
    
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
