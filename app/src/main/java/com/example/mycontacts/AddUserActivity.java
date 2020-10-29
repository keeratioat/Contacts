package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontacts.db.AppDatabase;
import com.example.mycontacts.model.User;
import com.example.mycontacts.util.AppExecutors;
import com.example.mycontacts.util.DateFormatter;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class AddUserActivity extends AppCompatActivity {

    private Calendar mCalendar = Calendar.getInstance();
    private EditText mBirthDateEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mBirthDateEditText = findViewById(R.id.birth_date_edit_text);
        mBirthDateEditText.setInputType(InputType.TYPE_NULL);
        mBirthDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog picker = new DatePickerDialog(
                        AddUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, day);
                        String formatDate = DateFormatter.formatForUi(mCalendar.getTime());
                        mBirthDateEditText.setText(formatDate);
                    }
                },mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)
                );picker.show();
            }
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //อ่านค่าจากช่อง first_name_edit_text ,last_name_edit_text
                mFirstNameEditText = findViewById(R.id.first_name_edit_text);
                mLastNameEditText = findViewById(R.id.last_name_edit_text);
                String firstName = mFirstNameEditText.getText().toString();
                String lastName =  mLastNameEditText.getText().toString();

                Date birthDate = mCalendar.getTime();
                int gender  = 1;

               /* new AlertDialog.Builder(AddUserActivity.this)
                        .setMessage(firstName + " " + lastName +"\n" +birthDate)
                        .setPositiveButton("OK" ,null).show();*/
               User users = new User(0,firstName , lastName,birthDate,gender ,true,birthDate);
               AppExecutors executors = new AppExecutors();
               executors.diskIO().execute(new Runnable() {
                   @Override
                   public void run() {
                       AppDatabase db  = AppDatabase.getInstance(AddUserActivity.this);
                       db.userDao().addUser();
                       finish();
                   }
               });
            }
        });
    }
}