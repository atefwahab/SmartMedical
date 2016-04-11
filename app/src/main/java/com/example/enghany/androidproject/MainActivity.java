package com.example.enghany.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dono.setalarm.RemindActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static MainActivity inst;
    ArrayList<Medcine> medicines;

    ListView lv;

    private GoogleApiClient client;

    static MainActivity myActivity;

    public MainActivity getActivity (){
        return this;
    }


    public static MainActivity instance() {
        return inst;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                    //defining the list
        MedicalDB db=new MedicalDB(getApplicationContext());
        medicines=db.showMedicine();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Medcine> adapter = new MyArrayAdapter(getApplicationContext(), medicines);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), MedInfo.class);
                TextView medicineText = (TextView) view.findViewById(R.id.myText);
                String medicineName = medicineText.getText().toString();
                intent.putExtra("medPressed", medicineName);

                startActivity(intent);


            }

        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



/*
create the long press menu
made by hadia
 */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Medicine Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Remove");
    }

/*
handling  the pressed action on the long press menu
edit and remove button will appear
made by hadia
 */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;

        if (item.getTitle() == "Edit") {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);

            info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            TextView medicineText = (TextView) info.targetView.findViewById(R.id.myText);
            String medicineName = medicineText.getText().toString();
            intent.putExtra("medPressed", medicineName);
            startActivity(intent);
//        else if(item.getTitle()=="Remove"){}
//        else {return false;}

        }
        return true;
    }
/*
making an add icon on the action bar
made by hadia

 */
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
/*
 handling the action if the add icon is pressed
 made by hadia
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addIcon) {
            Intent intent = new Intent(getApplicationContext(), AddMedicine.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.enghany.androidproject/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.enghany.androidproject/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public  void StartDialog(){



        Intent intent2 = new Intent(getBaseContext(), RemindActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendBroadcast(intent2);
        getApplication().startActivity(intent2);
    }
}