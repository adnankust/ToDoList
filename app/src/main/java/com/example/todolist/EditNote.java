package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {

    EditText editTitle, editDescription;
    Button btnSave, btnCancel;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent = getIntent();
        editTitle = findViewById(R.id.edt_edit_title);
        editDescription = findViewById(R.id.edt_edit_descrption);
        linearLayout = findViewById(R.id.btn_holder);

        editTitle.setText(intent.getStringExtra("title"));
        editDescription.setText(intent.getStringExtra("description"));

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note(editTitle.getText().toString(), editDescription.getText().toString());
                note.setId(intent.getIntExtra("id", 1));
                if(new NoteHandler(EditNote.this).update(note)) {
                    Toast.makeText(EditNote.this, "Note Updated", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(EditNote.this, "Failed to Update note", Toast.LENGTH_SHORT).show();
                }

                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();
    }
}