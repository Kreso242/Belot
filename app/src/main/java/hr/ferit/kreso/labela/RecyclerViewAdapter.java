package hr.ferit.kreso.labela;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<String> points=new ArrayList<>();
    private Context context;
    private ImageClickInterface imageClick;


   public RecyclerViewAdapter(ArrayList<String> points){
        this.points=points;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_name,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setName(points.get(position));
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,points.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return points.size();
    }


    public void addData(List<String> data){
        points.clear();
        points.addAll(data);
        notifyDataSetChanged();
    }

    public void insertNewItem(String name,int position){
        if(position>=0 && position<=points.size()){
            points.add(position,name);
            notifyItemInserted(position);
        }
    }

    public void removeItem(int position){
        if(position>=0 && position<points.size()){
            points.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewWe;
        RelativeLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWe=itemView.findViewById(R.id.tvName);
            parent_layout=itemView.findViewById(R.id.parent_layout);
        }

        public void setName(String name){
            textViewWe.setText(name);
        }
    }
}
