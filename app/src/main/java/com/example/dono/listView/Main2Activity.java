package com.example.dono.listView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dono.ConvertImage;
import com.example.enghany.androidproject.R;
import com.example.enghany.androidproject.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Medcine> arr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView listView=(ListView)findViewById(R.id.listView);

        Intent intent=getIntent();
        if( intent.getParcelableArrayListExtra("medcine") != null) {

            System.out.println("inside intent");
           arr =intent.getParcelableArrayListExtra("medcine");

            for (int i=0; i<arr.size();i++){
                System.out.println("////////////////////////////////////"+arr.size());
                System.out.println(arr.get(i).getName());
            }


        }else{

            System.out.println("////////////////////////////// no array ///////////////////////////////");
            Bitmap image1= BitmapFactory.decodeResource(getBaseContext().getResources(),
                    R.drawable.addicon);

            Bitmap image2= BitmapFactory.decodeResource(getBaseContext().getResources(),
                    R.drawable.addicon);

            Bitmap image3= BitmapFactory.decodeResource(getBaseContext().getResources(),
                    R.drawable.addicon);


            ConvertImage convert=new ConvertImage();

            Medcine obj1 = new Medcine("pnadol", "drug", convert.getBytes(image1), 3);

            Medcine obj2 = new Medcine("esprine", "drink", convert.getBytes(image2), 2);

            Medcine obj3 = new Medcine("catafast", "drug", convert.getBytes(image3), 4);


            arr.add(obj1);
            arr.add(obj2);
            arr.add(obj3);
        }

        MyCustom adapterMedicine =new MyCustom(getApplicationContext(),arr);
        listView.setAdapter(adapterMedicine);
        listView.setOnItemClickListener(this);
    }

   public  byte[] convertImageToByte (String imageUrL){
       ByteArrayOutputStream  bos = new ByteArrayOutputStream();;
       try {
       File file = new File(imageUrL);
       FileInputStream fis = new FileInputStream(file);
       //create FileInputStream which obtains input bytes from a file in a file system
       //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

       byte[] buf = new byte[1024];

           for (int readNum; (readNum = fis.read(buf)) != -1;) {
               bos.write(buf, 0, readNum);
           }
       } catch (Exception ex) {
          ex.printStackTrace();
       }
       byte[] bytes = bos.toByteArray();
       return  bytes;
   }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + arr.get(position).getName(),
                Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

          passengerInformationPopup(arr.get(position));
    }


    public void passengerInformationPopup(Medcine medcine) {

        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.list_dialog);
        TextView header = (TextView) dialog.findViewById(R.id.header);
        ImageView image=(ImageView)dialog.findViewById(R.id.imageView);
        TextView dose = (TextView) dialog.findViewById(R.id.dose);
        final Button accept = (Button) dialog.findViewById(R.id.accept);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        header.setText(medcine.getName());
        ConvertImage convert=new ConvertImage();

        Bitmap bitmap = convert.getPhoto(medcine.getImage()) ;

        image.setImageBitmap(bitmap);

         dose.setText("Dose : "+medcine.getDose());


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
                Intent intent2 = new Intent(getBaseContext(), CustomList.class);
                intent2.putParcelableArrayListExtra("medcine",arr);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();

            }
        });
        dialog.show();
    }



}
