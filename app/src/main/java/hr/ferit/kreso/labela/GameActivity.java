package hr.ferit.kreso.labela;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity implements ImageClickInterface   {

    private Button addResultButton,exitButton;
    private int sumWe=0,sumThem=0;
    private int upTo;
    private RecyclerView recyclerViewWe,recyclerViewThem;
    private RecyclerViewAdapter adapterWe;
    private RecyclerViewAdapter2 adapterThem;
    private TextView resultWe,resultThem;
    private TextView winner,who_is_winner;
    private ArrayList<Integer> pointsWe=new ArrayList<>();
    private ArrayList<Integer> pointsThem=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide app name bar
        setContentView(R.layout.activity_game);

            winner=findViewById(R.id.winner);
            who_is_winner=findViewById(R.id.who_is_winner);

            resultWe=findViewById(R.id.resultWe);
            resultThem=findViewById(R.id.resultThem);

            recyclerViewWe = findViewById(R.id.rvViewWe);
            recyclerViewWe.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewThem = findViewById(R.id.rvViewThem);
            recyclerViewThem.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewWe.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            recyclerViewThem.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

            adapterWe = new RecyclerViewAdapter(this);
            adapterThem=new RecyclerViewAdapter2(this);

            recyclerViewWe.setAdapter(adapterWe);
            recyclerViewThem.setAdapter(adapterThem);

            Intent intent = getIntent();
            upTo=intent.getIntExtra("upTo",0);

        if (!intent.getBooleanExtra("emptyList", true)) {
                pointsWe.addAll(intent.getIntegerArrayListExtra("we"));
                pointsThem.addAll(intent.getIntegerArrayListExtra("them"));
                adapterWe.addData(pointsWe);
                adapterThem.addData(pointsThem);
                recyclerViewWe.smoothScrollToPosition(adapterWe.getItemCount()-1);
                recyclerViewThem.smoothScrollToPosition(adapterThem.getItemCount()-1);

                for(int i=0;i<adapterWe.getItemCount();i++){
                    sumWe+=pointsWe.get(i);
                    sumThem+=pointsThem.get(i);
                }

                resultWe.setText(String.valueOf(sumWe));
                resultThem.setText(String.valueOf(sumThem));

                if(sumWe>=upTo || sumThem>=upTo){
                    if(sumWe>sumThem){
                        openDialog("WE");
                    }
                    else if(sumThem>sumWe){
                        openDialog("THEM");
                    }
                    else{
                       openDialog("NOBODY, IT'S A DRAW");
                    }

                }
            }

            exitButton=(Button)findViewById(R.id.ExitButton2);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishAffinity();
                    System.exit(0);
                }
            });

            addResultButton = (Button) findViewById(R.id.addResultButton);
            addResultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addResult();
                }
            });

    }

    private void openDialog(String data) {
        Dialog dialog=new Dialog();
        dialog.show(getSupportFragmentManager(),data);
    }

    private void addResult() {
        Intent intent=new Intent(this,InsertResultActivity.class);
        intent.putExtra("pointsArrayWe", pointsWe);
        intent.putExtra("pointsArrayThem", pointsThem);
        intent.putExtra("upTo",upTo);
        startActivity(intent);

    }

    public void onImageClicked(View view, int position){
        adapterWe.removeItem(position);
    }


}
