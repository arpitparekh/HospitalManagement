package arpit.hospitalmanagement.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import arpit.hospitalmanagement.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(view -> {

            String email = binding.edtEmailLogin.getText().toString();

            String password = binding.edtPasswordLogin.getText().toString();

            if(!email.isEmpty() && !password.isEmpty()){

                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(authResult!=null){

                            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }


        });

        binding.tvNewUser.setOnClickListener(view -> {

            startActivity(new Intent(this,SignupActivity.class));

        });

        binding.tvForgetPassword.setOnClickListener(view -> {

            startActivity(new Intent(this,ForgetPasswordActivity.class));

        });

    }
}