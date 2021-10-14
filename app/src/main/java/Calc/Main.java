package Calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml layout of main activity
        setContentView(R.layout.activity_main);

        //Button with image
        ImageButton calcButton = findViewById(R.id.calcButton);

        //button code
        calcButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //button with intent that starts calculator class
                startActivity(new Intent(Main.this, Calculator.class));
            }
        });

    }

}

