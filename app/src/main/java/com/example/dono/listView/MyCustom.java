package com.example.dono.listView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dono.ConvertImage;
import com.example.enghany.androidproject.Medcine;
import com.example.enghany.androidproject.R;

import java.util.ArrayList;

/**
 * Created by dono on 4/5/2016.
 */
public class MyCustom extends ArrayAdapter {




    ArrayList<Medcine> arr=new ArrayList<>();

   // private int [] images={R.drawable.asprine,R.drawable.addicon,R.drawable.brufen};
    private Context context;



    public MyCustom(Context context,ArrayList<Medcine> arr){
        super(context,R.layout.my_custom_layout, R.id.textViewTitle,arr);
        this.arr=arr;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        //high performance custom list

        View rawView=convertView;
        ViewCache viewCache;
        final  int posisionCopy=position;
        if(rawView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rawView=inflater.inflate(R.layout.my_custom_layout,parent,false);
            viewCache=new ViewCache(rawView);
            rawView.setTag(viewCache);
        }else{
            viewCache=(ViewCache)rawView.getTag();


        }
        TextView header= viewCache.getDepartmentView();
        header.setText(arr.get(position).getType());

        viewCache=(ViewCache)rawView.getTag();
        TextView title= viewCache.getSectorView();
        title.setText(arr.get(position).getName());


        viewCache=(ViewCache) rawView.getTag();
        ImageView img=viewCache.getImageView();



        ConvertImage convert=new ConvertImage();

        Bitmap image=convert.getPhoto(arr.get(position).getImage());

        img.setImageBitmap(image);


        // return view;
        return  rawView;
    }



}
