package com.ajspeller.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OrderSummary extends AppCompatActivity {

    String orderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderSummary = getIntent().getExtras().get("orderSummary").toString();

        TextView orderSummaryTextView;
        orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(orderSummary);

    }

    public void generateEmail(View view) {
        createIntent();
    }

    private void createIntent() {

        //String subject = "Just Java Order for " + customer.getText().toString();
        String subject = "Just Java";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "not@home.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
