import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * This program is made with java GUI. It asks the
 * user to put  an amount charged by the restaurant,
 * and it calculates a 20% tip by clicking a button.
 */

public class TipCalculator extends Application 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        // Create a label to ask for the restaurant charge
        Label charge = new Label("Restaurant Charge: ");
        // Create a text field for the user's input
        TextField total = new TextField();
        // Create a button to calculate tip
        Button calculate = new Button("Calculate Tip");
        // Create a label to show the amount to tip
        Label tipAmount = new Label("Amount to tip: ");
        // Create an empty label to display the calculated tip
        Label tip = new Label("");
        
        // Event Handler using Lambda expressions, it executes 
        // when the button is clicked. It calculates the 20%
        // of the amount given by the user.
        calculate.setOnAction(event ->
        {
            // Initiate a float variable to store the tip
            float calculatedTip = 0;
            // Create a float variable to get the amount the user's input
            float chargeAmount = Float.parseFloat(total.getText());
            // Calculate 20% of the user's input
            calculatedTip = (chargeAmount * 20)/100;
            // Display the calculated tip in the tip label
            tip.setText("$" + calculatedTip);
        });
        
        // Create a VBox container and add the labels, text field, and button 
        // with 10px of spacing between the controls
        VBox vbox = new VBox(10, charge, total, calculate, tipAmount, tip);
        // Align the VBox to the center
        vbox.setAlignment(Pos.CENTER);
        // Set 10 px of padding around the VBox
        vbox.setPadding(new Insets(10));
        // Create a scene and add the VBox to the root node.
        Scene scene = new Scene(vbox, 200, 200);
        // Put the scene on the stage.
        primaryStage.setScene(scene);
        // Show the window
        primaryStage.show();
    }
    
}
