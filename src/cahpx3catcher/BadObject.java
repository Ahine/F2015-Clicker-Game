/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cahpx3catcher;


import static cahpx3catcher.GameController.timeline;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Andy
 */
public class BadObject extends SpawnedObject {  

    public BadObject(int radius, int locationX, int locationY, AnchorPane a, Label l) {
        super(BADCOLOR, radius, locationX, locationY, a);
        
        this.getCircle().setOnMouseEntered((MouseEvent t) -> {
            gameOver(a, l);
        });        
    }
    
    @Override
    public void gameOver(AnchorPane a, Label l){
        timeline.stop();
         a.getChildren().clear(); 
        gameOverMessage(a, "You Lose!");       
   }     
}
