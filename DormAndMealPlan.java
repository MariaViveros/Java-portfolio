import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class DormAndMealPlan extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /**
         * @param args the command line arguments
         */
        launch(args); // Launch the application.
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        Label selectDorm = new Label("Select a Dorm");
        
        ComboBox<String> dorms = new ComboBox();
        dorms.getItems().addAll("Allen Hall", "Pike Hall",
                "Farthing Hall", "University Suites");
        
        VBox dormVbox = new VBox(10, selectDorm, dorms);
        
        Label selectMeal = new Label("Select a Meal Plan");
        
        ComboBox<String> meals = new ComboBox();
        meals.getItems().addAll("7 meals per week", "14 meals per week",
                "Unlimited meals");
        
        VBox mealVbox = new VBox(10, selectMeal, meals);
        
        HBox selectHbox = new HBox(10, dormVbox, mealVbox);
        
        Label cost = new Label("Cost: ");
        
        Label costAmount = new Label("0.00");
        
        HBox costHbox = new HBox(10, cost, costAmount);
        costHbox.setAlignment(Pos.CENTER);
        
        Button calcCost = new Button("Calculate Cost");
        
        VBox costVBox = new VBox(10, costHbox, calcCost);
        costVBox.setAlignment(Pos.CENTER);
        
        calcCost.setOnAction(event -> 
        {
            double totalCost = 0;
            double dormCost = 0;
            double mealCost = 0;
            
            String dorm = dorms.getValue();
            String meal = meals.getValue();
            
            switch(dorm)
            {
                case "Allen Hall" : dormCost = 1800; break;
                case "Pike Hall" : dormCost = 2200; break;
                case "Farthing Hall" : dormCost = 2800; break;
                case "University Suites" : dormCost = 3000; break;
                default: dormCost = 0; break;
            }
            
            switch(meal)
            {
                case "7 meals per week" : mealCost = 600; break;
                case "14 meals per week" : mealCost = 1100; break;
                case "Unlimited meals" : mealCost = 1800; break;
                default: mealCost = 0; break;
            }
            
            totalCost = dormCost + mealCost;
            costAmount.setText("$" + totalCost);
        });
        
        VBox vbox = new VBox(10, selectHbox, costVBox);
        // Set 10 px of padding around the HBox
        vbox.setPadding(new Insets(10));
        // Create a scene and add the VBox to the root node.
        Scene scene = new Scene(vbox, 300, 150);
        // Put the scene on the stage.
        primaryStage.setScene(scene);
        // Show the window
        primaryStage.show();
    }
}
