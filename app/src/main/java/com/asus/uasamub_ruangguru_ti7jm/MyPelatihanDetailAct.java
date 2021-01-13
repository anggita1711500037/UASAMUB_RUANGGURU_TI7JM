package com.asus.uasamub_ruangguru_ti7jm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPelatihanDetailAct extends AppCompatActivity {

    DatabaseReference reference;
    TextView xnama_pelatihan, xlokasi, xtime_pelatihan, xdate_pelatihan, xketentuan;
    LinearLayout btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pelatihan_detail);

        xnama_pelatihan = findViewById(R.id.xnama_pelatihan);
        xlokasi = findViewById(R.id.xlokasi);
        xtime_pelatihan = findViewById(R.id.xtime_pelatihan);
        xdate_pelatihan = findViewById(R.id.xdate_pelatihan);
        xketentuan = findViewById(R.id.xketentuan);
        btn_back = findViewById(R.id.btn_back);

        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String nama_pelatihan_baru = bundle.getString("nama_pelatihan");

        // mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Kelas").child("SD1");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                xnama_pelatihan.setText(dataSnapshot.child("nama_kelas").getValue().toString());
                xlokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                xtime_pelatihan.setText(dataSnapshot.child("time_kelas").getValue().toString());
                xdate_pelatihan.setText(dataSnapshot.child("date_kelas").getValue().toString());
                xketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile = new Intent(MyPelatihanDetailAct.this,MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

    }
}
