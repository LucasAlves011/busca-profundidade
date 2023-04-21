package com.example.guiprofundidade2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.example.guiprofundidade2.HelloApplication.*;

public class HelloController {
    @FXML
    private Label l1;
    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private Rectangle r1;

    @FXML
    private Rectangle r2;

    @FXML
    private Rectangle r3;

    @FXML
    private Rectangle r4;

    @FXML
    private Rectangle r5;

    @FXML
    private Rectangle r6;

    @FXML
    private Rectangle r7;

    @FXML
    private Rectangle r8;

    @FXML
    private Rectangle r9;

    @FXML
    private Button botaoStart;

    @FXML
    private Label contadorPassos;

    public void initialize() {
        l1.setText(String.valueOf(inicial.estado[0][0]));
        l2.setText(String.valueOf(inicial.estado[0][1]));
        l3.setText(String.valueOf(inicial.estado[0][2]));
        l4.setText(String.valueOf(inicial.estado[1][0]));
        l5.setText(String.valueOf(inicial.estado[1][1]));
        l6.setText(String.valueOf(inicial.estado[1][2]));
        l7.setText(String.valueOf(inicial.estado[2][0]));
        l8.setText(String.valueOf(inicial.estado[2][1]));
        l9.setText(String.valueOf(inicial.estado[2][2]));
        contadorPassos.setText(String.valueOf(resultado.size()-1));
    }


    public int contador=0;

    @FXML
    protected void onHelloButtonClick()  {
        botaoStart.setDisable(true);

        int limite=resultado.size();


        Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {

            l1.setText(String.valueOf(resultado.get(contador).estado[0][0]));
            if (String.valueOf(resultado.get(contador).estado[0][0]).equals("1")) {
                r1.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[0][0]).equals("0")) {
                r1.setStyle("-fx-fill: #848484;");
            } else {
                r1.setStyle("-fx-fill: DODGERBLUE;");
            }

            l2.setText(String.valueOf(resultado.get(contador).estado[0][1]));
            if (String.valueOf(resultado.get(contador).estado[0][1]).equals("2")) {
                r2.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[0][1]).equals("0")) {
                r2.setStyle("-fx-fill: #848484;");
            } else {
                r2.setStyle("-fx-fill: DODGERBLUE;");
            }

            l3.setText(String.valueOf(resultado.get(contador).estado[0][2]));
            if (String.valueOf(resultado.get(contador).estado[0][2]).equals("3")) {
                r3.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[0][2]).equals("0")) {
                r3.setStyle("-fx-fill: #848484;");
            } else {
                r3.setStyle("-fx-fill: DODGERBLUE;");
            }

            l4.setText(String.valueOf(resultado.get(contador).estado[1][0]));
            if (String.valueOf(resultado.get(contador).estado[1][0]).equals("4")) {
                r4.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[1][0]).equals("0")) {
                r4.setStyle("-fx-fill: #848484;");
            } else {
                r4.setStyle("-fx-fill: DODGERBLUE;");
            }

            l5.setText(String.valueOf(resultado.get(contador).estado[1][1]));
            if (String.valueOf(resultado.get(contador).estado[1][1]).equals("5")) {
                r5.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[1][1]).equals("0")) {
                r5.setStyle("-fx-fill: #848484;");
            } else {
                r5.setStyle("-fx-fill: DODGERBLUE;");
            }

            l6.setText(String.valueOf(resultado.get(contador).estado[1][2]));

            if (String.valueOf(resultado.get(contador).estado[1][2]).equals("6")) {
                r6.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[1][2]).equals("0")) {
                r6.setStyle("-fx-fill: #848484;");
            } else {
                r6.setStyle("-fx-fill: DODGERBLUE;");
            }

            l7.setText(String.valueOf(resultado.get(contador).estado[2][0]));
            if (String.valueOf(resultado.get(contador).estado[2][0]).equals("7")) {
                r7.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[2][0]).equals("0")) {
                r7.setStyle("-fx-fill: #848484;");
            } else {
                r7.setStyle("-fx-fill: DODGERBLUE;");
            }

            l8.setText(String.valueOf(resultado.get(contador).estado[2][1]));

            if (String.valueOf(resultado.get(contador).estado[2][1]).equals("8")) {
                r8.setStyle("-fx-fill: green;");
            } else if (String.valueOf(resultado.get(contador).estado[2][1]).equals("0")) {
                r8.setStyle("-fx-fill: #848484;");
            }
            else {
                r8.setStyle("-fx-fill: DODGERBLUE;");
            }

            l9.setText(String.valueOf(resultado.get(contador).estado[2][2]));
            if (String.valueOf(resultado.get(contador).estado[2][2]).equals("0")) {
                r9.setStyle("-fx-fill: #848484;");
            }
            else {
                r9.setStyle("-fx-fill: DODGERBLUE;");
            }

            contadorPassos.setText(String.valueOf( limite - contador - 1));
            contador++;
        }));
        timeline.setCycleCount(limite);
        timeline.play();
    }
}