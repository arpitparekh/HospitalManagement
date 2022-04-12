package arpit.hospitalmanagement.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import arpit.hospitalmanagement.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.button.setOnClickListener(view -> {

            String email = binding.edtEmailSignUp.getText().toString();
            String password = binding.edtPassword.getText().toString();
            String repeat = binding.edtRepatePasswordSignUp.getText().toString();

            if(!email.isEmpty() && !password.isEmpty() && !repeat.isEmpty() && password.equals(repeat)){

                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        if(authResult!=null){

                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }else{

                Toast.makeText(this, "Requirement not fulfilled", Toast.LENGTH_SHORT).show();
            }


        });

    }
}