package hr.ferit.kreso.labela;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class StartingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        Button nextGameButton = findViewById(R.id.NewGameButton);
        Button rulesButton = findViewById(R.id.RulesButton);
        Button exitGameButton = findViewById(R.id.ExitButton);

        // font
        TextView textView = findViewById(R.id.appName);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Sweetland.ttf");
        textView.setTypeface(custom_font);

        nextGameButton.setOnClickListener(v -> openGame());

        rulesButton.setOnClickListener(v -> openRules());

        exitGameButton.setOnClickListener(v -> exitGame());

    }


    private void openRules() {
        goToUrl("https://labela.000webhostapp.com/LaBela.html");
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void exitGame() {
        finishAffinity();
        System.exit(0);
    }

    private void openGame() {
        Intent intent = new Intent(this, GameSelectorActivity.class);
        startActivity(intent);
    }

}

