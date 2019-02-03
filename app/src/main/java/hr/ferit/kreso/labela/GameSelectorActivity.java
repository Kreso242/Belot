package hr.ferit.kreso.labela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class GameSelectorActivity extends AppCompatActivity {

    private int upTo1001 = 1001, upTo701 = 701, upTo501 = 501;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_game_selector);

        RadioButton radioButton1001 = findViewById(R.id.radioButton1001);
        RadioButton radioButton701 = findViewById(R.id.radioButton701);
        RadioButton radioButton501 = findViewById(R.id.radioButton501);
        RelativeLayout rlLastGame = findViewById(R.id.rlLastGame);
        TextView tvSumPointsWe = findViewById(R.id.tvSumPointsWe);
        TextView tvSumPointsThem = findViewById(R.id.tvSumPointsThem);

        radioButton1001.setOnClickListener(v -> openGameTable(upTo1001));

        radioButton701.setOnClickListener(v -> openGameTable(upTo701));

        radioButton501.setOnClickListener(v -> openGameTable(upTo501));


        ArrayList<GameItem> list = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
        {
            List<GameItem> resultList = realm.where(GameItem.class).findAll();
            list.addAll(resultList);
        });

        if (list.isEmpty()) {
            rlLastGame.setVisibility(View.GONE);
        } else {
            rlLastGame.setVisibility(View.VISIBLE);
            int sumWe = 0;
            int sumThem = 0;

            for (int i = 0; i < list.size(); i++) {
                sumWe += list.get(i).pointWe;
                sumThem += list.get(i).pointThem;
            }

            tvSumPointsWe.setText(String.valueOf(sumWe));
            tvSumPointsThem.setText(String.valueOf(sumThem));

        }
        realm.close();

        rlLastGame.setOnClickListener(view -> openLastGameTable());

    }

    private void openLastGameTable() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openGameTable(int upTo) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
        {
            List<GameItem> resultList = realm.where(GameItem.class).findAll();
            ((RealmResults<GameItem>) resultList).deleteAllFromRealm();
            realm.where(MaxGamePoint.class).findAll().deleteAllFromRealm();
        });
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        realm.beginTransaction();
        realm.copyToRealm(new MaxGamePoint(upTo));
        realm.commitTransaction();
        realm.close();
    }
}
