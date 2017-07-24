package com.ajspeller.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        if (calculatePrice() == 0) {
            priceMessage = "Please enter a quantity greater than zero.\n\nThank you!";
        } else {
            priceMessage = createOrderSummary(calculatePrice());
        }
        displayMessage(priceMessage);
    }

    private String createOrderSummary(int orderPrice) {
        String summary;

        summary = "Name: AJ Speller";
        summary += "\n\nQuantity: " + quantity;
        summary += "\nTotal: $" + orderPrice;
        summary += "\n\nThank you!";

        return summary;
    }

    private int calculatePrice() {
        return quantity * 5;
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
