package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class createacct extends AppCompatActivity {

  EditText mail,pass;
  ImageView createacctbn;
  Intent intent;
  String email,password,I,TAG;
  private FirebaseAuth mAuth;

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
    setContentView(R.layout.createacct);

    mail = findViewById(R.id.emailtext);
    pass = findViewById(R.id.passwordtext);
    mAuth = FirebaseAuth.getInstance();
    createacctbn = (ImageView)findViewById(R.id.createacctbtn);
    intent = new Intent(createacct.this,createdacctpage.class);




  }

  public void signup(){
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // Sign in success, update UI with the signed-in user's information
                  Log.d(TAG, "createUserWithEmail:success");
                  FirebaseUser user = mAuth.getCurrentUser();
                  updateUI(user);
                  startActivity(intent);
                } else {
                  // If sign in fails, display a message to the user.
                  Log.w(TAG, "createUserWithEmail:failure", task.getException());
                  Toast.makeText(createacct.this, "Authentication failed.",
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
