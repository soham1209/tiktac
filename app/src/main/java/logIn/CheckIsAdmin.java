package logIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educationapp.R;

import admin.AdminAddTest;
import admin.AdminTestView;

public class CheckIsAdmin extends AppCompatActivity {
    EditText text;
    Button btn;
    private static final String password = "123456";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_is_admin);

        text = findViewById(R.id.adminPass);
        btn = findViewById(R.id.checkPass);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPass = text.getText().toString().trim();
                if (password.equals(getPass)){
                    startActivity(new Intent(CheckIsAdmin.this, AdminAddTest.class));
                    finish();
                    Toast.makeText(CheckIsAdmin.this, " Admin Login Successfully ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckIsAdmin.this, "Enter The Valid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}