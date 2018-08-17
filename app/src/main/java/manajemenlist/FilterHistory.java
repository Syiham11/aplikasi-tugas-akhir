package manajemenlist;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterHistory extends Filter {

    private HistoryAdapter adapter;
    private ArrayList<ModelHistory> data = new ArrayList<>();

    public FilterHistory(HistoryAdapter adapter, ArrayList<ModelHistory> data) {
        this.adapter = adapter;
        this.data = data;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        // cek validitas teks yang akan dicari (constraint)
        if (constraint != null && constraint.length() > 0) {
            // ubah teks ke uppercase
            constraint = constraint.toString().toUpperCase();
            // sediakan array untuk menyimpan data hasil filter
            ArrayList<ModelHistory> filteredPlayers = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                // cek masing-masing data
                if (data.get(i).getTanggalKejadian().toUpperCase().contains(constraint)) {
                    // masukkan data ke array
                    filteredPlayers.add(data.get(i));
                }
            }

            // simpan ukuran data dan salin datanya ke results
            results.count = filteredPlayers.size();
            results.values = filteredPlayers;
        }
        else {
            // jika data original kosong, maka tetap simpan ukuran dan data ke results
            results.count = data.size();
            results.values = data;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        // set data dari adapter dengan hasil pencarian
        adapter.setData((ArrayList<ModelHistory>) results.values);

        // beri tau adapter bahwa data telah diubah
        adapter.notifyDataSetChanged();
    }
}
