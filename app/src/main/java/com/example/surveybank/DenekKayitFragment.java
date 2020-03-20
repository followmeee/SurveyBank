package com.example.surveybank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.zip.Inflater;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class DenekKayitFragment extends DialogFragment {

    EditText isim;
    EditText soyisim;
    EditText dogum_tarihi;
    Spinner cinsiyet;
    EditText cep_telefonu;
    EditText meslek;
    EditText egitim_durumu;
    EditText email;
    EditText medeni_hal;
    EditText bölge;
    EditText sehir;
    Button kayit_ol;

    String isimm;
    String soyisimm;
    String dogum_tarihii;
    String cinsiyett;
    String cep_telefonuu;
    String meslekk;
    String egitim_durumuu;
    String emaill;
    String medeni_hall;
    String bolgee;
    String sehirr;
    Anket anket;


    public void setAnket(Anket anket) {
        this.anket = anket;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.denek_kayit_fragment, null);
        isim = view.findViewById(R.id.isim_et);
        soyisim = view.findViewById(R.id.soyisim_et);
        dogum_tarihi = view.findViewById(R.id.dtarihi_et);
        cinsiyet = view.findViewById(R.id.spinner);
        cep_telefonu = view.findViewById(R.id.gsm_et);
        meslek = view.findViewById(R.id.meslek_et);
        egitim_durumu = view.findViewById(R.id.egitim_durumu_et);
        email = view.findViewById(R.id.mail_et);
        medeni_hal = view.findViewById(R.id.medeni_hali_et);
        bölge = view.findViewById(R.id.bolge_et);
        sehir = view.findViewById(R.id.sehir_et);
        kayit_ol = view.findViewById(R.id.kayit_ol);
        final ProgressDialog progress = new ProgressDialog(getActivity());
        kayit_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setMessage("Denek kayıt oluyor:) ");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.show();
                isimm = isim.getText().toString();
                soyisimm = soyisim.getText().toString();
                dogum_tarihii = dogum_tarihi.getText().toString();
                cinsiyett = cinsiyet.getSelectedItem().toString();
                cep_telefonuu = cep_telefonu.getText().toString();
                meslekk = meslek.getText().toString();
                egitim_durumuu = egitim_durumu.getText().toString();
                emaill = email.getText().toString();
                medeni_hall = medeni_hal.getText().toString();
                bolgee = bölge.getText().toString();
                sehirr = sehir.getText().toString();
                RestInterface restInterface =
                        ApiClient.getClient().create(RestInterface.class); /**interfaceyle retrofiti tanıstırdım**/
                Call<ResponseBody> call = restInterface.PostDenekKaydi(
                        isimm,
                        soyisimm,
                        dogum_tarihii,
                        cinsiyett,
                        cep_telefonuu,
                        meslekk,
                        egitim_durumuu,
                        emaill,
                        medeni_hall,
                        bolgee,
                        sehirr
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        progress.hide();
                        if (response.isSuccessful()) {
                            Intent ıntent = new Intent(getActivity(), SorularActivity.class);
                            ıntent.putExtra(SorularActivity.KEY_EXTRA, anket);
                            startActivity(ıntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progress.hide();

                    }
                });

            }


        });


        return view;
    }
}
