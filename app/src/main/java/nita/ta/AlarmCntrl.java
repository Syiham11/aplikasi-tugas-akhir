package nita.ta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmCntrl extends Fragment implements View.OnClickListener {

    private Button btnCctv;

    public AlarmCntrl() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_cntrl, container, false);

        btnCctv = (Button) view.findViewById(R.id.btnCctv);
        btnCctv.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCctv) {
            Intent inten = getContext().getPackageManager()
                    .getLaunchIntentForPackage("com.xm.csee.debug");
            if(inten != null) {
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
            }
        }
    }
}
