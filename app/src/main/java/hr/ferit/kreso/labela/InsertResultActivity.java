package hr.ferit.kreso.labela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Objects;

import io.realm.Realm;

public class InsertResultActivity extends AppCompatActivity implements Call_dialog.Call_dialogListener {

    private EditText editTextWe, editTextThem;
    private int callSumWe = 0, callSumThem = 0, sumWe = 0, sumThem = 0, upTo;
    private boolean weChecked = false, themChecked = false, stiglja = false;
    public ArrayList<Integer> pointsWe;
    public ArrayList<Integer> pointsThem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar
        setContentView(R.layout.activity_insert_result);

        openDialog();

        Intent intent = getIntent();
        pointsWe = Objects.requireNonNull(intent.getExtras()).getIntegerArrayList("pointsArrayWe");
        pointsThem = intent.getExtras().getIntegerArrayList("pointsArrayThem");
        upTo = intent.getIntExtra("upTo", upTo);

        editTextWe = findViewById(R.id.editTextWe);
        editTextThem = findViewById(R.id.editTextThem);
        editTextWe.setFilters(new InputFilter[]{new InputFilterMinMax("0", "162")});
        editTextThem.setFilters(new InputFilter[]{new InputFilterMinMax("0", "162")});
        editTextWe.setText("");
        editTextThem.setText("");

        Button doneButton = findViewById(R.id.doneButton);

        ToggleButton toggleButton20We = findViewById(R.id.toggleButton20We);
        ToggleButton toggleButton40We = findViewById(R.id.toggleButton40We);
        ToggleButton toggleButton50We = findViewById(R.id.toggleButton50We);
        ToggleButton toggleButton80We = findViewById(R.id.toggleButton80We);
        ToggleButton toggleButton90We = findViewById(R.id.toggleButton90We);
        ToggleButton toggleButton100We = findViewById(R.id.toggleButton100We);
        ToggleButton toggleButton150We = findViewById(R.id.toggleButton150We);
        ToggleButton toggleButton200We = findViewById(R.id.toggleButton200We);

        ToggleButton toggleButton20Them = findViewById(R.id.toggleButton20Them);
        ToggleButton toggleButton40Them = findViewById(R.id.toggleButton40Them);
        ToggleButton toggleButton50Them = findViewById(R.id.toggleButton50Them);
        ToggleButton toggleButton80Them = findViewById(R.id.toggleButton80Them);
        ToggleButton toggleButton90Them = findViewById(R.id.toggleButton90Them);
        ToggleButton toggleButton100Them = findViewById(R.id.toggleButton100Them);
        ToggleButton toggleButton150Them = findViewById(R.id.toggleButton150Them);
        ToggleButton toggleButton200Them = findViewById(R.id.toggleButton200Them);


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
        editTextWe.setOnFocusChangeListener((view, b) -> {
            if (b) {
                editTextWe.addTextChangedListener(textWatcherWe);
            } else
                editTextWe.removeTextChangedListener(textWatcherWe);
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

        editTextThem.setOnFocusChangeListener((view, b) -> {
            if (b) {
                editTextThem.addTextChangedListener(textWatcherThem);
            } else
                editTextThem.removeTextChangedListener(textWatcherThem);
        });


        toggleButton20We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 20;
            } else {
                callSumWe = callSumWe - 20;
            }
        });

        toggleButton40We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 40;
            } else {
                callSumWe = callSumWe - 40;
            }
        });

        toggleButton50We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 50;
            } else {
                callSumWe = callSumWe - 50;
            }
        });

        toggleButton80We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 80;
            } else {
                callSumWe = callSumWe - 80;
            }
        });

        toggleButton90We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 90;
            } else {
                callSumWe = callSumWe - 90;
            }
        });

        toggleButton100We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 100;
            } else {
                callSumWe = callSumWe - 100;
            }
        });

        toggleButton150We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 150;
            } else {
                callSumWe = callSumWe - 150;
            }
        });

        toggleButton200We.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumWe = callSumWe + 200;
            } else {
                callSumWe = callSumWe - 200;
            }
        });

        toggleButton20Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 20;
            } else {
                callSumThem = callSumThem - 20;
            }
        });

        toggleButton40Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 40;
            } else {
                callSumThem = callSumThem - 40;
            }
        });

        toggleButton50Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 50;
            } else {
                callSumThem = callSumThem - 50;
            }
        });

        toggleButton80Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 80;
            } else {
                callSumThem = callSumThem - 80;
            }
        });

        toggleButton90Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 90;
            } else {
                callSumThem = callSumThem - 90;
            }
        });

        toggleButton100Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 100;
            } else {
                callSumThem = callSumThem - 100;
            }
        });

        toggleButton150Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 150;
            } else {
                callSumThem = callSumThem - 150;
            }
        });

        toggleButton200Them.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                callSumThem = callSumThem + 200;
            } else {
                callSumThem = callSumThem - 200;
            }
        });

        doneButton.setOnClickListener(v -> {
            stiglja = false;
            if (editTextWe.getText().toString().trim().length() == 0 || editTextThem.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Unesi rezultat!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Pokušaj ponovo!", Toast.LENGTH_SHORT).show();
            } else {
                if (Integer.parseInt(editTextWe.getText().toString()) == 0) {
                    sumThem = 162 + 90 + callSumThem + callSumWe;
                    stiglja = true;
                    String emoji=getEmojiByUnicode(0x1F605);
                    Toast.makeText(getApplicationContext(), "Štiglja, 'MI' morate početi igrati" + emoji, Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(editTextThem.getText().toString()) == 0) {
                    sumWe = 162 + 90 + callSumThem + callSumWe;
                    stiglja = true;
                    String emoji=getEmojiByUnicode(0x1F605);
                    Toast.makeText(getApplicationContext(), "Štiglja, 'VI' morate početi igrati" + emoji, Toast.LENGTH_SHORT).show();

                } else {
                    sumWe = sumOfAllWe();
                    sumThem = sumOfAllThem();
                }

                if (weChecked && sumWe < ((sumWe + sumThem) / 2 + 1) && !stiglja) {
                    sumThem = sumThem + sumWe;
                    sumWe = 0;
                    String emoji=getEmojiByUnicode(0x1F605);
                    Toast.makeText(getApplicationContext(), "'MI' su pali, trgnite se malo" + emoji, Toast.LENGTH_SHORT).show();
                    goBackToTable();
                } else if (themChecked && sumThem < ((sumWe + sumThem) / 2 + 1) && !stiglja) {
                    sumWe = sumWe + sumThem;
                    sumThem = 0;
                    String emoji=getEmojiByUnicode(0x1F605);
                    Toast.makeText(getApplicationContext(), "'VI' su pali, trgnite se malo" + emoji, Toast.LENGTH_SHORT).show();
                    goBackToTable();
                } else
                    goBackToTable();
            }
        });
    }

    private int sumOfAllWe() {
        sumWe = sumWe + Integer.parseInt(editTextWe.getText().toString()) + callSumWe;
        return sumWe;

    }

    private int sumOfAllThem() {
        sumThem = sumThem + Integer.parseInt(editTextThem.getText().toString()) + callSumThem;
        return sumThem;
    }

    private void goBackToTable() {

        pointsWe.add(sumWe);
        pointsThem.add(sumThem);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        GameItem gameItem = new GameItem(sumWe, sumThem);
        realm.copyToRealm(gameItem);
        realm.commitTransaction();
        realm.close();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("emptyList", false);
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Pritisni 'UREDU'!", Toast.LENGTH_SHORT).show();
    }

    private void openDialog() {
        Call_dialog call_dialog = new Call_dialog();
        call_dialog.setCancelable(false);
        call_dialog.show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void applySelected(String data) {
        if (data.matches("MI"))
            weChecked = true;
        if (data.matches("VI"))
            themChecked = true;
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
