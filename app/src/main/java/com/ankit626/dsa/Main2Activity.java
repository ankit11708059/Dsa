package com.ankit626.dsa;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    private EditText email,pass;
    private ImageView rf;
    private Button btn;
    private TextView dfg;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imj=(ImageView)findViewById(R.id.kp);
        email=(EditText)findViewById(R.id.editEmail);
        pass=(EditText)findViewById(R.id.editPass);
        btn=(Button)findViewById(R.id.btn1);
        dfg=(TextView)findViewById(R.id.dfg);
        rf=(ImageView)findViewById(R.id.rf);
        progressBar=(ProgressBar)findViewById(R.id.pwd);
        Glide.with(this).load(R.drawable.k).into(imj); //Sir it was loading slowly when i was loading it with xml
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        progressBar.setVisibility(View.GONE);
        rf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent utube=new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in"));
                startActivity(utube);
            }
        });
        dfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailtext=email.getText().toString().trim();
                String Passtext=email.getText().toString().trim();

                if(!TextUtils.isEmpty(emailtext) && !TextUtils.isEmpty(Passtext)){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(emailtext,Passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String user=mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user=mDatabase.child(user);
                                current_user.child("Name").setValue(emailtext);
                                Intent abc= new Intent(Main2Activity.this,MainActivity.class);
                                startActivity(abc);
                                finish();
                                progressBar.setVisibility(View.GONE);

                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Main2Activity.this,"Check your Internet connection",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }

            }
        });


    }

}
