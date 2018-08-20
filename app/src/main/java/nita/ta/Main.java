package nita.ta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import manajemendb.ManajemenDB;
import manajemenlist.DeleteListener;

public class Main extends AppCompatActivity
        implements View.OnClickListener {

    public Button btnHistory;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ManajemenDB db;

    private FragHistory fragHistory = new FragHistory();
    private AlarmCntrl fragAlarm = new AlarmCntrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        aturTabs(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        db = ManajemenDB.dapatkanObjek(this);
    }

    @Override
    public void onClick(View view) {
    }

    public void aturTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.tambahTab(fragAlarm, "ALARM");
        adapter.tambahTab(fragHistory, "HISTORY");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> dataKonten = new ArrayList<>();
        private List<String> dataLabelTab = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return dataKonten.get(position);
        }

        @Override
        public int getCount() {
            return dataKonten.size();
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return dataLabelTab.get(pos);
        }

        public void tambahTab(Fragment frag, String label) {
            dataKonten.add(frag);
            dataLabelTab.add(label);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuAbout) {
            startActivity(new Intent(this, About.class));
        }
        else if(id == R.id.menuDeleteAll) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Apakah anda yakin ingin menghapus semua data history ?");
            dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.hapusSemua(ManajemenDB.TABEL_HISTORY);
                    fragHistory.getAdapter().getData().clear();
                    fragHistory.getAdapter().notifyDataSetChanged();
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


        return super.onOptionsItemSelected(item);
    }
}
