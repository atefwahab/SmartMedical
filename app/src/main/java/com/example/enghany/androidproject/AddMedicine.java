package com.example.enghany.androidproject;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.dono.setalarm.AlarmReceiver;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class AddMedicine extends AppCompatActivity implements TimeChange{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static private AddMedicine myActivity;
    EditText name;
    ImageView mImageView;
    Spinner medType, medDur, medRep;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    AlarmManager mgrAlarm;
    ArrayList<PendingIntent> intentArray;
    int i;
    TimePickerDialog timePickerDialog;
    long time;
    long date;
    EditText duration;
    EditText repitation;
    Button save;
    byte img[];
    String medcineDru,medcineType,medcineRep;
    TimePicker timer = new TimePicker();
    Calendar calSet = Calendar.getInstance();
    ImageView myTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_medicine);
        mImageView = (ImageView) findViewById(R.id.imageView);
        save = (Button) findViewById(R.id.save);
        // connect alarm with project mahmoud
        myTime=(ImageView)findViewById(R.id.myTime);
        myTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimePickerDialog(false);
            }
        });

        mgrAlarm = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        intentArray = new ArrayList<PendingIntent>();
        i=0;

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatchTakePictureIntent();

            }
        });

        name = (EditText) findViewById(R.id.medcineName);

        medicineType();
        medicineDruation();
        medicineRepitation();
        //showDays();
        myActivity = this;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMdecineData();

                Intent intent =new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //getMdecineData();


    }


    /**
     * medcine type drop down menu
     */
    private void medicineType() {
        medType = (Spinner) findViewById(R.id.medType_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medType_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medType.setAdapter(adapter);
        medType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medcineType=medType.getSelectedItem().toString();
                Log.i("@@@@@@@@@",medcineType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /**
     * medcine duration drop down
     */
    private void medicineDruation() {
        medDur = (Spinner) findViewById(R.id.medDru_spinner);
        EditText druation = (EditText) findViewById(R.id.druation);
        druation.getText();
        Log.i(String.valueOf(druation.getText()), "medicineDruation: ");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medDur_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medDur.setAdapter(adapter);
        medDur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medcineDru = medDur.getSelectedItem().toString();
                Log.i("@@@@@@@@@@",medcineDru);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //medcineDru=medDur.getSelectedItem().toString();
        //Log.i("^^^^^^^^^^^^^^^^", medcineDru);


    }

    /**
     * medcine repition
     */
    private void medicineRepitation() {
        medRep = (Spinner) findViewById(R.id.repetition);
        EditText druation = (EditText) findViewById(R.id.Repetition);
        druation.getText();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medRep_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medRep.setAdapter(adapter);
        medRep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medcineRep=medRep.getSelectedItem().toString();
                Log.i("@@@@@@@@@@",medcineRep);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Time Picker
     */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Date Picker
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * days of weeks
     */
    public void showDays() {
        final CharSequence[] items = {" SunDay ", " MonDay ", " TuesDay ", " WendsDay", "thursday", "FriDay"};
        // arraylist to keep the selected items
        final ArrayList seletedItems = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select days Now");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel

                    }
                });

        AlertDialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();
    }

    public static AddMedicine getInstance() {
        return myActivity;
    }

    /***
     * get all data of form
     */
    private void getMdecineData() {

        Medcine medicine = new Medcine();
        duration = (EditText) findViewById(R.id.druation);
        repitation = (EditText) findViewById(R.id.Repetition);
        /***
         * convert the image view to bitmap then compress the bitmap image to byte of array
         * **/
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img = bos.toByteArray();

//        String medcinType=medType.getItemAtPosition(medType.getSelectedItemPosition()).toString();
//        Log.i("&&&&&&&&&&&&&",medcinType);
        //String medcineDruation=medDur.getSelectedItem().toString();
        // String medcineRepation=medRep.getSelectedItem().toString();
//        medcine.setType(medcinType);
//        medcine.setTypeRepetition(medcineRepation);
//        medcine.setTypeDuration(medcineDruation);

        //set the medcine name
        //Log.i("@@@@@@@@@@@@",medcineDru);
        medicine.setName(name.getText().toString());
        //set the image
        medicine.setImage(img);
        // set the medcine type
        medicine.setType(medcineType);
        //set the duration
        medicine.setTypeDuration(medcineDru);
        //Log.i("#########",medcineDru);
        //set number of duration
        medicine.setNumDuration(Integer.parseInt(duration.getText().toString()));
        //set the repitation type
        medicine.setTypeRepetition(medcineRep);
        // set number of repitation
        medicine.setNumRepetition(Integer.parseInt(repitation.getText().toString()));
        //set start time
        medicine.setMedTime(time);
        //set Date
        medicine.setStartDate(date);
        // insert into database
        MedicalDB medcineObject=new MedicalDB(getApplicationContext());
        medcineObject.insertRow(medicine);
        ArrayList<Medcine> med= new ArrayList();
        med=medcineObject.retriveRow();
        // Log.i("!!!!!!!!!",med.get(5).getType());

        for (int i=0;i<med.size();i++){
            Log.i("!!!!!!!!!",med.get(i).getName());
            Log.i("!!!!!!!!!",String.valueOf(med.get(i).getStartDate()));

        }
    }

    @Override
    public void changeTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        time = cal.getTimeInMillis();

        Log.i("================", cal.getTime().toString());

    }

    @Override
    public void changeDate(int year, int month, int day) {

        Calendar calendar= Calendar.getInstance();
        calendar.set(year,month,day);
        calendar.set(year,month,day);
        // get date object from Calendar to print date
        date= calendar.getTimeInMillis();
        Log.i("++++++++++", calendar.getTime().toString());

    }
    /***
     * my inner class for time
     */
    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        TimeChange tm;

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            tm.changeTime(hourOfDay, minute);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            tm = (TimeChange) activity;
        }
    }
    /**
     * my inner class for date
     */
    public static class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        TimeChange tm;
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            tm =(TimeChange) activity;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar= Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);




            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        /**
         * @param view        The view associated with this listener.
         * @param year        The year that was set.
         * @param monthOfYear The month that was set (0-11) for compatibility
         *                    with {@link Calendar}.
         * @param dayOfMonth  The day of the month that was set.
         */
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tm.changeDate(year, monthOfYear, dayOfMonth);
        }
    }// end of datepicker class


    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(AddMedicine.this, onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), is24r);
        timePickerDialog.setTitle("Set Alarm");
        // flag=false;
        timePickerDialog.show();

    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {

            Calendar calSet = Calendar.getInstance();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);


            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {


        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        intentArray.add(pendingIntent);
    }





}


