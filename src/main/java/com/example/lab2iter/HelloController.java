package com.example.lab2iter;

import Iter.ConcreteAggregate;
import Iter.Iterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Label labelError;
    @FXML
    private ImageView imgView;

    private Button btnClickStop;

    @FXML
    private TextField speedTextField;

    public ConcreteAggregate imgBuild = new ConcreteAggregate("");
    public Iterator imgIter = imgBuild.getIterator();
    Timeline timeline = new Timeline();



    public void btnClickBack() {
        imgView.setImage(imgIter.preview());
    }

    public void btnClickNext() {
        imgView.setImage(imgIter.next());
    }

    @FXML
    private void btnClickSlideShow() {
        String speedText = speedTextField.getText();

        if (speedText.isEmpty()) {
            labelError.setText("Введите скорость для слайд-шоу");
        } else {
            try {
                int time = Integer.parseInt(speedText);

                labelError.setText("");
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time), event -> {
                    imgView.setImage(imgIter.next());
                }));
                timeline.play();
            } catch (NumberFormatException e) {
                labelError.setText("Введите числовое значение для скорости слайд-шоу");
            }
        }
    }

    @FXML
    private void btnClickStop() {
        timeline.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgView.setImage(new Image("/1.jpg"));
    }

}