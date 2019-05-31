package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numofcoff = 1;
    CheckBox whippedCreamCheckBox, chocolateCheckBox;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        chocolateCheckBox = findViewById(R.id.checkbox_chocolate);
        editTextName = findViewById(R.id.editText_name);
    }

    public void numminus(View view) {
        if (numofcoff > 1)
            numofcoff--;
        display(numofcoff);
    }

    public void numplus(View view) {
        if (numofcoff < 100)
            numofcoff++;
        display(numofcoff);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        if (numofcoff > 0)
            displayPrice(price);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ss03335550@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order of coffee");
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    public void display(int num) {
        TextView numtext = findViewById(R.id.textView_number);
        numtext.setText("" + num);

    }

    private String createOrderSummary(int num) {
        String str;
        str = "Name:";
        str += editTextName.getText().toString() + "\n";
        if (whippedCreamCheckBox.isChecked())
            str = str + "Add whipped cream?true\n";
        else
            str = str + "Add whipped cream?false\n";
        if (chocolateCheckBox.isChecked())
            str = str + "Add chocolate?true\n";
        else
            str = str + "Add chocolate?false\n";
        str = str + "Quantity:" + numofcoff + "\nTotal:$" + num + "\nThank You!";
        return (str);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(createOrderSummary(number));
    }

    private int calculatePrice() {
        int basePrice = numofcoff * 5;
        if (whippedCreamCheckBox.isChecked())
            basePrice += numofcoff * 1;
        if (chocolateCheckBox.isChecked())
            basePrice += numofcoff * 2;
        return (basePrice);
    }

}
