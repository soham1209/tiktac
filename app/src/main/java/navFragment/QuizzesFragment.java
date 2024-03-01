package navFragment;

import android.os.Bundle;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import logIn.CheckIsAdmin;
import com.example.educationapp.MainActivity;
import com.example.educationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import quiz.androidQuiz;
import quiz.javaQuiz;
import quiz.pyQuiz;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizzesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizzesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MainActivity mainActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizzesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizzesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizzesFragment newInstance(String param1, String param2) {
        QuizzesFragment fragment = new QuizzesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quizzes, container, false);
        Button buttonJava = view.findViewById(R.id.javaButton);
        Button buttonPy = view.findViewById(R.id.pythonButton);
        Button buttonAndroid = view.findViewById(R.id.androidButton);

        mainActivity=(MainActivity)getActivity();
        buttonJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), javaQuiz.class).putExtra("TestLang","Java");
                startActivity(i);
            }
        });
        buttonPy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), pyQuiz.class).putExtra("TestLang","Python");
                startActivity(i);
            }
        });
        buttonAndroid.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(getActivity(), androidQuiz.class).putExtra("TestLang","Android");
                startActivity(i);
            }
        });
        FirebaseDatabase.getInstance().getReference()
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        for (DataSnapshot users: snapshot.child("Java").getChildren()) {
                            String que=users.child("queNum").getValue(String.class);
                            String opt1=users.child("opt1").getValue(String.class);
                            String opt2=users.child("opt2").getValue(String.class);
                            String opt3=users.child("opt3").getValue(String.class);
                            String opt4=users.child("opt4").getValue(String.class);
                            String ans=users.child("ans").getValue(String.class);
                            startActivity(new Intent(getActivity(), CheckIsAdmin.class));
                            Toast.makeText(getActivity(), que+ " "+opt1, Toast.LENGTH_SHORT).show();
//                                quizModelArrayList.add(new javaQuizModel(que,opt1,opt2,opt3,opt4,ans));
//                            javaQuizModel quizModel = new javaQuizModel(que, opt1, opt2, opt3, opt4, ans);
//                            quizModelArrayList.add(quizModel);



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    return  view;
    }
}