package Cartoon;
import javafx.scene.layout.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Interior {

/*The interior of the room. Primarily constructed of an image.*/

private Pane _interiorPane;
private Rectangle2D _viewport;
private ImageView _interiorView;

public Interior (){
    _interiorPane = new Pane();
    Image image = new Image("/placeholderInterior.png");
    _interiorView = new ImageView();
    _interiorView.setImage(image);
    _viewport = new Rectangle2D(0, 0, 900, 1100);
    _interiorView.setViewport(_viewport);
    double xLoc = 0;
    double yLoc = 0;
    this.setShift(xLoc, yLoc);
    _interiorPane.getChildren().addAll(_interiorView);
}

public Pane getRoot() {
    return _interiorPane;
}

/*setShift method allows for the position of the layer to be altered according
*to the mouse location. The xshift and yshift variables are calculated so that
*dragging the mouse away from the center will cause the layer to shift a certain
*amount in the opposite direction the mouse is moved.*/
public void setShift(double x, double y){
    double xshift = -.03*(x-(Constants.SCENE_WIDTH/2));
    double yshift = -.03*(y-(Constants.SCENE_WIDTH/2));
    _interiorView.setX(xshift);
    _interiorView.setY(yshift);
}

}
