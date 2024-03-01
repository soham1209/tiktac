package navFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import logIn.Login;
import com.example.educationapp.MainActivity;
import com.example.educationapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button btnLogout;
    Button resetPass;

//    String strEmail;
//
//    EditText edtText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MainActivity mainActivity;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        btnLogout = view.findViewById(R.id.logOut);

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                startActivity(new Intent(getActivity(), Login.class));
//                getActivity().finish();
//                Toast.makeText(getActivity(), "Successfully logout , get back soon", Toast.LENGTH_SHORT).show();




                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Are You Sure?");
                        builder.setMessage("You want to Log Out?");

                        builder.setPositiveButton("LogOut", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getActivity(), Login.class));
                                getActivity().finish();
                                Toast.makeText(getActivity(), "Successfully logout , get back soon", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder.show();

                    }
                });



//        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_setting, container, false);
        return view;

//        resetPass.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                mAuth.sendPasswordResetEmail()
//
//
//            }
//        })


    }
    


//    private void Resetpassword(){
//        resetPass.setVisibility(View.INVISIBLE);
//
//        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(getActivity(), "Reset link send", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(), Login.class);
//                startActivity(intent);
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getActivity(),"Error:-"+e.getMessage(),Toast.LENGTH_SHORT).show();
//
//                        resetPass.setVisibility(View.VISIBLE);
//
//                    }
//
//
//                });
//        return;
//    }

}