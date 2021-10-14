package Calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    //level will be updated every 2 years.
    double level = 80;

    //stat vars init
    double defense = 0;
    double speed = 0;
    double crit = 0;
    double det = 0;
    double dhit = 0;
    double piety = 0;
    double tenacity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //needed for custom activities
        super.onCreate(savedInstanceState);

        //set my calculator layout I designed
        setContentView(R.layout.activity_calculator);

        //text views for every stat title. save all layout views into array
        final TextView[] views = new TextView[9];
        views[0] = findViewById(R.id.criticalchanceView);
        views[1] = findViewById(R.id.criticaldamageView);
        views[2] = findViewById(R.id.directhitchanceView);
        views[3] = findViewById(R.id.determinationdamageView);
        views[4] = findViewById(R.id.tenacityView);
        views[5] = findViewById(R.id.defensemitigationView);
        views[6] = findViewById(R.id.mpregenView);
        views[7] = findViewById(R.id.speedView);
        views[8] = findViewById(R.id.dotdamageView);
        Button calculateButton = findViewById(R.id.calculatebtn);

        //on button click = all views are updated, the edittext values are saved into vars
        calculateButton.setOnClickListener(new View.OnClickListener() {

            EditText criticalhitEdit = findViewById(R.id.criticalhitEdit);
            EditText directhitEdit = findViewById(R.id.directhitEdit);
            EditText defenseEdit = findViewById(R.id.defenseEdit);
            EditText speedEdit = findViewById(R.id.speedEdit);
            EditText determinationEdit = findViewById(R.id.determinationEdit);
            EditText pietyEdit = findViewById(R.id.pietyEdit);
            EditText tenacityEdit = findViewById(R.id.tenacityEdit);

            @Override
            public void onClick(View view) {

                //parsing all numeric values to calculate their formulas
                crit = Integer.parseInt(criticalhitEdit.getText().toString());
                dhit = Integer.parseInt(directhitEdit.getText().toString());
                defense = Integer.parseInt(defenseEdit.getText().toString());
                speed = Integer.parseInt(speedEdit.getText().toString());
                det = Integer.parseInt(determinationEdit.getText().toString());
                piety = Integer.parseInt(pietyEdit.getText().toString());
                tenacity = Integer.parseInt(tenacityEdit.getText().toString());

                //380 is base substat for lvl80(340 for det and piety). 3300 is base div stat for lvl80
                double critd =  Math.floor(200 * ( crit - 380 )/ 3300 + 1400) / 1000;
                critd = Math.floor(critd*100-100);//to %
                double critc =  Math.floor(200 * ( crit - 380 )/ 3300 + 50 ) / 10;
                double dhitc = Math.floor(550*(dhit-380)/3300)/10;
                double detd =  Math.floor(130*(det-340)/3300+1000)/1000;
                detd = Math.floor(detd*100-100);//to %
                double tend =  Math.floor(100*(tenacity-380)/3300+1000)/1000;
                tend = Math.floor(tend*100-100);//to %
                double def = Math.floor(15*defense/3300);
                double mpregen = Math.floor(((piety-340)/22));
                double speeds = Math.floor(((speed-380)*0.00397727272));
                //obtained from (difference in percentage between 4.0s gcd at
                // 380speed and 3.37s gcd at 3960speed) divided by 3960
                double dotd = Math.floor(130*(speed-380)/3300+1000)/1000;
                dotd = Math.floor(dotd*100-100);//to %

                //numeric values to string values
                String critd2 = Double.toString(critd);
                String critc2 = Double.toString(critc);
                String dhitc2 = Double.toString(dhitc);
                String detd2 = Double.toString(detd);
                String tend2 = Double.toString(tend);
                String def2 = Double.toString(def);
                String mpregen2 = Double.toString(mpregen);
                String speeds2 = Double.toString(speeds);
                String dotd2 = Double.toString(dotd);

                //update views after formula calculations
                views[0].setText(critc2 + "%");
                views[1].setText("+" + critd2 + "%");
                views[2].setText(dhitc2 + "%");
                views[3].setText("+"+detd2+"%");
                views[4].setText("+"+tend2+"%");
                views[5].setText("+"+def2+"%");
                views[6].setText("+"+mpregen2+" MP/tick");
                views[7].setText("+"+speeds2+"%");
                views[8].setText("+"+dotd2+"%");

            }
        });


    }
}
//commands to connect phone wireless using adb
//cd C:\Users\angel\AppData\Local\Android\Sdk\platform-tools
//.\adb kill-server
//.\adb usb
//.\adb tcpip 5555
//#unplugphone
//.\adb connect 192.168.1.11:5555