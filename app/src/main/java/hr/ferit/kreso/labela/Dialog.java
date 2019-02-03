package hr.ferit.kreso.labela;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class Dialog extends AppCompatDialogFragment {

    @NonNull
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        LayoutInflater inflater=getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view=inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle(" ")
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent=new Intent(getActivity(),StartingPageActivity.class);
                    startActivity(intent);
                });
        TextView winner = view.findViewById(R.id.winner);

        winner.setText(getTag());

        return builder.create();
    }

}
