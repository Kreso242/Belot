package hr.ferit.kreso.labela;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class GameSelectorActivity extends AppCompatActivity {

    private RadioButton radioButton1001;
    private RadioButton radioButton701;
    private RadioButton radioButton501;
    private int upTo1001=1001,upTo701=701,upTo501=501;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide app name bar
        setContentView(R.layout.activity_game_selector);

        radioButton1001=(RadioButton)findViewById(R.id.radioButton1001);
        radioButton701=(RadioButton)findViewById(R.id.radioButton701);
        radioButton501=(RadioButton)findViewById(R.id.radioButton501);

        radioButton1001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTable(upTo1001);
            }
        });

        radioButton701.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTable(upTo701);
            }
        });

        radioButton501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTable(upTo501);
            }
        });

    }

    private void openGameTable(int upTo) {
        Intent intent=new Intent(this,GameActivity.class);
        intent.putExtra("empty", false);
        intent.putExtra("upTo",upTo);
        startActivity(intent);
    }
}
