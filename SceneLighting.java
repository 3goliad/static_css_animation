package Cartoon;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SceneLighting {

/*This class is a transparent rectangle that grows less transparent as the moon
* rises, in order to create a shift in the light as night falls. Thus, its Primary
* method is lightTransition.*/

private Pane _sceneLightingPane;

public SceneLighting (){
    _sceneLightingPane = new Pane();
    Rectangle _lighting = new Rectangle(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    _lighting.setFill(Color.web("1e3039", 0.5));
    double _opacity = Constants.SUN_FLOOR;
    this.lightTransition(_opacity);
    _sceneLightingPane.getChildren().addAll(_lighting);
}

public Pane getRoot() {
    return _sceneLightingPane;
}

/*This method is called by the moonhander. The location of the moon is converted
* into a fraction of 1, and then used to determine the pane's opacity.*/
public void lightTransition(double opacity){
    opacity = (opacity - Constants.SUN_FLOOR) / Constants.SUN_RANGE;
    _sceneLightingPane.setOpacity(1-opacity);
}

}
