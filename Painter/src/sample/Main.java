package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, 500, 500);
        Canvas canvas = new Canvas(500, 500);
        sp.getChildren().add(canvas);
        GraphicsContext gc;

        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);

        Socket s = new Socket("127.0.0.1", 6236);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        scene.setOnMousePressed(event ->{
            gc.beginPath();
            gc.lineTo(event.getSceneX(), event.getSceneY());
            gc.stroke();

            try {
                double[] arr = {event.getSceneX(), event.getSceneY()};
                dos.writeUTF("p " + arr[0] + " " + arr[1]);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        scene.setOnMouseDragged(event -> {
            gc.lineTo(event.getSceneX(), event.getSceneY());
            gc.stroke();

            try {
                double[] arr = {event.getSceneX(), event.getSceneY()};
                dos.writeUTF("" + arr[0] + " " + arr[1]);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        scene.setOnMouseReleased(event -> {
//            System.out.println("finished");
//        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
