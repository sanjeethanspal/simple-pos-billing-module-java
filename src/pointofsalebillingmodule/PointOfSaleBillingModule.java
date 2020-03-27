/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsalebillingmodule;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author sanjeet
 */
public class PointOfSaleBillingModule extends Application {
    
    @Override
    public void start(Stage primaryStage){
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PoSController posController = new PoSController();
        posController.start();
    }
    
}
