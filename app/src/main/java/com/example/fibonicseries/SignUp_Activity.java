package com.example.fibonicseries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Activity extends AppCompatActivity {

    private TextView  tvSignIn;
    private EditText etEmail, etPass;
    private Button btnSignUp;

    //to authenticate the driver we use
    private FirebaseAuth firebaseAuth;

    //while authenticating the user we show progress bar
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        //initializing the FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing the ProgressDialog
        progressDialog = new ProgressDialog(this);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //we are getting the data from the email & pass fields & storing in our new variables
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();

                //once we get the above data we will register the driver
                register(email, password);

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(SignUp_Activity.this, MainActivity.class));

            }
        });
    }

    //defining the registration process
    private void register(String email, String password){

        //to check if user has entered his email or not
        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUp_Activity.this,"Please enter an email", Toast.LENGTH_SHORT).show();
        }

        //to check if user has entered his password or not
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SignUp_Activity.this,"Please enter a password", Toast.LENGTH_SHORT).show();
        }

        //once all the fields are filled then we will register the driver
        else{

            //while the authentication process is in progress we show a Progress bar with
            progressDialog.setTitle("Driver Registration");
            progressDialog.setMessage("Just a moment");
            progressDialog.show();


            //here we create the user in Firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(SignUp_Activity.this, "SignUp Sucessfull", Toast.LENGTH_SHORT).show();
                                //once the above task is sucessfull well dismiss the progress bar
                                progressDialog.dismiss();

                                //once the above task is sucessful direct the driver to the google maps activity
                                startActivity(new Intent(SignUp_Activity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(SignUp_Activity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
        }
    }
}