// Exercise31_01Server.java: The server can communicate with multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Exercise31_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> {
      try {
        ServerSocket serverSocket = new ServerSocket(8000);
        Platform.runLater(() -> ta.appendText("Exercise31_01Server started at " + new Date() + '\n'));
        Socket socket = serverSocket.accept();
        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        
        while (true) {
          Date clientConnectDate = new Date();
          double annualInterestRate = inputFromClient.readDouble();
          int numberOfYears = inputFromClient.readInt();
          double loanAmount = inputFromClient.readDouble();
          double monthlyInterestRate = annualInterestRate / 1200;
          double monthlyPayment = loanAmount * monthlyInterestRate / (1- 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
          double totalPayment = monthlyPayment * numberOfYears * 12;
          outputToClient.writeDouble(monthlyPayment);
          outputToClient.writeDouble(totalPayment);
          
          Platform.runLater(() -> {
            ta.appendText("Connected to a client at " + clientConnectDate + '\n');
            ta.appendText("Annual Interest Rate: " + annualInterestRate + '\n');
            ta.appendText("Number Of Years: " + numberOfYears + '\n');
            ta.appendText("Loan Amount: " + loanAmount + '\n');
            ta.appendText("monthlyPayment: " + monthlyPayment + '\n');
            ta.appendText("totalPayment: " + totalPayment + '\n');
          });
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }).start();
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
