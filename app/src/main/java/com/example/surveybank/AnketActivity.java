package com.example.surveybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// todo İnterface 5.aşama implements
public class AnketActivity extends AppCompatActivity implements AnketAdapter.ClickListener {
    RecyclerView recyclerView;
    AnketAdapter anketAdapter;
    public static final String KEY_EXTRA = "BUNDLE_ANKETOR_ID_KEY";
    String TAG = "akis";
    public ProgressDialog progressDialog;

   public int anketör_idd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anket);
        recyclerView = findViewById(R.id.anket_recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        anketAdapter = new AnketAdapter();
        // todo İnterface 7.aşama
        anketAdapter.setClickListener(this);
        recyclerView.setAdapter(anketAdapter); /**recylerviewe adapterini tanıttım.*/
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Anketler yükleniyor:) ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();



        if (getIntent().hasExtra(KEY_EXTRA)) {
            anketör_idd = getIntent().getIntExtra(KEY_EXTRA, -1);
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + KEY_EXTRA);
        }

        RestInterface restInterface =
                ApiClient.getClient().create(RestInterface.class); /**interfaceyle retrofiti tanıstırdım**/
        Call<List<Anket>> call = restInterface.GetAnketListesi(anketör_idd);

        call.enqueue(new Callback<List<Anket>>() {
            @Override
            public void onResponse(Call<List<Anket>> call, Response<List<Anket>> response) {
                progressDialog.hide();
                if (response.body() != null) {

                    Log.i(TAG, "onResponse:listenin sayısı " + response.body().size());
                }
                if (response.isSuccessful()) {
                    anketAdapter.setAnket_listesi(response.body());
                    anketAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<Anket>> call, Throwable t) {
                progressDialog.hide();

            }
        });

    }
    public void showDialog(Anket anket) {

        DenekKayitFragment denekKayitFragment=new DenekKayitFragment();
        denekKayitFragment.setAnket(anket);
        denekKayitFragment.setAnketorId(anketör_idd);
        denekKayitFragment.show(getSupportFragmentManager(), "DenekKayitFragment");


    }

    // todo İnterface 6.aşama
    @Override
    public void onclickItem(Anket anket) {
//
        showDialog(anket);
    }
}
