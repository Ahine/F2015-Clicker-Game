/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cahpx3catcher;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Andy
 */
public class GameController extends Switchable implements Initializable {
    @FXML
    private AnchorPane gamePane;
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private Button playBtn;
    
    @FXML
    private Button resetBtn;
    
    @FXML
    private ImageView background;
   
    static ArrayList<GoodObject> goodObjects;
    static ArrayList<BadObject> badObjects;
    
    static Integer score = 0;    
    static Timeline timeline;
    static Integer seconds;
    
    private final int minRadius = 5;    
    private final int spawn = 25;   
    
    Random ran = new Random();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        goodObjects = new ArrayList<>();
        badObjects = new ArrayList<>();
        
        gamePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0);");
        
         timeline = new Timeline(new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {
                    seconds++;    
                    timeLabel.setText(seconds.toString());
                })
        );
        
        // TODO
    }    

    
    @FXML
    public void startGame(){        
        timeline.stop();   //cant call reset because we want to keep the image     
        score = 0;
        seconds = 0;
        gamePane.getChildren().clear();
        goodObjects.clear();
        badObjects.clear();
        scoreLabel.setText(score.toString());
        timeLabel.setText(seconds.toString());
        
        if(goodObjects.isEmpty()){       
            
        int rad, x, y;       
        
        for(int i = 0; i<spawn; i++){
            rad = ran.nextInt(30) + minRadius;
            x = (ran.nextInt((int) (gamePane.getWidth()-rad)) );
            y = (ran.nextInt((int) (gamePane.getHeight()-rad)) );
            
            if((i%3) != 0)            
                goodObjects.add(new GoodObject(rad, x, y, gamePane, scoreLabel)); 
            else
                badObjects.add(new BadObject(rad, x, y, gamePane, scoreLabel));
        }
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();       
        }
    }       
    
    @FXML
    public void resetAll(){
        
        timeline.stop();         
        score = 0;
        seconds = 0;
        gamePane.getChildren().clear();
        goodObjects.clear();
        badObjects.clear();
        scoreLabel.setText(score.toString());
        timeLabel.setText(seconds.toString());
        background.setImage(null);
        
    }
  
    @FXML
    public void showAbout(){
     this.getSceneManager().switchTo("about");
    }
    
    @FXML
    public void uploadBackground() throws IOException{          
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extJpg = new ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extPng = new ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extJpg, extPng);
        
        Stage stage = (Stage)root.getScene().getWindow();       
        File file = fileChooser.showOpenDialog(stage);
                        
        if(file != null){       
        
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);                
                background.setImage(image); 
                
            } catch (IOException ex) {
                Alert alert = new Alert(AlertType.ERROR, "Could not load Image!");
                alert.showAndWait();                                  
            }
        }      
    }

    
    
    
    
    
     
    
    
    

  
    
}
