package com.example.android.justjava; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int numberOfCofees = 1;

    public void submitOrder(View view) {
        display(numberOfCofees);
        String summary = calculateOrderSummary();
        displayPrice(summary);
      //  composeEmail(summary);
    }

    public String Edit() {
        EditText nameEditText = (EditText) findViewById(R.id.name_text);
        String name = nameEditText.getText().toString();
        return name;
    }

//    public void composeEmail(String text) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_TEXT, text);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee summary for  " + Edit() );
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }


    public void increment(View view) {
        numberOfCofees=numberOfCofees+1;
        display( numberOfCofees);
    }
    
    public void decrement(View view) {
        if(numberOfCofees<=1 ) {
            Toast.makeText(this,"You cannot order less than one coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCofees=numberOfCofees-1;
        display(numberOfCofees);
    }

    public int BasePrice() {
        int cost = numberOfCofees * 5;
        return cost;
    }

    public String calculateOrderSummary() {
        String name = Edit();
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\n Quantity: " + numberOfCofees;
        int price = BasePrice();
        if(checkForWhipedCream()== true && checkForChocolate() == true) {
            priceMessage = priceMessage + "\n Whipped Cream added";
            priceMessage = priceMessage + "\n Chocolate added";
            priceMessage = priceMessage + "\n Total: " + (price+(numberOfCofees*5)) + "$";
            priceMessage = priceMessage + "\n Thank You!"; }

        if(checkForWhipedCream() == true && checkForChocolate() == false) {
            priceMessage = priceMessage + "\n Whipped Cream added";
            priceMessage = priceMessage + "\n Total: " + (price+(numberOfCofees*2)) + "$";
            priceMessage = priceMessage + "\n Thank You!";
        }

        if(checkForWhipedCream()== false && checkForChocolate() == true) {
            priceMessage = priceMessage + "\n Chocolate added";
            priceMessage = priceMessage + "\n Total: " + (price+(numberOfCofees*3)) + "$";
            priceMessage = priceMessage + "\n Thank You!"; }

        if(checkForWhipedCream()== false && checkForChocolate() == false) {
            priceMessage = priceMessage + "\n Total: " + price + "$";
            priceMessage = priceMessage + "\n Thank You!"; }

        return priceMessage;
    }

    public boolean checkForWhipedCream() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whippedCream_checkbox);
        boolean hasWhippedCream = checkBox.isChecked();
        return hasWhippedCream;
    }

    public boolean checkForChocolate() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkBox.isChecked();
        return hasChocolate;
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int noOfCofees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + noOfCofees);
    }
    /**
     * This method displays the given price on the screen.
     */
    private String displayPrice(String  priceMessage) {
        TextView priceTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        priceTextView.setText(priceMessage);
        priceTextView.setTextSize(22);
        return(priceMessage);
    }

}


