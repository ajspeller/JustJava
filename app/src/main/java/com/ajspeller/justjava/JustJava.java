package com.ajspeller.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class JustJava extends AppCompatActivity {

    private double quantity = 1;
    private EditText customer;
    private TextView quantityTextView;
    private TextView orderSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justjava);

        setup();
    }

    private void setup() {
        initializeVariables();
        createToppingsCheckBoxes();
    }

    private void createToppingsCheckBoxes() {

        LinearLayout toppingLayout = (LinearLayout) findViewById(R.id.toppingsLayout);

        for (int i = 0; i < Topping.Toppings.length; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(Topping.Toppings[i].getName() +
                    "  $" + String.format("%.2f", Topping.Toppings[i].getPrice()));
            checkBox.setTextSize(16);
            checkBox.setId((i + 1) * 1000);

            toppingLayout.addView(checkBox);
        }

    }

    public void submitOrder(View view) {
        displayMessage(summaryString());
    }

    private void initializeVariables() {

        customer = (EditText) findViewById(R.id.customer_name);

        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("1");

        orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("$0");

        RadioButton smallCoffee = (RadioButton) findViewById(R.id.coffeeSmall);
        smallCoffee.performClick();

    }

    private String summaryString() {

        String priceMessage;

        if (calculatePrice() == 0) {
            priceMessage = "Please enter a quantity greater than zero.\n\nThank you!";
        } else {
            priceMessage = createOrderSummary();
        }
        return priceMessage;
    }

    private void createIntent() {

        String subject = "Just Java Order for " + customer.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "not@home.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, summaryString());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary() {

        String summary;

        CheckBox checkBox;

        summary = "Name: " + customer.getText().toString();
        summary += "\n";
        for (int i = 0; i < Topping.Toppings.length; i++) {
            checkBox = (CheckBox) findViewById((i + 1) * 1000);
            summary += "\n" + Topping.Toppings[i].getName() + "? " + checkBox.isChecked();
        }
        summary += "\n\nQuantity: " + String.format("%.0f", quantity);
        summary += "\nTotal: $" + String.format("%.2f", calculatePrice());
        summary += "\n\nThank you!";

        return summary;
    }

    private double calculatePrice() {

        double pricePerCup;

        CheckBox checkBox;

        TextView initialPricePerCup = (TextView) findViewById(R.id.pricePerCupDisplay);

        String pricePerCupAsString = initialPricePerCup.getText().toString();

        try {
            pricePerCup = Double.parseDouble(pricePerCupAsString
                    .substring(1, pricePerCupAsString.indexOf(".")));

            for (int i = 0; i < Topping.Toppings.length; i++) {
                checkBox = (CheckBox) findViewById((i + 1) * 1000);

                if (checkBox.isChecked()) {
                    pricePerCup += Topping.Toppings[i].getPrice();
                }
            }

            return quantity * pricePerCup;

        } catch (NumberFormatException nfe) {
            Log.i("calculatePrice", "calculatePrice: " + nfe);
        }
        return -1;

    }

    private void displayQuantity(double number) {
        quantityTextView.setText(String.format(getString(R.string.new_quantity), number));
    }

    private void displayMessage(String message) {
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

    public void generateEmail(View view) {
        createIntent();
    }

    public void onSizeGroupClicked(View view) {

        TextView pricePerCup = (TextView) findViewById(R.id.pricePerCupDisplay);

        switch (view.getId()) {
            case R.id.coffeeSmall:
                pricePerCup.setText(R.string.small_coffee_price);
                break;
            case R.id.coffeeLarge:
                pricePerCup.setText(R.string.large_coffee_price);
                break;
        }
    }
}
