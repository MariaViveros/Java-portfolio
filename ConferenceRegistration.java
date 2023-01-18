import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;


public class ConferenceRegistration extends Application
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
        double GENERAL_COST = 895;
        double STUDENT_COST = 495;
        double DINNER = 30;
        double E_COMMERCE = 295;
        double FUTURE_WEB = 295;
        double JAVA = 395;
        double NET_SECURITY = 395;
        
        RadioButton generalReg = new RadioButton("General Registration");
        RadioButton studentReg = new RadioButton("Student Registration");
        
        // Create a ToggleGroup for the RadioButtons
        ToggleGroup registration = new ToggleGroup();
        
        //Add the RadioButtons to the toggle group
        generalReg.setToggleGroup(registration);
        studentReg.setToggleGroup(registration);
        
        VBox vbox1 = new VBox(10, generalReg, studentReg);
        
        CheckBox nightDinner = new CheckBox("Opening Night Dinner");
        
        Label selectWorkshop = new Label("Select an Option Workshop");
        
        // Create the LostView for the workshops
        ListView<String> workshopsList = new ListView<>();
        // Add items to the list
        workshopsList.getItems().addAll("Introduction to E-commerce",
                "The Future of the Web", "Advanced Java Programming",
                "Network Security");
        workshopsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        VBox vbox2 = new VBox(10, selectWorkshop, workshopsList);
        
        Label cost = new Label("Cost: ");
        
        Label totalCost = new Label("$0.00");
        
        HBox hbox1 = new HBox(cost, totalCost);
        hbox1.setAlignment(Pos.CENTER);
        
        Button calculateCost = new Button("Calculate Cost");
        calculateCost.setOnAction(event -> 
        {
            double total = 0;
            
            if(generalReg.isSelected())
                total += GENERAL_COST;
            else if(studentReg.isSelected())
                total += STUDENT_COST;
            
            if(nightDinner.isSelected())
                total += DINNER;
            
            ObservableList<Integer> u = workshopsList.getSelectionModel().getSelectedIndices();
            for(int i : u)
            {
                switch(i)
                {
                    case 0: total += E_COMMERCE; break;
                    case 1: total += FUTURE_WEB; break;
                    case 2: total += JAVA; break;
                    case 3: total += NET_SECURITY; break;
                }
            }

            totalCost.setText("$" + total);
        });
        
        // Create a VBox container and add the labels, text field, and button 
        // with 10px of spacing between the controls
        VBox vbox = new VBox(10, vbox1, nightDinner, vbox2, hbox1, calculateCost);
        // Align the VBox to the center
        vbox.setAlignment(Pos.CENTER);
        // Set 10 px of padding around the VBox
        vbox.setPadding(new Insets(10));
        // Create a scene and add the VBox to the root node.
        Scene scene = new Scene(vbox, 250, 300);
        // Put the scene on the stage.
        primaryStage.setScene(scene);
        // Show the window
        primaryStage.show();
    }
    
}
