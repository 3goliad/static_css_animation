package Cartoon;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class Moon{

/* This class creates the object which is animated by the timeline. It is
*composed of a circle with two rectangles and 1 circle inside of it. It has
*methods that are called by the moonhandler to move it.*/
private Pane _moonPane;
private Circle _core;
private Rectangle _crater1;
private Rectangle _crater2;
private Circle _crater3;

public Moon (){
    _moonPane = new Pane();
    _core = new Circle();
    _core.setFill(Color.WHITE);
    _core.setRadius(20);
    _crater1 = new Rectangle(10,13);
    _crater2 = new Rectangle(4,8);
    _crater3 = new Circle();
    _crater3.setRadius(4);
    _crater1.setFill(Color.WHITE);
    _crater2.setFill(Color.WHITE);
    _crater3.setFill(Color.WHITE);
    _crater1.setStroke(Color.web("999899"));
    _crater2.setStroke(Color.web("999899"));
    _crater3.setStroke(Color.web("999899"));
    _crater1.setStrokeWidth(.5);
    _crater2.setStrokeWidth(.5);
    _crater3.setStrokeWidth(.5);
    double yLoc = 600;
    this.setYLoc(yLoc);
    this.setXLoc(550);
    _moonPane.getChildren().addAll(_core, _crater1, _crater2, _crater3);
}

public Pane getRoot() {
    return _moonPane;
}

/* This controls the y location of the moon and its craters. It is called by
* the moonhandler in order to move the moon up and down each cycle. */
public void setYLoc(double y){
    _core.setCenterY(y);
    _crater1.setY(y-10);
    _crater2.setY(y-1);
    _crater3.setCenterY(y+10);
}

/* This controls the x location of the moon and its craters. Unlike the y location,
the x location of the moon is determined by mouse input so that it aligns with the
parallax of the other layers. Thus, this method is callled by the mousehandler.*/
public void setXLoc(double x){
    x = -.3*(x-(Constants.SCENE_WIDTH/2));
    x = x + 350;
    _core.setCenterX(x);
    _crater1.setX(x);
    _crater2.setX(x-10);
    _crater3.setCenterX(x+2);
}

}
