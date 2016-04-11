package com.example.enghany.androidproject;

/**
 * Created by Eng.Hany on 05/04/2016.
 */
public class Medicine {

    private int id;
    private String name;        // Medcine Name
    private String type;        //pill, injection, syrup

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

    private long startDate;     //the beginning of medcine date
    byte [] image;      //Medcine image
    long medTime;       //Start Time for medcine alarm
    int  dose; //items per dose

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
}
