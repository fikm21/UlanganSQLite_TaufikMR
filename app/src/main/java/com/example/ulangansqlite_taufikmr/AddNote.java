package com.example.ulangansqlite_taufikmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNote extends AppCompatActivity {
    public static final String EXTRA_id = "extra_id";
    public static final String EXTRA_tgl = "extra_tgl";
    public  static final String EXTRA_isi = "extra_isi";
    public static final String EXTRA_title = "extra_title";
    boolean edit = false;

    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.etContent)
    EditText etisi;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);

        ImageView save = (ImageView) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        ImageView delete = (ImageView )findViewById(R.id.deleteButton);
        if (getIntent().getStringExtra(EXTRA_title) == null){
            edit = false;
            delete.setVisibility(View.INVISIBLE);
        }else {
            edit = true;
            etisi.setText(getIntent().getStringExtra(EXTRA_isi));
            etTitle.setText(getIntent().getStringExtra(EXTRA_title));

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBhelper db = new DBhelper(AddNote.this);
                    db.delete(Integer.valueOf(getIntent().getStringExtra(EXTRA_id)));
                    back();
                }
            });
        }
    }

    private void saveNote(){
        Date tgl = Calendar.getInstance().getTime();
        String title = etTitle.getText().toString();
        String isi = etisi.getText().toString();

        if (title.equals("") || isi.equals("")){
            showToast("Judul dan dekripsi diisi telebih dahulu");
        }else {
            DBhelper db = new DBhelper(this);
            if (edit==true){
                Note note = new Note(title,isi,tgl.toString(),getIntent().getStringExtra(EXTRA_id));
                db.update(note);
            }else {
                Note note = new Note (title,isi,tgl.toString(),getIntent().getStringExtra(EXTRA_id));
                db.insert(note);
            }
            db.close();
            back();
        }


    }
    private void back(){
        Intent intent = new Intent(AddNote.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}