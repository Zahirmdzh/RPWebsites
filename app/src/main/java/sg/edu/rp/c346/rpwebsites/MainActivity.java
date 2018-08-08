package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1, spn2;
    Button btnGo;
    ArrayList<String> aLCategory;
    ArrayAdapter<String> aaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinnerCat);
        spn2 = findViewById(R.id.spinnerSub);
        btnGo = findViewById(R.id.buttonGo);

        // initialize array list
        aLCategory= new ArrayList<>();
        // create an array adapter using default spiiner layout
        aaCategory = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,aLCategory);
        // bind array adapter to the spinner
        spn2.setAdapter(aaCategory);

spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        aLCategory.clear();

        if (pos == 0) {
            String[] strCategory = getResources().getStringArray(R.array.rpcat);
            aLCategory.addAll(Arrays.asList(strCategory));
        } else if (pos == 1){

            String[] strCategory = getResources().getStringArray(R.array.rpsoi);
            aLCategory.addAll(Arrays.asList(strCategory));
        }
        aaCategory.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});

btnGo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String url ="";

        if (spn1.getSelectedItemPosition() == 0) {
            if (spn2.getSelectedItemPosition() == 0) {
                url = "https://www.rp.edu.sg/";
            } else if (spn2.getSelectedItemPosition() == 1) {
                url = "https://www.rp.edu.sg/student-life";
            }
        } else if (spn1.getSelectedItemPosition() == 1) {
            if (spn2.getSelectedItemPosition() == 0) {
                url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
            } else if (spn2.getSelectedItemPosition() == 1) {
                url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
            }

        }
        Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
        myIntent.putExtra("url",url);
        startActivity(myIntent);
    }
});
    }
}
