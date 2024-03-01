package quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class pyQuiz extends AppCompatActivity {
    private TextView questionTV,questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<pyQuizModel> quizModelArrayList;
    Random random;
    int currentScore = 0,questionAttempted = 1,currentPos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_py_quiz);
        questionTV = findViewById(R.id.idTvQuestion);
        questionNumberTV = findViewById(R.id.idTvQuestionAttempt);
        option1Btn = findViewById(R.id.idButtonOption1);
        option2Btn = findViewById(R.id.idButtonOption2);
        option3Btn = findViewById(R.id.idButtonOption3);
        option4Btn = findViewById(R.id.idButtonOption4);
        quizModelArrayList = new ArrayList<>();
//Db Code
        FirebaseDatabase.getInstance().getReference().child("Tests")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot users:snapshot.child("Python").getChildren()) {
                            String que=users.child("queNum").getValue(String.class);
                            String opt1=users.child("opt1").getValue(String.class);
                            String opt2=users.child("opt2").getValue(String.class);
                            String opt3=users.child("opt3").getValue(String.class);
                            String opt4=users.child("opt4").getValue(String.class);
                            String ans=users.child("ans").getValue(String.class);
                            Toast.makeText(pyQuiz.this, "Start Quiz", Toast.LENGTH_SHORT).show();


                            quizModelArrayList.add(new pyQuizModel(que,opt1,opt2,opt3,opt4,ans));


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        random = new Random();
        getQuizQuestion(quizModelArrayList);
        currentPos = random.nextInt(quizModelArrayList.size());
//        currentPos = quizModelArrayList.ne
        setDataView(currentPos);option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos= random.nextInt(quizModelArrayList.size());
                setDataView(currentPos);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos= random.nextInt(quizModelArrayList.size());
                setDataView(currentPos);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos= random.nextInt(quizModelArrayList.size());
                setDataView(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionAttempted++;
                currentPos= random.nextInt(quizModelArrayList.size());
                setDataView(currentPos);
            }
        });
    }
    private  void  showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(pyQuiz.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_score_sheet,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTv = bottomSheetView.findViewById(R.id.idTvScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTv.setText("Your Score is \n"+currentScore + "/10");

        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataView(currentPos);
                questionAttempted =1;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    private void setDataView(int currentPos){
        questionNumberTV.setText("Question Attempted : "+questionAttempted+"/10" );

        if(questionAttempted == 10){
            showBottomSheet();
        }
        else {
            questionTV.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModelArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModelArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModelArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModelArrayList.get(currentPos).getOption4());
        }

    }
    private void getQuizQuestion(ArrayList<pyQuizModel> quizModelArrayList) {
        quizModelArrayList.add(new pyQuizModel("Who invented Java Programming?", "Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling"));

    }
}