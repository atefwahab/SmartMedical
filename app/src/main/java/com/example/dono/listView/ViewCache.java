package com.example.dono.listView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enghany.androidproject.R;


/**
 * Created by dono on 4/5/2016.
 */
public class ViewCache {
    private View baseView;
    private TextView departmentView;
    private  TextView sectorView;
    private ImageView imageView;

    public ViewCache(View baseView){
        this.baseView=baseView;
    }

    public View getBaseView(){
        return  baseView;
    }

    public TextView getDepartmentView(){
        if(departmentView==null){
            departmentView=(TextView) baseView.findViewById(R.id.textViewTitle);

        }
        return departmentView;
    }


    public TextView getSectorView(){
        if (sectorView==null){
            sectorView=(TextView) baseView.findViewById(R.id.textViewHeader);
        }
        return sectorView;
    }

    public ImageView getImageView(){
        if(imageView==null){
            imageView=(ImageView) baseView.findViewById(R.id.imageView);
        }
        return imageView;
    }




}
