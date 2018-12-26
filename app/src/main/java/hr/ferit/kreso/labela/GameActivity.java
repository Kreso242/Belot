package hr.ferit.kreso.labela;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity implements ImageClickInterface   {

    private Button addResultButton;
    private int sumWe=0,sumThem=0,we,them;
    private RecyclerView recyclerViewWe,recyclerViewThem;
    private RecyclerViewAdapter adapterWe;
    private RecyclerViewAdapter2 adapterThem;

    private ArrayList<String> pointsWe=new ArrayList<>();
    private ArrayList<String> pointsThem=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide app name bar
        setContentView(R.layout.activity_game);


            recyclerViewWe = findViewById(R.id.rvViewWe);
            recyclerViewWe.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewThem = findViewById(R.id.rvViewThem);
            recyclerViewThem.setLayoutManager(new LinearLayoutManager(this));

            adapterWe = new RecyclerViewAdapter(pointsWe);
            adapterThem=new RecyclerViewAdapter2(pointsThem);

            recyclerViewWe.setAdapter(adapterWe);
            recyclerViewThem.setAdapter(adapterThem);

            Intent intent = getIntent();
            we = intent.getIntExtra("we", 0);
            them = intent.getIntExtra("them", 0);

            adapterWe.insertNewItem(String.valueOf(we), adapterWe.getItemCount());
            adapterThem.insertNewItem(String.valueOf(them), adapterThem.getItemCount());


            addResultButton = (Button) findViewById(R.id.addResultButton);
            addResultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addResult();
                }
            });

    }

    private void addResult() {
        Intent intent=new Intent(this,InsertResultActivity.class);
        startActivity(intent);

    }

    public void onImageClicked(View view, int position){
        adapterWe.removeItem(position);
    }


}
