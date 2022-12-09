/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartUp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/superpos/view/MainForm.fxml"));
        
        Scene mainScene = new Scene(root);
        
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("SUPER POS System");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
