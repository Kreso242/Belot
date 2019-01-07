package hr.ferit.kreso.labela;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.hardware.input.InputManager;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class InsertResultActivity extends AppCompatActivity implements Call_dialog.Call_dialogListener{

    private EditText editTextWe,editTextThem;
    private ToggleButton toggleButton20We,toggleButton50We;
    private ToggleButton toggleButton90We,toggleButton100We;
    private ToggleButton toggleButton150We,toggleButton200We;
    private ToggleButton toggleButton20Them,toggleButton50Them;
    private ToggleButton toggleButton90Them,toggleButton100Them;
    private ToggleButton toggleButton150Them,toggleButton200Them;
    private ToggleButton toggleButton40We,toggleButton40Them;
    private ToggleButton toggleButton80We,toggleButton80Them;
    private Button doneButton;
    private int callSumWe=0,callSumThem=0,sumWe=0,sumThem=0,upTo;
    private Context context;
    private boolean weChecked=false,themChecked=false,fallWe=false,fallThem=false,stiglja=false;
    public ArrayList<Integer> pointsWe;
    public ArrayList<Integer> pointsThem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide app name bar
        setContentView(R.layout.activity_insert_result);

        openDialog();

        Intent intent = getIntent();
        pointsWe =  intent.getExtras().getIntegerArrayList("pointsArrayWe");
        pointsThem =  intent.getExtras().getIntegerArrayList("pointsArrayThem");
        upTo=intent.getIntExtra("upTo",upTo);

        editTextWe=(EditText)findViewById(R.id.editTextWe);
        editTextThem=(EditText)findViewById(R.id.editTextThem);
        editTextWe.setFilters(new InputFilter[]{new InputFilterMinMax("0","162")});
        editTextThem.setFilters(new InputFilter[]{new InputFilterMinMax("0","162")});
        editTextWe.setText("");
        editTextThem.setText("");

        doneButton=(Button)findViewById(R.id.doneButton);

        toggleButton20We=(ToggleButton)findViewById(R.id.toggleButton20We);
        toggleButton40We=(ToggleButton)findViewById(R.id.toggleButton40We);
        toggleButton50We=(ToggleButton)findViewById(R.id.toggleButton50We);
        toggleButton80We=(ToggleButton)findViewById(R.id.toggleButton80We);
        toggleButton90We=(ToggleButton)findViewById(R.id.toggleButton90We);
        toggleButton100We=(ToggleButton)findViewById(R.id.toggleButton100We);
        toggleButton150We=(ToggleButton)findViewById(R.id.toggleButton150We);
        toggleButton200We=(ToggleButton)findViewById(R.id.toggleButton200We);

        toggleButton20Them=(ToggleButton)findViewById(R.id.toggleButton20Them);
        toggleButton40Them=(ToggleButton)findViewById(R.id.toggleButton40Them);
        toggleButton50Them=(ToggleButton)findViewById(R.id.toggleButton50Them);
        toggleButton80Them=(ToggleButton)findViewById(R.id.toggleButton80Them);
        toggleButton90Them=(ToggleButton)findViewById(R.id.toggleButton90Them);
        toggleButton100Them=(ToggleButton)findViewById(R.id.toggleButton100Them);
        toggleButton150Them=(ToggleButton)findViewById(R.id.toggleButton150Them);
        toggleButton200Them=(ToggleButton)findViewById(R.id.toggleButton200Them);



        final TextWatcher textWatcherWe = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextWe.getText().toString().matches("")) {
                    editTextThem.setText(String.valueOf(162));
                } else
                    editTextThem.setText(String.valueOf(162 - Integer.parseInt(editTextWe.getText().toString())));
            }
        };
        editTextWe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    editTextWe.addTextChangedListener(textWatcherWe);
                }
                else
                    editTextWe.removeTextChangedListener(textWatcherWe);
            }
        });

        final TextWatcher textWatcherThem = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextThem.getText().toString().matches("")) {
                    editTextWe.setText(String.valueOf(162));
                } else
                    editTextWe.setText(String.valueOf(162 - Integer.parseInt(editTextThem.getText().toString())));
            }
        };

        editTextThem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    editTextThem.addTextChangedListener(textWatcherThem);
                }
                else
                    editTextThem.removeTextChangedListener(textWatcherThem);
            }
        });



        toggleButton20We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+20;
                }
                else{
                    callSumWe=callSumWe-20;
                }
            }
        });

        toggleButton40We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+40;
                }
                else{
                    callSumWe=callSumWe-40;
                }
            }
        });

        toggleButton50We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+50;
                }
                else{
                    callSumWe=callSumWe-50;
                }
            }
        });

        toggleButton80We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+80;
                }
                else{
                    callSumWe=callSumWe-80;
                }
            }
        });

        toggleButton90We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+90;
                }
                else{
                    callSumWe=callSumWe-90;
                }
            }
        });

        toggleButton100We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+100;
                }
                else{
                    callSumWe=callSumWe-100;
                }
            }
        });

        toggleButton150We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+150;
                }
                else{
                    callSumWe=callSumWe-150;
                }
            }
        });

        toggleButton200We.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumWe=callSumWe+200;
                }
                else{
                    callSumWe=callSumWe-200;
                }
            }
        });

        toggleButton20Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+20;
                }
                else{
                    callSumThem=callSumThem-20;
                }
            }
        });

        toggleButton40Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+40;
                }
                else{
                    callSumThem=callSumThem-40;
                }
            }
        });

        toggleButton50Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+50;
                }
                else{
                    callSumThem=callSumThem-50;
                }
            }
        });

        toggleButton80Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+80;
                }
                else{
                    callSumThem=callSumThem-80;
                }
            }
        });

        toggleButton90Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+90;
                }
                else{
                    callSumThem=callSumThem-90;
                }
            }
        });

        toggleButton100Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+100;
                }
                else{
                    callSumThem=callSumThem-100;
                }
            }
        });

        toggleButton150Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+150;
                }
                else{
                    callSumThem=callSumThem-150;
                }
            }
        });

        toggleButton200Them.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    callSumThem=callSumThem+200;
                }
                else{
                    callSumThem=callSumThem-200;
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stiglja=false;
                if(editTextWe.getText().toString().trim().length()==0 || editTextThem.getText().toString().trim().length()==0){
                    Toast.makeText(getApplicationContext(),"Enter result!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Try again!",Toast.LENGTH_SHORT).show();
                }
                   else{
                    if(Integer.parseInt(editTextWe.getText().toString())==0){
                        sumThem=162+90+callSumThem+callSumWe;
                        stiglja=true;
                        Toast.makeText(getApplicationContext(),"It seems that 'WE' are big losers",Toast.LENGTH_SHORT).show();
                    }

                    else if(Integer.parseInt(editTextThem.getText().toString())==0){
                        sumWe=162+90+callSumThem+callSumWe;
                        stiglja=true;
                        Toast.makeText(getApplicationContext(),"It seems that 'THEM' are big losers",Toast.LENGTH_SHORT).show();

                    }

                    else{
                    sumWe=sumOfAllWe();
                    sumThem=sumOfAllThem();
                    }

                    if(weChecked && sumWe<((sumWe+sumThem)/2+1) && !stiglja) {
                        sumThem=sumThem+sumWe;
                        sumWe=0;
                        Toast.makeText(getApplicationContext(),"It seems that 'WE' are losers",Toast.LENGTH_SHORT).show();
                        goBackToTable(sumWe + sumThem);
                    }
                    else if(themChecked && sumThem<((sumWe+sumThem)/2+1) &&!stiglja) {
                        sumWe=sumWe+sumThem;
                        sumThem=0;
                        Toast.makeText(getApplicationContext(),"It seems that 'THEM' are losers",Toast.LENGTH_SHORT).show();
                        goBackToTable(sumWe + sumThem);
                    }

                    else
                    goBackToTable(sumWe+sumThem);
                }
            }
        });
    }

    private int sumOfAllWe() {
        sumWe=sumWe+Integer.parseInt(editTextWe.getText().toString())+callSumWe;
        return sumWe;

    }

    private int sumOfAllThem() {
        sumThem=sumThem+Integer.parseInt(editTextThem.getText().toString())+callSumThem;
            return sumThem;
    }


    private void goBackToTable(int sum) {

                pointsWe.add(sumWe);
                pointsThem.add(sumThem);
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("emptyList", false);
                intent.putExtra("we", pointsWe);
                intent.putExtra("them", pointsThem);
                intent.putExtra("upTo", upTo);
                startActivity(intent);


    }

    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Click done!",Toast.LENGTH_SHORT).show();
    }

    private void openDialog() {
        Call_dialog call_dialog=new Call_dialog();
        call_dialog.setCancelable(false);
        call_dialog.show(getSupportFragmentManager(),"tag");
    }

    @Override
    public void applySelected(String data) {
        if(data.matches("WE"))
            weChecked=true;
        if(data.matches("THEM"))
            themChecked=true;
    }
}
