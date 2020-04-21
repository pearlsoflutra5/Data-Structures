import javafx.animation.PathTransition; 
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);
		
		imageView.setX(35); 
		imageView.setY(200);
		
		new Thread (new Runnable(){
			public void run() {
				try { 
					for(int i = 200; i >= 0; i--){
						imageView.setY(i);
						Thread.sleep(10);
					}							
				} 
				catch (Exception e) {
				}	
			}
		}).start();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 200); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		// Create a path transition
		/*
		
		*/
	}
	
	public static void main(String[] args) {
			Application.launch(args);
	}
}