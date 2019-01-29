package hr.ferit.kreso.labela;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Call_dialog extends AppCompatDialogFragment {

    private RadioButton radioButtonWe,radioButtonThem;
    private TextView textView;

    private Call_dialogListener listener;


    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.call_layout,null);

        radioButtonWe=view.findViewById(R.id.callWe);
        radioButtonThem=view.findViewById(R.id.callThem);
        textView=view.findViewById(R.id.who_called);

        builder.setView(view).setTitle(" ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!radioButtonWe.isChecked() && !radioButtonThem.isChecked()) {
                            Call_dialog call_dialog = new Call_dialog();
                            call_dialog.setCancelable(false);
                            assert getFragmentManager() != null;
                            call_dialog.show(getFragmentManager(), "tag");
                        }
                        if (radioButtonWe.isChecked()) {
                            listener.applySelected("WE");
                        }
                        if (radioButtonThem.isChecked()){
                            listener.applySelected("THEM");
                        }
                    }

                })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_UP){
                            Toast.makeText(getContext(),"Select who called",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        return false;
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener=(Call_dialogListener)context;
        } catch (ClassCastException e) {
            throw  new ClassCastException((context.toString() + "must implement Call_dialogListener"));
        }
    }

    public interface Call_dialogListener{
        void applySelected(String data);


    }

}
