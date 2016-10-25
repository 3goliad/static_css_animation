package Cartoon;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkyLighting {

private Pane _skyLightingPane;
private ImageView _cloudsView;
private Rectangle2D _viewport;
private Rectangle _sky;
private double _xshift;

/* This class provides the gradient background for the sky, and also the clouds.*/

public SkyLighting (){
        _skyLightingPane = new Pane();
        Image image = new Image("/clouds.png");
        _cloudsView = new ImageView();
        _cloudsView.setImage(image);
        _viewport = new Rectangle2D(0, 0, 440, 54);
        _cloudsView.setViewport(_viewport);
        _sky = new Rectangle(Constants.SCENE_WIDTH+200, Constants.SCENE_HEIGHT);
        Stop[] stops = new Stop[] {new Stop(0, Color.web("33505d")), new Stop(.3, Color.web("8d929f")),
         new Stop(.5, Color.web("da94bd")), new Stop(.6, Color.web("f29a82"))};
        LinearGradient skyGradient = new LinearGradient(0, 0, 0, .8, true, CycleMethod.NO_CYCLE, stops);
        _sky.setFill(skyGradient);
        double _opacity = Constants.SUN_FLOOR;
        double xLoc = 0;
        double yLoc = 0;
        double _xshift = 0;
        this.skyTransition(_opacity);
        this.cloudMove(_opacity);
        this.setShift(xLoc, yLoc);
        _skyLightingPane.getChildren().addAll(_sky, _cloudsView);
    }

    public Pane getRoot() {
        return _skyLightingPane;
    }

    /* This method moves the clouds depending on the moon's location. It is called by
    * the moonhandler. The moon location is converted intro a fraction of 1 and then
    * multipled by the cloud location. Also, the parallax xshift of clouds is taken
    * into account, so they can move on their own accord while also remaining aligned
    * in parallax. */
    public void cloudMove(double x){
        x = (x - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
        _cloudsView.setX((x*(400+ _xshift))-300);
    }

    /*Similar to the method in SceneLighting, this controls the opacity of the gradient
    * depending on the moon's location.*/
    public void skyTransition(double opacity){
        opacity = (opacity - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
        _skyLightingPane.setOpacity(opacity);
    }

    /*setShift method allows for the position of the layer to be altered according
    *to the mouse location. The xshift and yshift variables are calculated so that
    *dragging the mouse away from the center will cause the layer to shift a certain
    *amount in the opposite direction the mouse is moved.*/
    public void setShift(double x, double y){
        _xshift = -.3*(x-(Constants.SCENE_WIDTH/2));
        double yshift = -.3*(y-(Constants.SCENE_WIDTH/2));
        _cloudsView.setX(_xshift+Constants.FRAME_ADJUST);
        _cloudsView.setY(yshift+380);
        _sky.setX(_xshift);
        _sky.setY(yshift);
    }

}
