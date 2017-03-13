package com.example.value.instantresume;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainFragment extends Fragment {
    Button send_button;
    EditText phone_input;
    TextView url_view;
    String phone_input_num;
    String url = "";
    final String URL_RETRIEVAL_ERROR = "Error: not able to get url from storage";
    final String DEFAULT_URL = "No Url saved";

    static MainFragment init(int val) {
        MainFragment truitonFrag = new MainFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    url = getUrlFromStorage();

                    url_view.setText(url, TextView.BufferType.EDITABLE);
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {}
        };

        ( (MainActivity)getActivity()).setOnPageChangeListener(listener);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_main, container,
                false);

        url_view = (TextView) layoutView.findViewById(R.id.linkedin_url);
        phone_input   = (EditText) layoutView.findViewById(R.id.phone_num_input);
        send_button = (Button) layoutView.findViewById(R.id.text_message_button);
        setHasOptionsMenu(true);

        url = getUrlFromStorage();
        url_view.setText(url, TextView.BufferType.EDITABLE);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_input_num = phone_input.getText().toString();

                String send_result_message;
                if ( ! validatePhoneNum(phone_input_num)) {
                    send_result_message = "Not a valid Phone Number";
                } else {
                    sendSMS(phone_input_num, url);
                    send_result_message = "URL sent to " + phone_input_num;
                }
                Toast.makeText(getActivity(), send_result_message,Toast.LENGTH_SHORT).show();
            }
        });

        return layoutView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.clear();
        menuInflater.inflate(R.menu.activity_main_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_go_to_setup:
                goToSetup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getUrlFromStorage() {
        String url;
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (preferences.contains("url")) {
            url = preferences.getString("url", URL_RETRIEVAL_ERROR);
        } else {
            url = DEFAULT_URL;
        }

        return url;
    }

    private void goToSetup() {
        final int INTEGER_SETUP_PAGE_NUM = getResources().getInteger(R.integer.integer_setup_page_num);
        ( (MainActivity)getActivity() ).setCurrentPagerItem(INTEGER_SETUP_PAGE_NUM);
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public static boolean validatePhoneNum(String phone_input_num) {
        boolean isOnlyDigits = Pattern.matches("^[0-9]+$", phone_input_num);
        return ( (isOnlyDigits) && (phone_input_num.length() == 10) );
    }
}