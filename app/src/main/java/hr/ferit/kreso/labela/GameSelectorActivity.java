package hr.ferit.kreso.labela;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class GameSelectorActivity extends AppCompatActivity {

    RadioButton radioButton1001;
    RadioButton radioButton701;
    RadioButton radioButton501;

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
                openGameTable();
            }
        });

        radioButton701.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTable();
            }
        });

        radioButton501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTable();
            }
        });

    }

    private void openGameTable() {
        Intent intent=new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}
