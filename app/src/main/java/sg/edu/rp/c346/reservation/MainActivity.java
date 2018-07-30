package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    final  Calendar c = Calendar.getInstance();
    EditText etName;
    EditText etNum;
    EditText etPax;
    CheckBox cbSmoke;

    EditText etDate;
    EditText etTime;
    Button btnSubmit;
    Button btnReset;
    int dd = c.get(Calendar.DAY_OF_MONTH);
    int mm = c.get(Calendar.MONTH);
    int yy = c.get(Calendar.YEAR);

    int hr = c.get(Calendar.HOUR_OF_DAY);
    int min = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etNum = findViewById(R.id.etMobile);
        etPax = findViewById(R.id.etPax);
        cbSmoke = findViewById(R.id.checkboxSmoke);

        etDate = findViewById(R.id.editTextDate);
        etTime = findViewById(R.id.editTextTime);
        btnSubmit = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);



       etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        etDate.setText( dayOfMonth + "/" + (monthofYear+1) + "/" + year);
                        dd = dayOfMonth;
                        mm = monthofYear;
                        yy = year;

                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener,yy,mm,dd);

                myDateDialog.show();
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker View, int hourofDay, int minute) {
                        etTime.setText( hourofDay + ":" +minute);
                        hr = hourofDay;
                        min = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,hr,min,true);

                myTimeDialog.show();
            }
        });


       btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = "Name: " + etName.getText().toString();
                String mobile = "Mobile: " + etNum.getText().toString();
                String pax = "Pax: " + etPax.getText().toString();
                String text = "Area: ";
                String area = "";

                if (cbSmoke.isChecked()) {
                    area = text + "Smoking Allowed";
                } else {
                    area = text + "Smoking not Allowed";
                }
                String displayDateTime = "Date: " + etDate.getText().toString() + "\n" + "Time: " + etTime.getText().toString();
                String msg = name + "\n" + mobile + "\n" + pax + "\n" + area + "\n" + displayDateTime;

                if (etName.getText().toString().length() == 0 || etNum.getText().toString().length() == 0 || etPax.getText().toString().length()== 0 ) {
                    Toast.makeText(MainActivity.this, "Missing Fields Required!", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setTitle("Confirm Your Order");
                    myBuilder.setMessage(msg);
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                        }
                    });

                    myBuilder.setNeutralButton("Cancel", null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }
            }


        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText(null);
                etNum.setText(null);
                etPax.setText(null);
                etTime.setText(null);
                etDate.setText(null);
                if (cbSmoke.isChecked()) {
                    cbSmoke.setChecked(false);
                }

            }
        });

    }
}
