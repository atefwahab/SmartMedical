package com.example.enghany.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eng.Hany on 25/03/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Medcine> {
    Context context;
    ArrayList<Medcine> medicines;
//    String medicines[];
//    int images[];
/*
constructing the Arrayadapter with the maincontext and the datasource values to change it into views
made by hadia
 */
    public MyArrayAdapter(Context context,ArrayList<Medcine>medicines){
        super(context,R.layout.rowview,R.id.myText, medicines);
        this.context=context;
        this.medicines=medicines;

    }
    /*
    attaching the medicine row view on the list view
    if it is null attach it to the listview by converting it into a views object through layout inflater
    and make a view to reuse the cell
    made by hadia
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        View medicineView=convertView;
        ViewChache viewChace;
        if(medicineView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            medicineView=inflater.inflate(R.layout.rowview,parent,false);
            viewChace=new ViewChache(medicineView);
            medicineView.setTag(viewChace);
        }
        else{
            viewChace=(ViewChache)medicineView.getTag();
        }
        Medcine currentMedicine=medicines.get(position);
        TextView nameOfMedicine=viewChace.getMedicineName();
        nameOfMedicine.setText(currentMedicine.getName());
        ImageView image=viewChace.getMedicineImage();
        //image.setImageBitmap(convertArrayOfBytes(currentMedicine.getImage()));


        return medicineView;

    }

    public Bitmap convertArrayOfBytes(byte[]image){
        Bitmap bitmapImage= BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmapImage;

    }


}


