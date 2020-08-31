package com.example.dante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class trasactionAdapter extends RecyclerView.Adapter<trasactionAdapter.MyviewHolder> {
    String data[];
    String transaciondate;
    Context context;

    public trasactionAdapter(Context ct, String[] titles,String date){
        context = ct;
        data = titles;
        transaciondate = date;

    }


    @NonNull
    @Override
    public trasactionAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transaction_custom_view,parent,false);

        return new trasactionAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.text1.setText(data[position]);
        holder.date1.setText(transaciondate);

    }



    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView date1;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.transcardname);
            date1 = itemView.findViewById(R.id.date);

        }
    }
}
