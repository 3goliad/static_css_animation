package Cartoon;
import javafx.scene.layout.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.BlendMode;

public class Exterior {

/* This class creates the trees in the background. It has setShift method for
* parallax, and also a shadowTransition method so that the shadow cast on it
* grows larger as the moon rises. */

    private Pane _exteriorPane;
    private Rectangle2D _viewport;
    private ImageView _exteriorView;
    private Rectangle _shadowFill;
    private SVGPath _svg;
    private double _yshift;

    public Exterior (){
        _exteriorPane = new Pane();
        Image image = new Image("/placeholderExterior.png");
        _exteriorView = new ImageView();
        _exteriorView.setImage(image);
        _viewport = new Rectangle2D(0, 0, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        _exteriorView.setViewport(_viewport);
        double xLoc = 0;
        double yLoc = 0;
        double _yshift = 0;
        _shadowFill = new Rectangle(0, 213, Constants.SCENE_WIDTH, 400);
        // //This string is extremely long because multiline String literals don't work
        String exteriorPath = new String("M607,660c-2.5,0-5,0.1-7.5,0.1c-164.7,0-329.3,0-494,0c-1.2,0-2.3-0.1-3.5,0c-4.9,0.6-6.2-1.6-6.2-6.3c0.2-32.3,0.1-64.7,0.1-97c0-63.8,0-127.7,0-191.5c0-7.4,0-7.6,7.5-7.4c4.5,0.1,7.5-0.7,10.7-4.9c4.7-6.1,10.7-11.6,18.1-14.8c2.4-1.1,2.6-3.1,2.7-5.4c0.2-4.3,0.2-8.6,0.6-12.9c0.8-7.5,9.9-14,16.7-14c4.8,0,9.7-0.2,14.5,0c9.2,0.4,15.9,5.4,21,12.6c1.8,2.6,3.7,3.4,6.6,3.3c10.1-0.4,18.2,3.5,23.6,12.2c2.4,3.9,5.6,5.2,10,4.8c8.9-0.7,17.4,2.4,26.1,3.6c5.6,0.8,8.2,6.3,12,10.6c1.6-3,3.4-5.6,4.4-8.4c3.2-9.3,11.4-12,19.4-13.6c12.1-2.4,24.2-3.1,35.2,5.5c6.4,5,12.3,3.1,17-3.5c3.9-5.5,10.1-7.4,15.9-9.8c8.5-3.5,17.4-3.4,26.5-3.3c7,0.1,14.2-1.3,21.1-2.7c3.3-0.7,4.8-4.5,6.7-7.1c6.5-9.2,15.7-12.3,26.3-12.1c5.7,0.1,11.7-1.2,17.2,1.6c1.9,1,1.9-0.8,2.3-2.1c1-3.8,2.1-7.7,3.4-11.4c3.3-9.4,11.3-14.5,18.5-20.3c2.1-1.7,2.9-3.2,3-5.7c0.3-13.8,7.1-21.9,20.8-23.9c4.2-0.6,5.2-2.3,5.2-6.1c0.1-9.3,8.8-19.2,18.3-19.9c12-1,23.6,0.9,34,7.5c1.2,0.7,2.5,1.8,3.7,1.2c5.7-2.5,9.4,1.1,13.5,3.8c1.2,0.8,2.5,1.5,3.8,2.3c1.1-2.5,1.7-5,3.1-6.9c3.3-4.7,9.2-5.9,14.6-3.3c2.4,1.1,4.8,2.3,7.2,3.4C607,365.3,607,512.7,607,660z");
        _svg = new SVGPath();
        _svg.setContent(exteriorPath);
        _svg.setTranslateX(xLoc);
        _svg.setTranslateY(yLoc);
        _exteriorPane.setClip(_svg);
        shadowTransition(yLoc);
        this.setShift(xLoc, yLoc);
        _exteriorPane.getChildren().addAll(_exteriorView, _shadowFill);
    }

    public Pane getRoot() {
        return _exteriorPane;
    }

    /* This method creates the changing shadow by moving a light rectangle with
    * a multiply effect upwards as the moon moves up. The rectangle is clipped by
    * an svgPath so only its bottom edge is visible as the top of the shadow. */
    public void shadowTransition(double height){
        _shadowFill.setFill(Color.web("f7cfcf"));
        height = (height - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
        _shadowFill.setOpacity(.9);
        _shadowFill.setBlendMode(BlendMode.OVERLAY);
        _shadowFill.setTranslateY((height*(130+_yshift))-350);
    }

    /*setShift method allows for the position of the layer to be altered according
    *to the mouse location. The xshift and yshift variables are calculated so that
    *dragging the mouse away from the center will cause the layer to shift a certain
    *amount in the opposite direction the mouse is moved.*/
    public void setShift(double x, double y){
        double xshift = (-.15*(x-(Constants.SCENE_WIDTH/2)))+50;
        _yshift = (-.15*(y-(Constants.SCENE_WIDTH/2)))+50;
        _exteriorView.setX(xshift);
        _exteriorView.setY(_yshift);
        _shadowFill.setTranslateX(xshift);
        _shadowFill.setTranslateY(_yshift);
        _svg.setTranslateX(xshift);
        _svg.setTranslateY(_yshift);
        _exteriorPane.setClip(_svg);
    }

}
