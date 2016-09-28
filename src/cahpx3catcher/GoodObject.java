/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cahpx3catcher;

import static cahpx3catcher.GameController.goodObjects;
import static cahpx3catcher.GameController.score;
import static cahpx3catcher.GameController.seconds;
import static cahpx3catcher.GameController.timeline;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Andy
 */
public class GoodObject extends SpawnedObject {
    
    public GoodObject(int radius, int locationX, int locationY, AnchorPane a, Label l) {
        super(GOODCOLOR, radius, locationX, locationY, a);  
        
        Circle c = this.getCircle();
        GoodObject self = this;
             
        c.setOnMouseClicked((MouseEvent t) -> {
            score++;
            l.setText(score.toString());
            a.getChildren().remove(c);
            goodObjects.remove(self);           
            
            if(goodObjects.isEmpty()){
                gameOver(a, l);
            }
        });        
    }  

    @Override
    public void gameOver(AnchorPane a, Label l) {
       timeline.stop();
        
        score+= (100 - seconds);
        l.setText(score.toString());
        gameOverMessage(a, "You Win!"); 
    }  
    
}
