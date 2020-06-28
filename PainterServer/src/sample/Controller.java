package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML StackPane sp;
    @FXML Canvas canvas;
    GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
    }

    public void drawFirst(String a, String b){
        double x = Double.parseDouble(a);
        double y = Double.parseDouble(b);
        gc.beginPath();
        gc.lineTo(x, y);
        gc.stroke();
    }

    public void drawNext(String a, String b){
        double x = Double.parseDouble(a);
        double y = Double.parseDouble(b);
        gc.lineTo(x, y);
        gc.stroke();
    }
}
