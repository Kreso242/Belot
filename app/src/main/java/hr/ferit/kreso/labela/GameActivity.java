package hr.ferit.kreso.labela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class GameActivity extends AppCompatActivity {

    private int sumWe = 0, sumThem = 0;
    private int upTo;
    private RecyclerViewAdapter adapterWe, adapterThem;
    private TextView resultWe, resultThem;
    private ArrayList<Integer> pointsWe = new ArrayList<>();
    private ArrayList<Integer> pointsThem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_game);

        ArrayList<GameItem> list = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
        {
            List<GameItem> resultList = realm.where(GameItem.class).findAll();
            list.addAll(resultList);
        });

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                pointsWe.add(list.get(i).pointWe);
                pointsThem.add(list.get(i).pointThem);
            }
        }

        resultWe = findViewById(R.id.resultWe);
        resultThem = findViewById(R.id.resultThem);

        RecyclerView recyclerViewWe = findViewById(R.id.rvViewWe);
        recyclerViewWe.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerViewThem = findViewById(R.id.rvViewThem);
        recyclerViewThem.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewWe.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewThem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapterWe = new RecyclerViewAdapter();
        adapterThem = new RecyclerViewAdapter();

        recyclerViewWe.setAdapter(adapterWe);
        recyclerViewThem.setAdapter(adapterThem);

        upTo = Objects.requireNonNull(realm.where(MaxGamePoint.class).findFirst()).maxGame;


        if (!pointsWe.isEmpty()) {
            adapterWe.addData(pointsWe);
            adapterThem.addData(pointsThem);
            recyclerViewWe.smoothScrollToPosition(adapterWe.getItemCount() - 1);
            recyclerViewThem.smoothScrollToPosition(adapterThem.getItemCount() - 1);

            for (int i = 0; i < adapterWe.getItemCount(); i++) {
                sumWe += pointsWe.get(i);
                sumThem += pointsThem.get(i);
            }

            resultWe.setText(String.valueOf(sumWe));
            resultThem.setText(String.valueOf(sumThem));

            if (sumWe >= upTo || sumThem >= upTo) {
                if (sumWe > sumThem) {
                    openDialog("MI");
                } else if (sumThem > sumWe) {
                    openDialog("VI");
                } else {
                    openDialog("NERIJEŠENO");
                }

                realm.executeTransaction(realm2 ->
                {
                    List<GameItem> resultList = realm.where(GameItem.class).findAll();
                    ((RealmResults<GameItem>) resultList).deleteAllFromRealm();
                });
                realm.close();
            }
        }

        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StartingPageActivity.class);
            startActivity(intent);
        });

        Button addResultButton = findViewById(R.id.addResultButton);
        addResultButton.setOnClickListener(v -> addResult());

        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> {

            if (adapterWe.getItemCount() != 0) {
                int sumWeTemp = pointsWe.get(adapterWe.getItemCount() - 1);
                int sumThemTemp = pointsThem.get(adapterThem.getItemCount() - 1);
                pointsWe.remove(adapterWe.getItemCount() - 1);
                pointsThem.remove(adapterThem.getItemCount() - 1);

                adapterWe.addData(pointsWe);
                adapterThem.addData(pointsThem);

                sumWe = sumWe - sumWeTemp;
                sumThem = sumThem - sumThemTemp;

                resultWe.setText(String.valueOf(sumWe));
                resultThem.setText(String.valueOf(sumThem));
                Realm realm12 = Realm.getDefaultInstance();
                realm12.executeTransaction(realm1 ->
                {
                    List<GameItem> resultList = realm12.where(GameItem.class).findAll();
                    Objects.requireNonNull(((RealmResults<GameItem>) resultList).last()).deleteFromRealm();
                });
                realm12.close();
            } else
                Toast.makeText(getApplicationContext(), "Tablica je prazna!", Toast.LENGTH_SHORT).show();
        });

    }

    private void openDialog(String data) {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), data);
    }

    private void addResult() {
        Intent intent = new Intent(this, InsertResultActivity.class);
        intent.putExtra("pointsArrayWe", pointsWe);
        intent.putExtra("pointsArrayThem", pointsThem);
        intent.putExtra("upTo", upTo);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Pritisni 'povratak' ako želiš izaći!", Toast.LENGTH_LONG);
        toast.show();
    }
}
