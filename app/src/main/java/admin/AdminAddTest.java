package admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educationapp.R;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddTest extends AppCompatActivity {
    EditText queNum,opt1,opt2,opt3,opt4,testLang,queAns;
    Button addQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_test);
        queNum=findViewById(R.id.QueNum);
        opt1=findViewById(R.id.Opt1);
        opt2=findViewById(R.id.Opt2);
        opt3=findViewById(R.id.Opt3);
        opt4=findViewById(R.id.Opt4);
        testLang=findViewById(R.id.TestLang);
        queAns=findViewById(R.id.queAns);
        addQuestionBtn=findViewById(R.id.addQuestion);

        /*Method For Add data in the database by Admin*/
        addQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testLanguage=testLang.getText().toString().trim();
                String quenumber=queNum.getText().toString().trim();
                String Answer=queAns.getText().toString().trim();
                String option1=opt1.getText().toString().trim();
                String option2=opt2.getText().toString().trim();
                String option3=opt3.getText().toString().trim();
                String option4=opt4.getText().toString().trim();

                Toast.makeText(AdminAddTest.this, "Question Added", Toast.LENGTH_SHORT).show();

                // add variables in database as a container of data which sent by admin
                AdminAddTestModdel add= new AdminAddTestModdel(
                        quenumber,option1,option2,option3,option4,Answer
                );

                //to set the child data in the db
                FirebaseDatabase.getInstance().getReference().child("Tests")
                        .child(testLanguage)
                        .child(quenumber)
                        .setValue(add);

                //To refresh add quize page
                testLang.setText("");
                opt1.setText("");
                opt2.setText("");
                opt3.setText("");
                opt4.setText("");
                queNum.setText("");
                queAns.setText("");
            }

        });

    }
}