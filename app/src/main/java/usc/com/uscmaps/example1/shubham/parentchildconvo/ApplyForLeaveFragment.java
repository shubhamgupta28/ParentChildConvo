package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shubham on 3/12/17.
 */

public class ApplyForLeaveFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fdf", ":Dfd");
        View rootView = inflater.inflate(R.layout.apply_for_leave, container, false);
        final TextView fromDateTV = (TextView) rootView.findViewById(R.id.fromDate);
        final TextView toDateTV = (TextView) rootView.findViewById(R.id.toDate);
        ImageButton setDate1 = (ImageButton) rootView.findViewById(R.id.pickDate1);
        ImageButton setDate2 = (ImageButton) rootView.findViewById(R.id.pickDate2);

        setDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy"); // Set your date format
                        String currentData = sdf.format(calendar.getTime());

                        fromDateTV.setText(currentData);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        setDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy"); // Set your date format
                        String currentData = sdf.format(calendar.getTime());

                        toDateTV.setText(currentData);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());


        // textView is the TextView view that should display it
        fromDateTV.setText(currentDateTimeString);
        toDateTV.setText(currentDateTimeString);

        return rootView;
    }


}
