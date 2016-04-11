package com.example.dono.listView;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dono.ConvertImage;
import com.example.enghany.androidproject.Medcine;
import com.example.enghany.androidproject.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class CustomList extends Activity {
    MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();
    }


    private void displayListView() {

        //Array list of countries
        ArrayList<Medcine> MedcineList = new ArrayList<Medcine>();


        // bgeeb array list elly mawgoda fe activity 2 elly heya adweya fe nafs el m3ad

        Intent intentValue=getIntent();
        MedcineList=intentValue.getParcelableArrayListExtra("medcine");

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,R.layout.custom_list_info, MedcineList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Medcine medcine = (Medcine) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + medcine.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Medcine> {

        private ArrayList<Medcine> mediceneList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Medcine> mediceneList) {
            super(context, textViewResourceId, mediceneList);
            this.mediceneList = new ArrayList<Medcine>();
            this.mediceneList.addAll(mediceneList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
            ImageView image;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.custom_list_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.image=(ImageView) convertView.findViewById(R.id.imageView3);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Medcine medcine = (Medcine) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        medcine.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Medcine medcine = mediceneList.get(position);
            ConvertImage convert=new ConvertImage();
            holder.code.setText(" (" +  medcine.getType() + ")");
            holder.name.setText(medcine.getName());
            holder.name.setChecked(medcine.isSelected());
            holder.image.setImageBitmap(convert.getPhoto(medcine.getImage()));
            holder.name.setTag(medcine);

            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList <Medcine> dataSelected =new ArrayList<Medcine>();
                ArrayList<Medcine > dataNotSelected=new ArrayList<Medcine>();

                StringBuffer responseText = new StringBuffer();
                responseText.append("medcine ...\n");

                ArrayList<Medcine> medcineList = dataAdapter.mediceneList;
                for(int i=0;i<medcineList.size();i++){

                    Medcine medcine =  medcineList.get(i);
                    if(medcine.isSelected()){
                        dataSelected.add(medcine);

                    }else{

                        dataNotSelected.add(medcine);

                        responseText.append("\n" + medcine.getName());
                    }
                }
                Intent intent = new Intent(getApplicationContext() ,Main2Activity.class);
                intent.putParcelableArrayListExtra("medcine",dataNotSelected);

                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                Notification notifi = new Notification.Builder(getApplicationContext())
                        .setTicker("medcine")
                        .setContentTitle("medcine")
                        .setContentText(responseText)
                        .setSmallIcon(R.drawable.addicon)
                        .setContentIntent(pIntent).getNotification();
                notifi.flags=Notification.FLAG_AUTO_CANCEL;
                NotificationManager notifiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notifiManager.notify(0, notifi);

                finish();



            }
        });

    }


    public  byte[] convertImageToByte (String imageUrL){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();;
        try {
            File file = new File(imageUrL);
            FileInputStream fis = new FileInputStream(file);
            //create FileInputStream which obtains input bytes from a file in a file system
            //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum);
                // System.out.println("read " + readNum + " bytes,");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        return  bytes;
    }

}
