package com.example.enghany.androidproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dono on 4/5/2016.
 */
public class Medcine implements Parcelable {


    boolean selected = false;

    private int id;
    private String name;        // Medcine Name
    private String type;        //pill, injection, syrup
    private long startDate;     //the beginning of medcine date
    byte [] image;      //Medcine image
    long medTime;       //Start Time for medcine alarm
    int  dose; //items per dose
    /**

     * Medcine Duration
     * (number) [days / weeks / months / years]
     */
    int numDuration;
    String typeDuration;
    /*
    * Medcine repetition
    * (number) [hours/day/week/month]
    * */
    int numRepetition ;
    String typeRepetition;


    public Medcine(){

    }

    public Medcine(String name,String type ,byte[] image,int dose){
        this.name =name;
        this.type=type;
        this.image =image;
        this.dose =dose;
    }

    public Medcine(String name,String type ,byte[] image , boolean selected){
        this.name =name;
        this.image =image;
        this.type=type;
        this.selected = selected;
    }


    protected Medcine(Parcel in) {
        selected = in.readByte() != 0;
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        startDate = in.readLong();
        image = in.createByteArray();
        medTime = in.readLong();
        dose = in.readInt();
        numDuration = in.readInt();
        typeDuration = in.readString();
        numRepetition = in.readInt();
        typeRepetition = in.readString();
    }

    public static final Creator<Medcine> CREATOR = new Creator<Medcine>() {
        @Override
        public Medcine createFromParcel(Parcel in) {
            return new Medcine(in);
        }

        @Override
        public Medcine[] newArray(int size) {
            return new Medcine[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public int getNumDuration() {
        return numDuration;
    }

    public void setNumDuration(int numDuration) {
        this.numDuration = numDuration;
    }

    public String getTypeDuration() {
        return typeDuration;
    }

    public void setTypeDuration(String typeDuration) {
        this.typeDuration = typeDuration;
    }

    public int getNumRepetition() {
        return numRepetition;
    }

    public void setNumRepetition(int numRepetition) {
        this.numRepetition = numRepetition;
    }

    public String getTypeRepetition() {
        return typeRepetition;
    }

    public void setTypeRepetition(String typeRepetition) {
        this.typeRepetition = typeRepetition;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public long getMedTime() {
        return medTime;
    }

    public void setMedTime(long medTime) {
        this.medTime = medTime;
    }


    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeLong(startDate);
        dest.writeByteArray(image);
        dest.writeLong(medTime);
        dest.writeInt(dose);
        dest.writeInt(numDuration);
        dest.writeString(typeDuration);
        dest.writeInt(numRepetition);
        dest.writeString(typeRepetition);
    }
}
