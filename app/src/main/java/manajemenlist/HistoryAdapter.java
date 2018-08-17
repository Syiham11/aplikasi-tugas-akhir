package manajemenlist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import nita.ta.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolderHistory>
        implements Filterable {

    private ArrayList<ModelHistory> data;
    private Context context;
    private FilterHistory filterHistory;

    private DeleteListener deleteListener;

    public HistoryAdapter(ArrayList<ModelHistory> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public ArrayList<ModelHistory> getData() {
        return data;
    }

    public void setData(ArrayList<ModelHistory> data) {
        this.data = data;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DeleteListener getDeleteListener() {
        return deleteListener;
    }

    public void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    @Override
    public ViewHolderHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowlist, parent, false);

        ViewHolderHistory viewHolder = new ViewHolderHistory(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderHistory holder, int position) {
        holder.txTgl.setText(data.get(position).getTanggalKejadian());
        holder.txWaktu.setText(data.get(position).getWaktuKejadian());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =
                        new AlertDialog.Builder(HistoryAdapter.this.context);

                dialog.setMessage("Apakah anda yakin ingin menghapus ?");
                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteListener.onDeleteClickListener(context, holder.getAdapterPosition());
                    }
                });

                dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ((data != null) ? data.size() : 0);
    }

    @Override
    public Filter getFilter() {
        if(filterHistory == null) {
            filterHistory = new FilterHistory(this, data);
        }

        return filterHistory;
    }

    class ViewHolderHistory extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView txTgl;
        public TextView txWaktu;
        public ImageButton btnDelete;

        public ViewHolderHistory(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.icon);
            txTgl = (TextView) itemView.findViewById(R.id.txTgl);
            txWaktu = (TextView) itemView.findViewById(R.id.txWaktu);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
        }
    }
}
