/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cahpx3catcher;

import static cahpx3catcher.GameController.score;
import static cahpx3catcher.GameController.seconds;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Andy
 */
public abstract class SpawnedObject{    
    
    protected static final Color GOODCOLOR = Color.GREEN;
    protected static final Color BADCOLOR = Color.RED;      
    
    private Circle fallingObj;
    private Color clr;       
   
    private int locationX;
    private int locationY;    
    
    public SpawnedObject(Color clr, int radius, int x, int y, AnchorPane a){
        fallingObj = new Circle(x, y, radius);
        fallingObj.setFill(clr);          
        this.locationX = x;
        this.locationY = y;  
        this.clr = clr;
        
        a.getChildren().add(fallingObj);       
    }
    
    public void gameOverMessage(AnchorPane a, String s){
        Label lbl = new Label(s + "\nTime: " + seconds + "\nFinal Score: " + score);
        
         a.getChildren().clear(); 
         a.getChildren().add(lbl);
         lbl.setLayoutX(a.getWidth()/3); 
         lbl.setLayoutY(a.getHeight()/3);
         lbl.setStyle("-fx-background-color: white;");
         lbl.setFont(Font.font("Verdana", 24));
         lbl.setTextAlignment(TextAlignment.CENTER);         
    }  
   
    public Circle getCircle(){
        return fallingObj;
    }
   
    public abstract void gameOver(AnchorPane a, Label l);
    
   }
    

