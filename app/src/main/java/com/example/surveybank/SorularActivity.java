package com.example.surveybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;

public class SorularActivity extends AppCompatActivity {
    public static final String KEY_EXTRA = "BUNDLE_ANKET_KEY";
    public static final String DENEK_KEY_EXTRA = "BUNDLE_DENEK_KEY";
    public static final String ANKETOR_ID_EXTRA = "BUNDLE_ANKETOR_KEY";
    RecyclerView recyclerView;
    SorularAdapter sorularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular);
        Intent i = getIntent();
        Anket anket = (Anket) i.getSerializableExtra(KEY_EXTRA);
        Integer denek_id=i.getIntExtra(DENEK_KEY_EXTRA,-1);
        Integer anketor_id=i.getIntExtra(ANKETOR_ID_EXTRA,-1);
        recyclerView=findViewById(R.id.soru_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
      sorularAdapter =new SorularAdapter();
      sorularAdapter.setSoru_listesi(anket.getSorular());
      sorularAdapter.setcontext(this);
      sorularAdapter.setDenek_id(denek_id);
      sorularAdapter.setAnketor_id(anketor_id);
      sorularAdapter.setAnket_id(anket.getID());

      recyclerView.setAdapter(sorularAdapter);



    }
}
