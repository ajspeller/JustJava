package com.ajspeller.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView priceTextView;
    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText("$0");
    }

    public void submitOrder(View view) {
        String priceMessage;

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();

        EditText userName = (EditText) findViewById(R.id.customer_name);
        String customer = userName.getText().toString();

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        if (totalPrice == 0) {
            priceMessage = "Please enter a quantity greater than zero.\n\nThank you!";
        } else {
            priceMessage = createOrderSummary(totalPrice, hasWhippedCream, hasChocolate,
                    customer);
        }
        displayMessage(priceMessage);
    }

    private String createOrderSummary(int orderPrice, boolean addWhippedCream,
                                      boolean addChocolate, String client) {

        String summary;

        summary = "Name: " + client;
        summary += "\n\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\n\nQuantity: " + quantity;
        summary += "\nTotal: $" + orderPrice;
        summary += "\n\nThank you!";

        return summary;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int pricePerCup = 5;

        if (addWhippedCream) {
            pricePerCup += 1;
        }

        if (addChocolate) {
            pricePerCup += 2;
        }
        return quantity * pricePerCup;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.format(getString(R.string.new_quantity), number));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
            return;
        }
        displayQuantity(quantity);
    }

    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }
}
