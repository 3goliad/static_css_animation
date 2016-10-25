package Cartoon;
import javafx.scene.layout.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Vanitas {

/* This class is used for the table display inside the room. It has a setShift
* method for parallax purposes, and also a watchShift method to make the watch on
* the table change time as the moon rises and sets. */

    private Pane _vanitasPane;
    private Rectangle2D _viewport;
    private ImageView _vanitasView;
    private Line _handle;

    public Vanitas (){
        _vanitasPane = new Pane();
        _handle = new Line(395, 593, 394, 602);
        _handle.setStroke(Color.web("272726"));
        _handle.setStrokeWidth(5);
        Image image = new Image("/placeholderTable.png");
        _vanitasView = new ImageView();
        _vanitasView.setImage(image);
        _viewport = new Rectangle2D(0, 0, 900, 1100);
        _vanitasView.setViewport(_viewport);
        double xLoc = 0;
        double yLoc = 0;
        this.setShift(xLoc, yLoc);
        this.watchShift(0);
        _vanitasPane.getChildren().addAll(_vanitasView, _handle);
    }

    public Pane getRoot() {
        return _vanitasPane;
    }

    /* This method rotates a line (the hand of the watch) through being called by
    * the moonhandler. The moon's location is converted into a fraction of 1 and then
    * used to determine the angle of the line's rotation. */
    public void watchShift(double time){
        time = (time - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
        time = (1-time)*.01;
        _handle.getTransforms().add(new Rotate(((time)*(90/12)), 395, 593));
    }

    /*setShift method allows for the position of the layer to be altered according
    *to the mouse location. The xshift and yshift variables are calculated so that
    *dragging the mouse away from the center will cause the layer to shift a certain
    *amount in the opposite direction the mouse is moved.*/
    public void setShift(double x, double y){
        double xshift = -.009*(x-(Constants.SCENE_WIDTH/2));
        double yshift = -.009*(y-(Constants.SCENE_WIDTH/2));
        _vanitasView.setX(xshift);
        _vanitasView.setY(yshift);
        _handle.setTranslateX(xshift);
        _handle.setTranslateY(yshift);
    }

}
