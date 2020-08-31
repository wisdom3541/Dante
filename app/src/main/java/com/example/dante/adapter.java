package com.example.dante;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class adapter extends RecyclerView.Adapter<adapter.MyviewHolder> {
    String data[];
    int img[];
    Context context;

    public adapter(Context ct, String[] titles, int[] Images) {
        context = ct;
        data = titles;
        img = Images;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_view, parent, false);

        return new MyviewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.text1.setText(data[position]);
        holder.imgs.setImageResource(img[position]);

           }

    @Override
    public int getItemCount() {
        return img.length;

    }


    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        ImageView imgs;
        int index = 2;
        String tag;


        public MyviewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = getPosition();
                    Log.e(tag, String.valueOf(index));
                    Intent i = new Intent(v.getContext(), carddetailspage.class);
                    i.putExtra("number",index);
                    v.getContext().startActivity(i);
                }
            });
            text1 = itemView.findViewById(R.id.textTitle);
            imgs = itemView.findViewById(R.id.imageView);

        }
    }
}
