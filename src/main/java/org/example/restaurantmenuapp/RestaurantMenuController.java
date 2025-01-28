package org.example.restaurantmenuapp;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;

public class RestaurantMenuController {

    @FXML
    private ListView<String> menuListView;  // ListView to display menu items

    @FXML
    private TextArea orderTextArea;  // TextArea to show the order

    @FXML
    private TextArea notesTextArea;  // TextArea to add notes to the order

    @FXML
    private Label totalLabel;  // Label to display the total amount

    // Sample menu items (replace with actual data from your system)
    private List<String> menuItems = Arrays.asList(
            "Pizza - $12.99",
            "Burger - $8.99",
            "Pasta - $10.99",
            "Salad - $7.99"
    );

    @FXML
    public void initialize() {
        // Add the menu items to the ListView
        menuListView.getItems().addAll(menuItems);

        // Enable multiple selection for the menu
        menuListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        // Optional: Add listener to show the selected items immediately in the orderTextArea
        menuListView.setOnMouseClicked(this::onItemSelected);
    }

    // Event handler for when a menu item is selected
    private void onItemSelected(MouseEvent event) {
        updateOrderDetails();
    }

    // Method to update the order text area and calculate the total dynamically
    private void updateOrderDetails() {
        // Get the selected items from the ListView
        List<String> selectedItems = menuListView.getSelectionModel().getSelectedItems();

        // Build the order details as a string
        StringBuilder orderDetails = new StringBuilder("Order:\n");
        double total = 0.0;

        for (String item : selectedItems) {
            orderDetails.append(item).append("\n");
            if (item.contains("$")) {
                String priceString = item.split(" - ")[1].replace("$", "");
                total += Double.parseDouble(priceString);
            }
        }

        // Add any notes to the order
        String notes = notesTextArea.getText();
        if (!notes.isEmpty()) {
            orderDetails.append("\nNotes: ").append(notes);
        }

        // Update the order details TextArea with the selected items
        orderTextArea.setText(orderDetails.toString());

        // Update the total amount
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    @FXML
    public void generateBill() {
        // When the generate bill button is pressed, recalculate and show the bill
        double total = calculateTotal();

        // Calculate VAT (15%)
        double vat = total * 0.15;
        double totalWithVat = total + vat;

        // Format the output
        String totalText = String.format("Total (excluding VAT): $%.2f", total);
        String vatText = String.format("VAT (15%%): $%.2f", vat);
        String finalTotalText = String.format("Total (including VAT): $%.2f", totalWithVat);

        // Update the UI with the total and VAT information
        totalLabel.setText(finalTotalText);

        // Show an alert with the breakdown
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bill Breakdown");
        alert.setHeaderText("Your Total Bill (Including VAT)");
        alert.setContentText(totalText + "\n" + vatText + "\n" + finalTotalText);
        alert.showAndWait();
    }

    // Helper method to calculate the total (excluding VAT)
    private double calculateTotal() {
        double total = 0.0;
        // Get the selected items from the ListView
        List<String> selectedItems = menuListView.getSelectionModel().getSelectedItems();

        for (String item : selectedItems) {
            if (item.contains("$")) {
                String priceString = item.split(" - ")[1].replace("$", "");
                total += Double.parseDouble(priceString);
            }
        }
        return total;
    }
}
