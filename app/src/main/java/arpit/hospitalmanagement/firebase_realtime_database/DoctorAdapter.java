package arpit.hospitalmanagement.firebase_realtime_database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import arpit.hospitalmanagement.databinding.DoctorRowItemBinding;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DocViewHolder> {

    ArrayList<Doctor> list;
    OnItemClickInterface clickInterface;
    Context context;

    interface OnItemClickInterface{

        void onItemClick(int position);
        void onImageClick(int position, ImageView ivMenu);

    }

    DoctorAdapter(ArrayList<Doctor>list,OnItemClickInterface clickInterface){
        this.list = list;
        this.clickInterface=clickInterface;
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        DoctorRowItemBinding binding = DoctorRowItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DocViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {

        Doctor d = list.get(position);

        holder.binding.setObj(d);

        holder.binding.ivMenu.setOnClickListener(view -> {

            clickInterface.onImageClick(position, holder.binding.ivMenu);

        });

        holder.itemView.setOnClickListener(view -> {

           clickInterface.onItemClick(position);

        });

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
