package com.example.stepcounterapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HealthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText sleep_intake;
    private EditText water_intake;
    private TextView  sleep_goal;
    private TextView water_goal;

    private Button btn;
    //View view;

    public HealthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthFragment newInstance(String param1, String param2) {
        HealthFragment fragment = new HealthFragment();
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



        View view = inflater.inflate(R.layout.fragment_health, container, false);
        sleep_intake = (EditText) view.findViewById(R.id.editText_sleep_intake);
        water_intake = (EditText) view.findViewById(R.id.editText_water_intake);
        sleep_goal = (TextView) view.findViewById(R.id.sleep_goal);
        water_goal = (TextView) view.findViewById(R.id.water_goal);
        btn = (Button) view.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sleep_hrs = Integer.parseInt(sleep_intake.getText().toString());
                int water_litres = Integer.parseInt(water_intake.getText().toString());
                switch (sleep_hrs) {
                    case 0: {
                        sleep_goal.setText("8 hrs");
                        break;
                    }
                    case 1: {
                        sleep_goal.setText("7 hrs");

                        break;
                    }
                    case 2: {
                        sleep_goal.setText("6 hrs");
                        break;
                    }
                    case 3: {
                        sleep_goal.setText("5 hrs");
                        break;
                    }
                    case 4: {
                        sleep_goal.setText("4 hrs");
                        break;
                    }
                    case 5: {
                        sleep_goal.setText("3 hrs");
                        break;
                    }
                    case 6: {
                        sleep_goal.setText("2 hrs");
                        break;
                    }
                    case 7: {
                        sleep_goal.setText("1 hr");
                        break;
                    }
                    case 8: {
                        sleep_goal.setText("0");
                        break;
                    }
                    default: {
                        sleep_goal.setText("0");

                    }
                }

                switch (water_litres) {
                    case 0: {
                        water_goal.setText("3 litres");
                        break;
                    }
                    case 1: {
                        water_goal.setText("2 litres");
                        break;
                    }
                    case 2: {
                        water_goal.setText("1 litre");
                        break;
                    }
                    case 3: {
                        water_goal.setText("0");
                        break;
                    }

                    default: {
                        water_goal.setText("0");

                    }


                }
            }


        });

      return view;
    }
}
