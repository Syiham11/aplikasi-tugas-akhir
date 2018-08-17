package manajemendb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PengisianData {
    public static void isiData(SQLiteDatabase sqldb) {
        ContentValues val = new ContentValues();

        val = new ContentValues();
        val.put("tanggal", "2018-08-13");
        val.put("waktu", "13.00");
        sqldb.insert(ManajemenDB.TABEL_HISTORY, null, val);

        val = new ContentValues();
        val.put("tanggal", "2017-12-13");
        val.put("waktu", "12.00");
        sqldb.insert(ManajemenDB.TABEL_HISTORY, null, val);

        val = new ContentValues();
        val.put("tanggal", "2019-08-13");
        val.put("waktu", "14.00");
        sqldb.insert(ManajemenDB.TABEL_HISTORY, null, val);

        val = new ContentValues();
        val.put("tanggal", "2020-08-13");
        val.put("waktu", "14.55");
        sqldb.insert(ManajemenDB.TABEL_HISTORY, null, val);
    }
}
