package com.example.uts;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Currency;
import com.google.android.material.card.MaterialCardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.text.NumberFormat;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private final ArrayList<RecyclerData> courseDataArrayList;
    private final Context mcontext;
    float x = 0;
    public RecyclerViewAdapter(ArrayList<RecyclerData>
                                       recyclerDataArrayList, Context
                                       mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                 int
                                                         viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,
                        parent, false);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int
            position) {
        RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.namaTV.setText(recyclerData.getTitle());
        holder.hargaTV.setText(recyclerData.getHarga());
        holder.gambarIV.setImageResource(recyclerData.getImgid());
        String hrg = holder.hargaTV.getText().toString();
        String number = hrg.replaceAll("[^0-9]", "");
        holder.cardView.setOnClickListener(view -> {
            float num = Float.parseFloat(number);
            x += num;
            String s = Float.toString(x);
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(0);
            format.setCurrency(Currency.getInstance("IDR"));
            s = format.format(Float.parseFloat(s));
            Intent intent = new Intent("custom-message");
            intent.putExtra("Rhrg", s);
            LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
        });
    }
    @Override
    public int getItemCount() {

        return courseDataArrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView namaTV, hargaTV;
        private final ImageView gambarIV;
        private final MaterialCardView cardView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTV = itemView.findViewById(R.id.nama);
            hargaTV = itemView.findViewById(R.id.harga);
            gambarIV = itemView.findViewById(R.id.gambar);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}