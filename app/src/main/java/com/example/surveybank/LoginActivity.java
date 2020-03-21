package com.example.surveybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {
    EditText kullanici;
    EditText sifre;
    CardView giris;
    RestInterface restInterface;
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kullanici = findViewById(R.id.kullanici_et);
        sifre = findViewById(R.id.sifre_et);
        giris = findViewById(R.id.giris_btn);
        restInterface = ApiClient.getClient().create(RestInterface.class);
        final ProgressDialog progress = new ProgressDialog(this);

        // TODO production da kaldır temp data
        kullanici.setText("kadir");
        sifre.setText("123");
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setMessage("Giriş yapıyor :) ");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.show();

                final String kullanici_adi = kullanici.getText().toString();
                final String sifresi = sifre.getText().toString();
                if (kullanici_adi.equals("")) {
                    Toast.makeText(LoginActivity.this, "kullanıcı adı bos bırakılamaz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sifresi.equals("")) {
                    Toast.makeText(LoginActivity.this, "sifre bos bırakılamaz", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call<List<Anketör>> call = restInterface.GetAnketörListesi();
                call.enqueue(new Callback<List<Anketör>>() {
                    @Override
                    public void onResponse(Call<List<Anketör>> call, Response<List<Anketör>> response) {
                        if (!response.isSuccessful()) {
                            progress.hide();
                            return;
                        }
                        Log.i(TAG, "onResponse cevabı bas: " + response.body().size());
                        progress.hide();
                        List<Anketör> list = response.body();
                        for (int i = 0; i < list.size(); i++) {
                            Anketör anketör = new Anketör();
                            anketör = list.get(i);
                            Log.i(TAG, "onResponse: anketör = " + anketör.toString());
                            if (kullanici_adi.equals(anketör.getKullaniciAdi()) && sifresi.equals(anketör.getKullaniciSifre())) {
                                Toast.makeText(LoginActivity.this, "giriş başarılı", Toast.LENGTH_SHORT).show();
                                Intent ıntent = new Intent(LoginActivity.this, AnketActivity.class);
                                ıntent.putExtra(AnketActivity.KEY_EXTRA, anketör.getID());
                                startActivity(ıntent);
                                return;
                            }
                        }
                        Toast.makeText(LoginActivity.this, "giriş başarısız", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<Anketör>> call, Throwable t) {
                        progress.hide();
                        Toast.makeText(LoginActivity.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }


}
