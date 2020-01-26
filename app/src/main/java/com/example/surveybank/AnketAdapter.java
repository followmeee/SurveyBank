package com.example.surveybank;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AnketAdapter extends RecyclerView.Adapter<AnketAdapter.MyHolder> {
    List<Anket> anket_listesi;
    // todo İnterface 2.aşama
    ClickListener clickListener;

    public void setAnket_listesi(List<Anket> anket_listesi) {
        this.anket_listesi = anket_listesi;
    }

    // todo İnterface 3.aşama
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_anket, viewGroup, false);

        MyHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int position) {

        myHolder.baslik.setText(anket_listesi.get(position).getBaslik());
        myHolder.aciklama.setText(anket_listesi.get(position).getAciklama());
        myHolder.constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // todo İnterface 4.aşama
                clickListener.onclickItem(anket_listesi.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        if (anket_listesi==null){
            return 0;
        }
        return anket_listesi.size();

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView baslik;
        public TextView aciklama;
        public ConstraintLayout constraint;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
           baslik =itemView.findViewById(R.id.baslik_id);
           aciklama=itemView.findViewById(R.id.aciklama_id);
           constraint =itemView.findViewById(R.id.root_id);

        }
    }

    // todo İnterface 1.aşama
    interface ClickListener{
        void onclickItem(Anket anket);
    }
}

