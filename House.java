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

public class House {
/* This class creates the house in the background. It has setShift method for
* parallax, and also a shadowTransition method so that the shadow cast on it
* grows larger as the moon rises. */

    private Pane _housePane;
    private Rectangle2D _viewport;
    private ImageView _houseView;
    private Rectangle _shadowFill;
    private SVGPath _svg;
    private double _yshift;

    public House (){
        _housePane = new Pane();
        Image image = new Image("/placeholderHouse.png");
        _houseView = new ImageView();
        _houseView.setImage(image);
        _viewport = new Rectangle2D(0, 0, 600, 800);
        _houseView.setViewport(_viewport);
        double xLoc = 0;
        double yLoc = 0;
        double yshift = 0;
        _shadowFill = new Rectangle(0, 280, Constants.SCENE_WIDTH, 200);
        // This string is extremely long because multiline String literals don't work.
        String housePath = new String("M607,691c-41.6,0.1-83.3,0.1-124.9,0.2c-61.3,0-122.6,0-183.9,0c-7.5,0-8.1-0.7-8.3-8.3c-0.6-20.8-1.1-41.6-2.1-62.3c-0.3-6.5-0.8-12.9-0.8-19.4c0-3.6,0.5-7.2-3.6-9.5c-2.5-1.4-1.5-4.6-1.5-7c0-49.8,0-99.6,0-149.4c0-7.9,0-8.1-7.8-7.9c-4.8,0.1-6.2-1.5-6.4-6.3c-0.3-8.3,3.3-14,9.4-19c20.9-17.2,41.7-34.6,62.6-52c8-6.7,16-13.5,24-20.3c1.7-1.5,3.2-2,5.4-1.1c14.1,6,28.2,11.9,42.3,17.8c23.3,9.8,46.7,19.6,70,29.3c16,6.7,31.4,14.6,46.7,22.6c17.3,9,34.8,17.5,52.2,26.3c8.9,4.5,17.8,9,26.8,13.5C607,522.3,607,606.7,607,691z");
        _svg = new SVGPath();
        _svg.setContent(housePath);
        _svg.setTranslateX(xLoc);
        _svg.setTranslateY(yLoc);
        //Because the "shadow" is just a rectangle that moves up with the moon, an
        //svg is used to provide clipping for the rectangle.
        _housePane.setClip(_svg);
        shadowTransition(yLoc);
        this.setShift(xLoc, yLoc);
        _housePane.getChildren().addAll(_houseView, _shadowFill);
    }

    public Pane getRoot() {
        return _housePane;
    }

    /* This method creates the changing shadow by moving a light rectangle with
    * a multiply effect upwards as the moon moves up. The rectangle is clipped by
    * an svgPath so only its bottom edge is visible as the top of the shadow. */
    public void shadowTransition(double height){
        _shadowFill.setFill(Color.web("ffebc4"));
        height = (height - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
        _shadowFill.setOpacity(.9);
        _shadowFill.setBlendMode(BlendMode.OVERLAY);
        _shadowFill.setTranslateY((height*(280+_yshift))-350);
    }


    /*setShift method allows for the position of the layer to be altered according
    *to the mouse location. The xshift and yshift variables are calculated so that
    *dragging the mouse away from the center will cause the layer to shift a certain
    *amount in the opposite direction the mouse is moved.*/

    public void setShift(double x, double y){
        double xshift = -.1*(x-(Constants.SCENE_WIDTH/2));
        _yshift = -.1*(y-(Constants.SCENE_WIDTH/2));
        _houseView.setX(xshift+Constants.FRAME_ADJUST);
        _houseView.setY(_yshift+Constants.FRAME_ADJUST);
        _svg.setTranslateX(xshift+Constants.FRAME_ADJUST);
        _svg.setTranslateY(_yshift+Constants.FRAME_ADJUST);
        _housePane.setClip(_svg);
    }

}
