
package admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.educationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminTestView extends AppCompatActivity {
    RecyclerView AdminTestViewRecyclerView;
    FloatingActionButton addTestBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_test_view);
        addTestBtn=findViewById(R.id.addTest);

        addTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTestView.this, AdminAddTest.class));
            }
        });


    }
}