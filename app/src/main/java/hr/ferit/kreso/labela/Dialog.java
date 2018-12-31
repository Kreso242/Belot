package hr.ferit.kreso.labela;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Dialog extends AppCompatDialogFragment {

    private TextView who_is_winner,winner;

    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle(" ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(getActivity(),StartingPageActivity.class);
                startActivity(intent);
            }
        });
        who_is_winner=view.findViewById(R.id.who_is_winner);
        winner=view.findViewById(R.id.winner);

        winner.setText(getTag());

        return builder.create();
    }

}
