package Cartoon;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class Cartoon {

    /* This class is responsible for instantiating all the layers of the animation,
    * and allowing for user key and mouse input to manipulate them. It also creates
    * timeline and keyframe for the moon animation, and a label. */

    private BorderPane _cartoonPane;
    private Moon _moon;
    private SceneLighting _sceneLighting;
    private SkyLighting _skyLighting;
    private Interior _interior;
    private Exterior _exterior;
    private House _house;
    private Label _label;
    private Vanitas _vanitas;
    private double _speed;
    private double _distance;
    private double _mouseX;
    private double _mouseY;

    public Cartoon (){
    _cartoonPane = new BorderPane();
    _moon = new Moon();
    _sceneLighting = new SceneLighting();
    _skyLighting = new SkyLighting();
    _interior = new Interior();
    _exterior = new Exterior();
    _house = new House();
    _vanitas = new Vanitas();
    _speed = .0015;
    _distance= Constants.SUN_HEIGHT;
    _cartoonPane.getChildren().addAll(_skyLighting.getRoot(),  _moon.getRoot(), _exterior.getRoot(),
    _house.getRoot(), _interior.getRoot(), _vanitas.getRoot(), _sceneLighting.getRoot());
    this.createLabelPane();
    _cartoonPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
    _cartoonPane.addEventHandler(MouseEvent.MOUSE_MOVED, new MouseHandler());
    _cartoonPane.requestFocus();
    _cartoonPane.setFocusTraversable(true);
    this.setupTimeline();
    }

    public Pane getRoot() {
        return _cartoonPane;
    }

    /* This method creates a label which is affected by the moon's movement. */
    private void createLabelPane(){
        HBox labelPane = new HBox();
        labelPane.setAlignment(Pos.CENTER);
        labelPane.setMinWidth(200);
        labelPane.setMinHeight(100);
        _label = new Label();
        _label.setTextFill(Color.WHITE);
        _label.setFont(new Font("Tahoma", 13));
        labelPane.getChildren().addAll(_label);
        _cartoonPane.setTop(labelPane);
    }

    /*Animation*/

    /* This method creates the Timeline for the moon's animation.*/
    public void setupTimeline(){
        KeyFrame kf = new KeyFrame(Duration.millis(1),
        new MoonHandler());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /*This method is called by the keyframe in the animation. If the moon's location
    exceeds either its upper or lower limit, the speed value is inverted so that it
    begins travelling in the opposite direction. Also, the text of the label is changed
    depending on the direction it is travelling.*/
    private class MoonHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            if (_distance > Constants.SUN_HEIGHT) {
                _speed *= -1;
                _distance = Constants.SUN_HEIGHT;
                _label.setText("Moon Rise");
            } else if (_distance < Constants.SUN_FLOOR) {
                _speed *= -1;
                _distance = Constants.SUN_FLOOR;
                _label.setText("Moon Set");
            }
            //By incrementing the distance variable through a seperate speed variable,
            //it is possible to change the moon's speed of travel.
            _distance += _speed;
            _moon.setYLoc(_distance);
            //The variables in each layer dependent on the moon's location (the
            // strength of the light, the location of the clouds, the shadows,
            // and the watch handle) have their respective methods called with the
            // distance variable as the argument.
            _sceneLighting.lightTransition(_distance);
            _skyLighting.skyTransition(_distance);
            _skyLighting.cloudMove(_distance);
            _house.shadowTransition(_distance);
            _exterior.shadowTransition(_distance);
            _vanitas.watchShift(_distance);
        }
    }


    /*Controls*/
    /*Keyhandler alters the speed of movement through the _speed instance variable.
    /*Possible to cycle through 20 different speeds by pressing Q.
    */
    private class KeyHandler implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent keyEvent){
            switch (keyEvent.getCode()){

            case Q:
            if (_speed > 0){
                _speed += .02;
                _speed = _speed % Constants.SPEED_LIMIT;
            } else {
                _speed -= .02;
                _speed = _speed % -Constants.SPEED_LIMIT;
            }

            break;

            default:
                break;
        }
        keyEvent.consume();
    }
    }

    /*Mouse Parallax. Any time the mouseEvent is triggered (whenever the mouse moves)
    * the x and Y locations of the mouse are used as arguments in the shifting methods
    * of each layer.*/
    private class MouseHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle (MouseEvent mouseEvent){
            _moon.setXLoc(mouseEvent.getX());
            _skyLighting.setShift(mouseEvent.getX(), mouseEvent.getY());
            _interior.setShift(mouseEvent.getX(), mouseEvent.getY());
            _exterior.setShift(mouseEvent.getX(), mouseEvent.getY());
            _house.setShift(mouseEvent.getX(), mouseEvent.getY());
            _vanitas.setShift(mouseEvent.getX(), mouseEvent.getY());
            mouseEvent.consume();
        }

    }



}
