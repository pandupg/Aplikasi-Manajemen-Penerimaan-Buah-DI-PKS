package com.example.databuahpks.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Truck;
import com.example.databuahpks.R;
import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.ViewHolder> {

    private Context context;
    private List<Truck> truckList;
    private OnItemClickListener listener;
    private OnItemClickListener itemClickListener;
    private OnDeleteClickListener deleteClickListener;

    public interface OnItemClickListener {
        void onItemClick(Truck truck);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Truck truck);
    }

    public TruckAdapter(Context context, List<Truck> truckList, OnItemClickListener itemClickListener, OnDeleteClickListener deleteClickListener ) {
        this.context = context;
        this.truckList = truckList;
        this.listener = listener;
        this.itemClickListener = itemClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public TruckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_riwayat_truck, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckAdapter.ViewHolder holder, int position) {
        Truck truck = truckList.get(position);
        holder.tvKodeTruck.setText(truck.getKodeTruck());
        holder.tvNamaPengemudi.setText(truck.namaPengemudi);

        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(truck));
        holder.btnHapus.setOnClickListener(v -> deleteClickListener.onDeleteClick(truck));
    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKodeTruck, tvNamaPengemudi;
        ImageView btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKodeTruck = itemView.findViewById(R.id.tv_kode_truck);
            tvNamaPengemudi = itemView.findViewById(R.id.tv_nama_pengemudi);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}