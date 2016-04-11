package com.example.enghany.androidproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Eng.Hany on 25/03/2016.
 */
public class ViewChache {
    private View baseView;
    private TextView medicineName;
    private ImageView MedicineImage;

    public ViewChache(View baseView){
        this.baseView=baseView;

    }

    public View getBaseView() {

        return baseView;
    }

    public ImageView getMedicineImage() {
        if(MedicineImage==null){
            MedicineImage= (ImageView) baseView.findViewById(R.id.myImage);
        }
        return MedicineImage;
    }

    public void setMedicineImage(ImageView imageView) {
        this.MedicineImage = MedicineImage;
    }



    public TextView getMedicineName() {
        if(medicineName==null){
            medicineName= (TextView) baseView.findViewById(R.id.myText);
        }
        return medicineName;
    }

    public void setMedicineName(TextView medicineView) {
        this.medicineName = medicineName;
    }

    public void setBaseView(View baseView) {

        this.baseView = baseView;
    }
}
