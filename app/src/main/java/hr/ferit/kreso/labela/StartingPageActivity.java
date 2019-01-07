package hr.ferit.kreso.labela;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingPageActivity extends AppCompatActivity {

    private Button nextGameButton;
    private Button rulesButton;
    private Button exitGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); //hide app name bar

        setContentView(R.layout.activity_starting_page);

        nextGameButton=(Button)findViewById(R.id.NewGameButton);
        rulesButton=(Button)findViewById(R.id.RulesButton);
        exitGameButton=(Button)findViewById(R.id.ExitButton);

        nextGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openGame();
                }
        });

        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRules();
            }
        });

        exitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });

    }


    private void openRules() {
        goToUrl("http://www.igrajkarte.com/blog/post/belot-bela-pravila/");
    }

    private void goToUrl(String url) {
        Uri uriUrl=Uri.parse(url);
        Intent launchBrowser= new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(launchBrowser);
    }

    private void exitGame() {
        finishAffinity();
        System.exit(0);
    }

    private void openGame() {
        Intent intent=new Intent(this,GameSelectorActivity.class);
        startActivity(intent);
    }

}

