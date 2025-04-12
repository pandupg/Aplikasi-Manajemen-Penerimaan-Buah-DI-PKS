package com.example.databuahpks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.databuahpks.Database.Buah;
import com.example.databuahpks.R;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BuahAdapter extends RecyclerView.Adapter<BuahAdapter.BuahViewHolder> {

    private Context context;
    private List<Buah> buahList;

    public BuahAdapter(Context context, List<Buah> buahList) {
        this.context = context;
        this.buahList = buahList;
    }

    @Override
    public BuahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_riwayat_buah, parent, false);
        return new BuahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuahViewHolder holder, int position) {
        Buah buah = buahList.get(position);

        // Format tanggal
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        //String tanggal = sdf.format(buah.tanggal);  // Assuming Buah has a 'tanggal' field (Date type)

        holder.textTanggal.setText(buah.tanggal);
        holder.textBeratDatang.setText("Berat Datang: " + buah.beratDatang);
        holder.textBeratPulang.setText("Berat Pulang: " + buah.beratPulang);
        holder.textJumlahMatang.setText("Jumlah Matang: " + buah.jumlahMatang);
        holder.textJumlahMentah.setText("Jumlah Mentah: " + buah.jumlahMentah);
        holder.textJumlahKematangan.setText("Jumlah Kematangan: " + buah.jumlahKematangan);
        holder.textJumlahBusuk.setText("Jumlah Busuk: " + buah.jumlahBusuk);
    }

    @Override
    public int getItemCount() {
        return buahList.size();
    }

    public class BuahViewHolder extends RecyclerView.ViewHolder {

        TextView textTanggal, textBeratDatang, textBeratPulang, textJumlahMatang, textJumlahMentah, textJumlahKematangan, textJumlahBusuk;

        public BuahViewHolder(View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.textTanggal);
            textBeratDatang = itemView.findViewById(R.id.textBeratDatang);
            textBeratPulang = itemView.findViewById(R.id.textBeratPulang);
            textJumlahMatang = itemView.findViewById(R.id.textJumlahMatang);
            textJumlahMentah = itemView.findViewById(R.id.textJumlahMentah);
            textJumlahKematangan = itemView.findViewById(R.id.textJumlahKematangan);
            textJumlahBusuk = itemView.findViewById(R.id.textJumlahBusuk);
        }
    }
}
