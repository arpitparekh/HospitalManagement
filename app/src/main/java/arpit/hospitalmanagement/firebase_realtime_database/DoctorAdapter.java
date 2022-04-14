package arpit.hospitalmanagement.firebase_realtime_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import arpit.hospitalmanagement.databinding.DoctorRowItemBinding;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DocViewHolder> {

    ArrayList<Doctor> list;

    DoctorAdapter(ArrayList<Doctor>list){
        this.list = list;
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        DoctorRowItemBinding binding = DoctorRowItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DocViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        Doctor d = list.get(position);

        holder.binding.setObj(d);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DocViewHolder extends RecyclerView.ViewHolder {
        DoctorRowItemBinding binding;
        public DocViewHolder(@NonNull DoctorRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
