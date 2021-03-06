package hr.ferit.kreso.labela;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;

import io.realm.Realm;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide status/notification bar (fullscreen)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar
        Realm.init(this);

        setContentView(R.layout.activity_splash_screen);

        // font
        TextView textView = findViewById(R.id.appName);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Sweetland.ttf");
        textView.setTypeface(custom_font);

        new Handler().postDelayed(() -> {

            Intent mainIntent = new Intent(SplashScreenActivity.this, StartingPageActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}

