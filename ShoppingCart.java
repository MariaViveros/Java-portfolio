import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * This program is made with java GUI. It simulates a
 * shopping cart of a library, where you can buy one, 
 * or many books. It has the option to add or remove
 * books to the cart, clear the cart, and check out.
 */

public class ShoppingCart extends Application
{
    public static void main (String[] args) 
    {
        /**
         * @param args the command line arguments
         */
        launch(args); // Launch the application.
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        // Create a label to title the book's list
        Label pickBookLabel = new Label("Pick a Book");
        
        // Constants for the ListView sizes
        final double WIDTH = 300, HEIGHT = 400;
        
        // Create a ListView for the book's ListView
        ListView<String> bookList = new ListView<>();
        // Set size of book's listView
        bookList.setPrefSize(WIDTH, HEIGHT);
        // Set selection mode to be able to select multiple items
        bookList.getSelectionModel().setSelectionMode(
            SelectionMode.MULTIPLE);
        // Put the items in the list
        bookList.getItems().addAll("I Did It Your Way", 
            "The History of Scotland", "Learn Calculus in One Day",
            "Feel the Stress", "Great Poems", "Europe on a Shoestring",
            "The Life of Mozart");
        
        // Create a label for subtotal
        Label subtotalLabel = new Label("Subtotal: ");
        // Create a label for subtotal amount
        Label subtotalAmount = new Label("0.00");
        // Create a label for tax
        Label taxLabel = new Label("Tax: ");
        // Create a label for tax amount
        Label taxAmount = new Label("0.00");
        // Create a label for total
        Label totalLabel = new Label("Total: ");
        // Create a label for total amount
        Label totalAmount = new Label("0.00");
        
        // Create a Hbox container for the total labels
        HBox hbox1 = new HBox(10, subtotalLabel, subtotalAmount,
            taxLabel, taxAmount, totalLabel, totalAmount);
        
        // Create a VBox container for the left column
        VBox vbox1 = new VBox(10, pickBookLabel, bookList,
            hbox1);
        
        // Create the button for add to cart
        Button addCart = new Button("Add To Cart");
        
        // Create a VBox container to put the add to cart button
        VBox vbox2 = new VBox(10,addCart);
        // Align the VBpx to the center
        vbox2.setAlignment(Pos.CENTER);
        
        // Create a label to title the Shopping Cart list
        Label cartLabel = new Label("Shopping Cart");
        
        // Create a ListView for the shopping cart's ListView
        ListView<String> cartList = new ListView<>();
        // Set size of listView
        cartList.setPrefSize(WIDTH, HEIGHT);
        // Set selection mode to be able to select multiple items
        cartList.getSelectionModel().setSelectionMode(
            SelectionMode.MULTIPLE);
        
        // Create a VBox container to put the right column
        VBox vbox3 = new VBox(10, cartLabel, cartList);
        
        // Create a button to remove from cart
        Button removeButton =new Button("Remove From Cart");
        // Create a button to clear cart
        Button clearButton =new Button("Clear Cart");
        // Create a button to checkout
        Button checkoutButton =new Button("Checkout");
        
        // Create a lambda expression for events on the add to cart button
        addCart.setOnAction(event -> 
        {
            // Get the ObservableList of selected books
            ObservableList<String> booksSelected = 
                    bookList.getSelectionModel().getSelectedItems();
            // Add selected books to the cart list
            cartList.getItems().setAll(booksSelected);
        });
        
        // Create a lambda expression for events on the remove button
        removeButton.setOnAction(event -> 
        {
            // Get the ObservableList of selected books
            ObservableList<String> checkoutItems = 
                    cartList.getSelectionModel().getSelectedItems();
            // Remove all selected books
            cartList.getItems().removeAll(checkoutItems);
        });
        
        // Create a lambda expression for events on the clear button
        clearButton.setOnAction(event -> 
        {
            // Clear all items in the shopping cart
            cartList.getItems().clear();
        });
        
        // Create a lambda expression for events on the checkout button
        checkoutButton.setOnAction(event -> 
        {
            double subtotal = 0; // Create a vaiable to store the subtotal
            double tax = 0; // Create a vaiable to store the tax
            double total = 0; // Create a vaiable to store the total
            
            // Check if there are items on the shopping cart
            if(!cartList.getItems().isEmpty())
            {   
                // Create a for loop the get all the items in the cart
                for(int index = 0; index <= cartList.getItems().size(); index++)
                {
                    // Select the first item on the list
                    cartList.getSelectionModel().selectFirst();
                    // Store the first item in a string variable
                    String firstItem = cartList.getSelectionModel().getSelectedItem();

                    // Create a switch statement to get the correct price for
                    // each book
                    switch(firstItem)
                    {
                        case "I Did It Your Way": subtotal += 111.95; break;
                        case "The History of Scotland": subtotal += 214.5; break;
                        case "Learn Calculus in One Day": subtotal += 29.95; break;
                        case "Feel the Stress": subtotal += 18.5; break;
                        case "Great Poems": subtotal += 12.95; break;
                        case "Europe on a Shoestring": subtotal += 10.95; break;
                        case "The Life of Mozart": subtotal += 14.5; break;
                    }
                    
                    // Remove the first item used, to get the next item on the
                    // list in the next iteration
                    cartList.getItems().remove(firstItem);
                }
                
                // Get the tax amount for the given subtotal
                tax = subtotal * .07;
                // Get the total amount
                total = subtotal + tax;
                
                // Format for the double variables
                DecimalFormat formatNumber = new DecimalFormat("#.00");
                
                // Change the label text for the right amounts
                subtotalAmount.setText("$" + formatNumber.format(subtotal));
                taxAmount.setText("$" + formatNumber.format(tax));
                totalAmount.setText("$" + formatNumber.format(total));
            }    
        });
        
        // Create a VBox container to add the buttons in the last column
        VBox vbox4 = new VBox(10,removeButton, clearButton,
                checkoutButton);
        // Align the VBox to the center
        vbox4.setAlignment(Pos.CENTER);
        
        // Create an HBox to add all the elements
        HBox hbox = new HBox(10, vbox1, vbox2, vbox3, vbox4);
        // Set 10 px of padding around the HBox
        hbox.setPadding(new Insets(10));
        // Create a scene and add the VBox to the root node.
        Scene scene = new Scene(hbox, 880, 480);
        // Put the scene on the stage.
        primaryStage.setScene(scene);
        // Show the window
        primaryStage.show();
    }
}
