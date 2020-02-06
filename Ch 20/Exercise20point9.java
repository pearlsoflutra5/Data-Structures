/*
Author: Kacie Rae
Date: 2-5-20
Description:  
*/
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.*;

public class Exercise20point9 extends Application {
  @Override
  public void start(Stage primaryStage) {
    MultipleBallPane ballPane = new MultipleBallPane();
    ballPane.setStyle("-fx-border-color: yellow");

    Button btAdd = new Button("+");
    Button btSubtract = new Button("-");
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(btAdd, btSubtract);
    hBox.setAlignment(Pos.CENTER);

    btAdd.setOnAction(e -> ballPane.add());
    btSubtract.setOnAction(e -> ballPane.subtract());
    ballPane.setOnMousePressed(e -> ballPane.pause());
    ballPane.setOnMouseReleased(e -> ballPane.play());

    ScrollBar sbSpeed = new ScrollBar();
    sbSpeed.setMax(20);
    sbSpeed.setValue(10);
    ballPane.rateProperty().bind(sbSpeed.valueProperty());

    BorderPane pane = new BorderPane();
    pane.setCenter(ballPane);
    pane.setTop(sbSpeed);
    pane.setBottom(hBox);

    Scene scene = new Scene(pane, 250, 150);
    primaryStage.setTitle("Exercise 20.9"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }

  private class MultipleBallPane extends Pane {
    private Timeline animation;

    public MultipleBallPane() {
      animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play(); 
    }

    public void add() {
      Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
      double radius = 2 + Math.random() * 19;
      getChildren().add(new Ball(30, 30, radius, color));
    }

    public void subtract() {
      if (getChildren().size() > 0) {
        ArrayList<Node> nodes = new ArrayList(getChildren());
        ArrayList<Ball> balls = new ArrayList(nodes);
        getChildren().remove(Collections.max(balls));
      }
    }

    public void play() {
      animation.play();
    }

    public void pause() {
      animation.pause();
    }

    public void increaseSpeed() {
      animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
      animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
      return animation.rateProperty();
    }

    protected void moveBall() {
      for (Node node: this.getChildren()) {
        Ball ball = (Ball)node;
        
        if (ball.getCenterX() < ball.getRadius() || ball.getCenterX() > getWidth() - ball.getRadius()) {
          ball.dx *= -1; 
        }
        if (ball.getCenterY() < ball.getRadius() || ball.getCenterY() > getHeight() - ball.getRadius()) {
          ball.dy *= -1; 
        }

        ball.setCenterX(ball.dx + ball.getCenterX());
        ball.setCenterY(ball.dy + ball.getCenterY());
      }
    }
  }

  class Ball extends Circle implements Comparable<Ball> {
    private double dx = 1, dy = 1;

    Ball(double x, double y, double radius, Color color) {
      super(x, y, radius);
      setFill(color); 
    }

    @Override 
    public int compareTo(Ball ball) {
      if (getRadius() > ball.getRadius())
        return 1;
      else if (getRadius() < ball.getRadius())
        return -1;
      else
        return 0;
    }
  }
}
