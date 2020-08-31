package com.example.dante;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome extends AppCompatActivity {
    Intent intent;
    TextView createacct;
    ImageView loginbtn;
    EditText Emailtext,Passwordtext;
    private FirebaseAuth mAuth;
    String email,password,remembermail,rememberpassord;
    String I;
    String TAG;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mAuth = FirebaseAuth.getInstance();

        Emailtext = findViewById(R.id.email);
        Passwordtext = findViewById(R.id.password);
        intent = new Intent(this,createacct.class);
       // intent2 = new Intent(this,fragmentsholder.class);
        createacct = findViewById(R.id.createacct);
        loginbtn = (ImageView)findViewById(R.id.loginbtn);


        remembermail = Utils.signemail;
        rememberpassord = Utils.signpassword;

        if(remembermail != null || rememberpassord != null  ){
            remembersignin();
        }



         createacct.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(intent);
             }
         });

         loginbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 email = Emailtext.getText().toString();
                 password = Passwordtext.getText().toString();
                 signin();
             }
         });
    }

    public void signin(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent i = new Intent(welcome.this, fragmentsholder.class);
                            i.putExtra("email",email);
                            Log.e(TAG,email);
                            Utils.useremail = email;
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(welcome.this, "Incorrect Email or Password",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void remembersignin(){
        mAuth.signInWithEmailAndPassword(remembermail, rememberpassord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent i = new Intent(welcome.this, fragmentsholder.class);
                            i.putExtra("email",email);
                            Log.e(TAG,email);
                            Utils.useremail = email;
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(welcome.this, "Account deleted or Password changed",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void updateUI(Object o) {
        Log.e(I, (String) "this" + o);
    }

}
