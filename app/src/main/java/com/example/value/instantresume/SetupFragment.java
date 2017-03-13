package com.example.value.instantresume;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetupFragment extends Fragment {
    int fragVal;
    EditText input_url;
    Button save_button;
    final String SAVE_SUCCESS_MESSAGE = "Saved locally";
    final String SAVE_ERROR_MESSAGE = "Error saving locally";

    static SetupFragment init(int val) {
        SetupFragment truitonFrag = new SetupFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layoutView = inflater.inflate(R.layout.fragment_setup, container,
                false);
        input_url   = (EditText) layoutView.findViewById(R.id.input_url);
        save_button = (Button) layoutView.findViewById(R.id.button_save);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = input_url.getText().toString();
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putString( "url", url);
                boolean result = prefEditor.commit();

                String result_message;
                if (result) {
                    result_message = SAVE_SUCCESS_MESSAGE;
                } else {
                    result_message = SAVE_ERROR_MESSAGE;
                }
                Toast.makeText(getActivity(), result_message, Toast.LENGTH_SHORT).show();

                int INTEGER_MAIN_PAGE_NUM = getResources().getInteger(R.integer.integer_main_page_num);
                ( (MainActivity)getActivity() ).setCurrentPagerItem(INTEGER_MAIN_PAGE_NUM);
            }
        });

        return layoutView;
    }

}