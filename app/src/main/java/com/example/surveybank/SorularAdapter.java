package com.example.surveybank;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SorularAdapter extends RecyclerView.Adapter<SorularAdapter.MyHolder> {
    List<Sorular> soru_listesi;
    Activity activiy;
    Integer denek_id;
    Integer anketor_id;
    Integer anket_id;
    List<SoruCevap> gönderilen_sorucevaplari;
    // todo İnterface 2.aşama
    ClickListener clickListener;

    public SorularAdapter() {
        gönderilen_sorucevaplari = new ArrayList<>();
    }

    public void setSoru_listesi(List<Sorular> soru_listesi) {
        this.soru_listesi = soru_listesi;

    }

    public void setcontext(SorularActivity sorularActivity) {
        this.activiy = sorularActivity;
    }

    public void setDenek_id(Integer denek_id) {
        this.denek_id = denek_id;
    }

    public void setAnketor_id(Integer anketor_id) {
        this.anketor_id = anketor_id;
    }

    public void setAnket_id(Integer anket_id) {
        this.anket_id = anket_id;
    }

    public void CevapGonder(final int soru_id, int secenek_id, final int secenek_pozisyonu) {

        final ProgressDialog progress = new ProgressDialog(activiy);
        progress.setMessage("Secenek gonderiliyor ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        RestInterface restInterface =
                ApiClient.getClient().create(RestInterface.class); /**interfaceyle retrofiti tanıstırdım**/
        Call<ResponseBody> call = restInterface.PostCevap(
                anket_id,
                anketor_id,
                soru_id,
                secenek_id,
                denek_id
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progress.hide();
                if (response.isSuccessful()) {
                    Toast.makeText(activiy, "secenek basarıyla gönderildi", Toast.LENGTH_SHORT).show();
                    SoruCevap soruCevap = new SoruCevap();
                    soruCevap.setSoru_id(soru_id);
                    soruCevap.setSecili_secenek_pozisyon(secenek_pozisyonu);

                    if (!buSoruListedeVarmi(soru_id)) {
                        gönderilen_sorucevaplari.add(soruCevap);
                        notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progress.hide();
            }
        });
    }

    private boolean buSoruListedeVarmi(int soru_id) {
        for (int i = 0; i < gönderilen_sorucevaplari.size(); i++) {
            SoruCevap soruCevap = gönderilen_sorucevaplari.get(i);
            if (soruCevap.getSoru_id() == soru_id)
                return true;
        }
        return false;
    }

    // todo İnterface 3.aşama
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_soru, viewGroup, false);

        MyHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int position) {

//anket ve anketör ıd ve denek id buraya getirilecek

        myHolder.soru_sirasi.setText("SORU " + soru_listesi.get(position).getSoruid().toString());
        myHolder.soru_baslik.setText(soru_listesi.get(position).getSoru());
        final int secenek_listesi_sayisi = soru_listesi.get(position).getSecenek().size();
        myHolder.linear1.setVisibility(View.GONE);
        myHolder.linear2.setVisibility(View.GONE);
        myHolder.linear3.setVisibility(View.GONE);
        myHolder.linear4.setVisibility(View.GONE);
        if (secenek_listesi_sayisi > 0 && soru_listesi.get(position).getSecenek().get(0) != null) {
            myHolder.secenek_1.setText(soru_listesi.get(position).getSecenek().get(0));
            myHolder.linear1.setVisibility(View.VISIBLE);
        }
        if (secenek_listesi_sayisi > 1 && soru_listesi.get(position).getSecenek().get(1) != null) {
            myHolder.secenek_2.setText(soru_listesi.get(position).getSecenek().get(1));
            myHolder.linear2.setVisibility(View.VISIBLE);
        }
        if (secenek_listesi_sayisi > 2 && soru_listesi.get(position).getSecenek().get(2) != null) {
            myHolder.secenek_3.setText(soru_listesi.get(position).getSecenek().get(2));
            myHolder.linear3.setVisibility(View.VISIBLE);
        }
        if (secenek_listesi_sayisi > 3 && soru_listesi.get(position).getSecenek().get(3) != null) {
            myHolder.secenek_4.setText(soru_listesi.get(position).getSecenek().get(3));
            myHolder.linear4.setVisibility(View.VISIBLE);
        }


        myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli);
        myHolder.linear2.setBackgroundResource(R.drawable.secenek_sekli);
        myHolder.linear3.setBackgroundResource(R.drawable.secenek_sekli);
        myHolder.linear4.setBackgroundResource(R.drawable.secenek_sekli);
        final int secili_secenek_pozisyonu = busorununcevabininpozisyonune(soru_listesi.get(position).getSoruid());
        switch (secili_secenek_pozisyonu) {
            case 0:
                myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli_tiklaninca);
                myHolder.linear2.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear3.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear4.setBackgroundResource(R.drawable.secenek_sekli);
                break;
            case 1:
                myHolder.linear2.setBackgroundResource(R.drawable.secenek_sekli_tiklaninca);
                myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear3.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear4.setBackgroundResource(R.drawable.secenek_sekli);
                break;
            case 2:
                myHolder.linear3.setBackgroundResource(R.drawable.secenek_sekli_tiklaninca);
                myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear2.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear4.setBackgroundResource(R.drawable.secenek_sekli);
                break;
            case 3:
                myHolder.linear4.setBackgroundResource(R.drawable.secenek_sekli_tiklaninca);
                myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear2.setBackgroundResource(R.drawable.secenek_sekli);
                myHolder.linear3.setBackgroundResource(R.drawable.secenek_sekli);
                break;
        }

        myHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onclickItem(soru_listesi.get(position));
                }

            }
        });
        myHolder.linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (secili_secenek_pozisyonu != -1) {
                    return;
                }
                myHolder.linear1.setBackgroundResource(R.drawable.secenek_sekli_tiklaninca);
                CevapGonder(soru_listesi.get(position).getSoruid(),
                        soru_listesi.get(position).getSecenekid().get(0),
                        0);


            }
        });
        myHolder.linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (secili_secenek_pozisyonu != -1) {
                    return;
                }
                myHolder.linear2.setBackground(activiy.getDrawable(R.drawable.secenek_sekli_tiklaninca));
                CevapGonder(soru_listesi.get(position).getSoruid(),
                        soru_listesi.get(position).getSecenekid().get(1),
                        1);


            }
        });
        myHolder.linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (secili_secenek_pozisyonu != -1) {
                    return;
                }
                myHolder.linear3.setBackground(activiy.getDrawable(R.drawable.secenek_sekli_tiklaninca));
                CevapGonder(soru_listesi.get(position).getSoruid(),
                        soru_listesi.get(position).getSecenekid().get(2),
                        2);


            }
        });
        myHolder.linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (secili_secenek_pozisyonu != -1) {
                    return;
                }
                myHolder.linear4.setBackground(activiy.getDrawable(R.drawable.secenek_sekli_tiklaninca));
                CevapGonder(soru_listesi.get(position).getSoruid(),
                        soru_listesi.get(position).getSecenekid().get(3),
                        3);


            }
        });

    }

    private int busorununcevabininpozisyonune(Integer soruid) {

        for (int i = 0; i < gönderilen_sorucevaplari.size(); i++) {
            SoruCevap soruCevap = gönderilen_sorucevaplari.get(i);
            if (soruCevap.getSoru_id() == soruid)
                return soruCevap.getSecili_secenek_pozisyon();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (soru_listesi == null) {
            return 0;
        }
        return soru_listesi.size();

    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView soru_sirasi;
        public TextView soru_baslik;
        public TextView secenek_1;
        public TextView secenek_2;
        public TextView secenek_3;
        public TextView secenek_4;
        public LinearLayout linearLayout;
        public LinearLayout linear1;
        public LinearLayout linear2;
        public LinearLayout linear3;
        public LinearLayout linear4;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            soru_sirasi = itemView.findViewById(R.id.soru_sirasi_tv);
            soru_baslik = itemView.findViewById(R.id.soru_baslik_tv);
            secenek_1 = itemView.findViewById(R.id.secenek_1_tv);
            secenek_2 = itemView.findViewById(R.id.secenek_2_tv);
            secenek_3 = itemView.findViewById(R.id.secenek_3_tv);
            secenek_4 = itemView.findViewById(R.id.secenek_4_tv);
            linearLayout = itemView.findViewById(R.id.root_id);
            linear1 = itemView.findViewById(R.id.linear1);
            linear2 = itemView.findViewById(R.id.linear2);
            linear3 = itemView.findViewById(R.id.linear3);
            linear4 = itemView.findViewById(R.id.linear4);

        }
    }

    // todo İnterface 1.aşama
    interface ClickListener {
        void onclickItem(Sorular sorular);
    }
}

