package arpit.hospitalmanagement.firebase_realtime_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import arpit.hospitalmanagement.R;
import arpit.hospitalmanagement.databinding.ActivityLiveBinding;


public class LiveActivity extends AppCompatActivity implements DoctorAdapter.OnItemClickInterface {

    private ActivityLiveBinding binding;
    private DatabaseReference ref;
    private ArrayList<Doctor> list;
    private ArrayList<String> keyList;
    private ProgressDialog pd;
    private boolean bool = false;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Fetching User Data");
        pd.setMessage("Please Wait");
        pd.show();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();
        keyList = new ArrayList<>();

        DoctorAdapter adapter = new DoctorAdapter(list,this);

        binding.recyclerView.setAdapter(adapter);

        ref = FirebaseDatabase.getInstance().getReference("Doctor");

        binding.btnSend.setOnClickListener(view -> {

          if(bool){

              String key = keyList.get(pos);

              String name = binding.edtDoctorName.getText().toString();
              String degree = binding.edtDoctorDegree.getText().toString();
              String field = binding.edtDoctorField.getText().toString();
              String hospital = binding.edtHospital.getText().toString();

              Doctor d = new Doctor(name,degree,field,hospital);

              ref.child(key).setValue(d);

              binding.btnSend.setText("Add");

          }else{

              Doctor d = new Doctor(binding.edtDoctorName.getText().toString(),
                      binding.edtDoctorDegree.getText().toString(),
                      binding.edtDoctorField.getText().toString(),
                      binding.edtHospital.getText().toString());

              ref.push().setValue(d);

          }

          clearThings();

        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                keyList.clear();
                for(DataSnapshot childSnap : snapshot.getChildren()){

                    Doctor d =  childSnap.getValue(Doctor.class);
                    String key = childSnap.getKey();

                    list.add(d);
                    keyList.add(key);

                    pd.dismiss();
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Doctor d = list.get(position);
        Toast.makeText(this,d.name, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onImageClick(int position, ImageView ivMenu) {

        Doctor d = list.get(position);
        String key = keyList.get(position);

        PopupMenu pop = new PopupMenu(this,ivMenu);

        pop.getMenuInflater().inflate(R.menu.update_delete_menu,pop.getMenu());

        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if(menuItem.getItemId()==R.id.action_update){

                    // update

                    binding.edtDoctorName.setText(d.name);
                    binding.edtDoctorDegree.setText(d.degree);
                    binding.edtDoctorField.setText(d.field);
                    binding.edtHospital.setText(d.hospitalName);

                    binding.btnSend.setText("Update");

                    bool = true;
                    pos = position;


                }else if(menuItem.getItemId()==R.id.action_delete){

                    // delete

                    ref.child(key).removeValue();


                }

                return false;
            }

        });

        pop.show();

    }

    void clearThings(){

        binding.edtDoctorField.setText("");
        binding.edtDoctorName.setText("");
        binding.edtHospital.setText("");
        binding.edtDoctorDegree.setText("");

    }
}