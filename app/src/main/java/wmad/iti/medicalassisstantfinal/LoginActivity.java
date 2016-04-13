package wmad.iti.medicalassisstantfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enghany.androidproject.MainActivity;
import com.example.enghany.androidproject.MedicalDB;
import com.example.enghany.androidproject.R;

public class LoginActivity extends Activity {

    EditText emailEditText ;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);


        /**
         * this method used to login
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    if(new MedicalDB(getApplicationContext()).login(emailEditText.getText().toString(),passwordEditText.getText().toString())){

                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(homeIntent );

                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                    }

                }else{

                    Toast.makeText(getApplicationContext(),"Empty Fields ",Toast.LENGTH_LONG).show();
                }

            }
        });



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * @author Atef
     * this method used to validate the filed
     */
    private boolean validate(){
        boolean flag =true;

        if(emailEditText.getText().toString().equals("")){
            flag =false;
        }

        if(passwordEditText.getText().toString().equals("")){
            flag=false;
        }
        return flag;
    }

}
