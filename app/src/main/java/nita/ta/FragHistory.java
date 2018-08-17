package nita.ta;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import manajemenlist.DeleteListener;
import manajemenlist.HistoryAdapter;
import manajemenlist.ModelHistory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragHistory extends Fragment
        implements FloatingSearchView.OnQueryChangeListener, DeleteListener {

    private RecyclerView listHistory;
    private FloatingSearchView sv;
    private ManajemenDB db;
    private HistoryAdapter adapter;

    public FragHistory() {
        // Required empty public constructor
    }

    public HistoryAdapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_history, container, false);

        listHistory = (RecyclerView) view.findViewById(R.id.listHistory);
        sv = (FloatingSearchView) view.findViewById(R.id.fsearchview);

        sv.setOnQueryChangeListener(this);

        // akses data
        db = ManajemenDB.dapatkanObjek(container.getContext());
        ArrayList<ManajemenDB.StrukturTabel> dataDb =
                db.dapatkanSemuaData(ManajemenDB.TABEL_HISTORY);

        ArrayList<ModelHistory> data = new ArrayList<>();
        for(int i = 0; i < dataDb.size(); i++) {
            data.add(new ModelHistory(dataDb.get(i).dapatkanData(1),
                    dataDb.get(i).dapatkanData(2)));
        }

        adapter = new HistoryAdapter(data, container.getContext());
        listHistory.setAdapter(adapter);
        listHistory.setLayoutManager(
                new LinearLayoutManager(container.getContext()));

        adapter.setDeleteListener(this);

        return view;
    }

    @Override
    public void onSearchTextChanged(String oldQuery, String newQuery) {
        adapter.getFilter().filter(newQuery);
    }

    @Override
    public void onDeleteClickListener(Context context, int posisi) {
        String tgl = adapter.getData().get(posisi).getTanggalKejadian();

        db.hapus(ManajemenDB.TABEL_HISTORY, "tanggal", tgl);

        adapter.getData().remove(posisi);
        adapter.notifyDataSetChanged();
    }
}
