package Cartoon;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/* This class provides overall organization to the application through a BorderPane.
* It instantiates Cartoon and adds it to the BorderPane. It also adds an exit Button
* and a label offering instruction to the user.
*/

public class PaneOrganizer {
    private BorderPane _pane;
    private Cartoon _cartoon;

    public PaneOrganizer(){
        _pane = new BorderPane();
        _pane.setStyle("-fx-background-color: #19262c;");
        _cartoon = new Cartoon();
        _pane.getChildren().addAll(_cartoon.getRoot());
        this.createbottomPane();
    }

    public Pane getRoot() {
        return _pane;
    }

    /* This method is used to create a bottom Hbox pane consisting of an
    exit button and label*/
    private void createbottomPane(){
        HBox bottomPane = new HBox(50);
        bottomPane.setAlignment(Pos.BOTTOM_LEFT);
        _pane.setBottom(bottomPane);
        Button btn = new Button("Exit");
        Label label = new Label("Press Q to cycle through speeds");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Tahoma", 13));
        label.setMinHeight(50);
        bottomPane.getChildren().addAll(btn, label);
        btn.setOnAction(new ClickHandler());
    }

    /*Adding click functionality to the button*/
    private class ClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            Platform.exit();
            e.consume();
        }
    }

}
