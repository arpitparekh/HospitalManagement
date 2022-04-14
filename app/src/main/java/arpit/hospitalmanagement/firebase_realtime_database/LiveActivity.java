package arpit.hospitalmanagement.firebase_realtime_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import arpit.hospitalmanagement.databinding.ActivityLiveBinding;


public class LiveActivity extends AppCompatActivity {

    private ActivityLiveBinding binding;
    private DatabaseReference ref;
    private ArrayList<Doctor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();

        DoctorAdapter adapter = new DoctorAdapter(list);

        binding.recyclerView.setAdapter(adapter);

        ref = FirebaseDatabase.getInstance().getReference("Doctor");

        binding.btnSend.setOnClickListener(view -> {

            Doctor d = new Doctor(binding.edtDoctorName.getText().toString(),
                    binding.edtDoctorDegree.getText().toString(),
                    binding.edtDoctorField.getText().toString(),
                    binding.edtHospital.getText().toString());

                    ref.push().setValue(d);

        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot childSnap : snapshot.getChildren()){

                    Doctor d =  childSnap.getValue(Doctor.class);

                    list.add(d);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}