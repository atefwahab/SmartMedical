package com.example.enghany.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.enghany.androidproject.dato.User;

import java.util.ArrayList;

/**
 * Created by dono on 3/23/2016.
 */
public class MedicalDB extends SQLiteOpenHelper {

    static int DBVersion=4;
    static String DBSchema = "projectDB";
    private static String USER_FIRSTNAME="FirstName";
    private static String USER_LASTNAME="LastName";
    private static String USER_EMAIL="Email";
    private static String USER_PASSWORD="Password";
    private static String USER_GENDER="Gender";
    private static String USER_BIRTHDATE="BirthDate";
    private static String TABLE_USERS = "Users";








    public MedicalDB (Context context){
        super(context,DBSchema,null,DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Users (Id INTEGER PRIMARY Key AUTOINCREMENT, FirstName varchar(50), LastName varchar(50)," +
                "Password varchar(50) ,Gender INTEGER ,Email varchar(50),BirthDate long);");

        db.execSQL("CREATE TABLE Medicine(Id INTEGER PRIMARY Key AUTOINCREMENT,Name varchar(50), image Blob ,type varchar(20)," +
                "StartDate  long, time long,dose INTEGER )");

       // db.execSQL("CREATE TABLE Repetation(Id_M INTEGER PRIMARY Key ,number INTEGER ,per varchar(20), FOREIGN KEY Id_M REFERENCES Medicine(Id) )");

       // db.execSQL("CREATE TABLE Duration(Id_M INTEGER PRIMARY Key ,number INTEGER ,per  varchar(20), FOREIGN KEY Id_M REFERENCES Medicine(Id) )");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE Users");
       // db.execSQL("DROP TABLE Repetation");
      //  db.execSQL("DROP TABLE Duration");
        db.execSQL("DROP TABLE Medicine");
        onCreate(db);
    }



    public void insertRow(Medcine s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name", s.getName());
        System.out.println("save" + s.getName());
        values.put("type", s.getType());
        System.out.println("save" + s.getType());
        values.put("StartDate", s.getStartDate());
        System.out.println("save" + s.getStartDate());
//        values.put("time", s.getTime());
//        System.out.println("save" + s.getTime());
        values.put("dose", s.getDose());
        System.out.println("save" + s.getDose());

        db.insert("Medicine", null, values);
        System.out.print("insert is done");

    }

    public ArrayList<Medcine> retriveRow(){

        ArrayList data=new ArrayList<>();

        SQLiteDatabase db=getReadableDatabase();
        String sql="select * from Medicine";
        String []str={"Name","type","StartDate","time","dose"};
        Cursor c=db.rawQuery(sql,null);

        while (c.moveToNext()){

            Medcine s=new Medcine();
            s.setName(c.getString(1));
            Log.i("*****************", s.getName());
            s.setName(c.getString(3));

            s.setStartDate(c.getLong(4));
            //s.setTime(c.getLong(5));
            s.setDose(c.getInt(6));


//            System.out.println(c.getString(1));
//            System.out.println(c.getString(3));
//            System.out.println(c.getLong(4));
//            System.out.println(c.getLong(5));
//            System.out.println(c.getInt(6));

            data.add(s);

           // System.out.println("Arraylist not empty");
        }
        return data;
    }

    public ArrayList<Medcine> showMedicine(){

        ArrayList medicines=new ArrayList();
        String selectQuery = "select * from Medicine";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.i("--------------------", "show medicine");
        while(cursor.moveToNext()){
            Medcine medicine=new Medcine();
            medicine.setName(cursor.getString(1));
            //medicine.setImage(cursor.getBlob(3));
            medicines.add(medicine);

        }

        return medicines;

    }

    /**
     * @author atef
     * this method used to register the user
     * @param user
     */
    public void registerUser(User user){
        // get the writableDatabase.
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        // prepare contents that will be inserted ..
        ContentValues contentValues=new ContentValues();

        contentValues.put(MedicalDB.USER_FIRSTNAME,user.getFirstname());
        contentValues.put(MedicalDB.USER_LASTNAME,user.getLastname());
        contentValues.put(MedicalDB.USER_EMAIL,user.getEmail());
        contentValues.put(MedicalDB.USER_PASSWORD,user.getPassword());
        contentValues.put(MedicalDB.USER_GENDER,user.getGender());
        contentValues.put(MedicalDB.USER_BIRTHDATE,user.getBirthdate());

        // insert into db
        sqLiteDatabase.insert(MedicalDB.TABLE_USERS, null, contentValues);

        // close connection
        sqLiteDatabase.close();

    }

    /**
     * @author Atef
     * this method is used to check login information ..
     * @param email
     * @param password
     * @return
     */
    public boolean login(String email,String password){
        boolean flag = false;

        // get the database object
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        // prepare your columns == Select id,name
        String[] columns={MedicalDB.USER_PASSWORD};

        String whereClause=USER_EMAIL +"=?";
        String [] args =new String[]{email};


        // Cursor to receive the retrive data ===
        Cursor cursor=sqLiteDatabase.query(MedicalDB.TABLE_USERS,columns,whereClause,args,null,null,null);



        // get values from cursor
        while (cursor.moveToNext()){


            if(cursor.getString(0).equals(password)){

                flag = true;
            }




        }

        return flag;
    }
}
