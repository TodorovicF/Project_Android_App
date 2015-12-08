package com.joinable.phatsprints.joinable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateEvent extends Fragment {
    TextView   today;
    TextView   tomorrow;
    EditText   location;
    EditText   description;
    Button     fromButton;
    Button     toButton;
    Button     shareButton;
    View       view;

    private PopupWindow timePopup;
    TimePicker picker;
    Button cancelButton;

    int screenWidth  = 0;
    int screenHeight = 0;
    Boolean day = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_event_layout, container, false);

        linkGuI();
        setupTodayTomorrowButtons();
        setupTimePickerPopup();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getScreenDimensions();
        resizeUI();
    }

    public void linkGuI() {
        location    = (EditText) view.findViewById(R.id.locationENTRY);
        description = (EditText) view.findViewById(R.id.descriptionEDITOR);
        shareButton = (Button) view.findViewById(R.id.shareButton);
        fromButton  = (Button) view.findViewById(R.id.fromButton);
        toButton    = (Button) view.findViewById(R.id.toButton);
        today       = (TextView) view.findViewById(R.id.todayText);
        tomorrow    = (TextView) view.findViewById(R.id.tomorrowText);
        today.setTextColor(Color.rgb(0, 129, 255));
        tomorrow.setTextColor(Color.rgb(185, 185, 185));

        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // send to server and facebook
            }
        });
    }

    public void resizeUI() {

    }

    public void setupTodayTomorrowButtons() {
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day) {

                }
                else {
                    today.setTextColor(Color.rgb(0,129,255));
                    tomorrow.setTextColor(Color.rgb(185,185,185));
                    day = true;
                }
            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day) {
                    today.setTextColor(Color.rgb(185,185,185));
                    tomorrow.setTextColor(Color.rgb(0,129,255));
                    day = false;
                }
                else {

                }
            }
        });
    }

    // setup OnClickListenvers for timeTo and timeFrom buttons to open TimePicker
    public void setupTimePickerPopup() {
        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePopup(0);
            }
        });
        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePopup(1);
            }
        });
    }

    // setup and initiate TimePicker popup
    public void openTimePopup(int time) {
        try {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // prepare layout of the popup window
            View layout  = inflater.inflate(R.layout.time_popup_layout,
                    (ViewGroup) view.findViewById(R.id.time_popup));
            // setup views within the popup window
            picker       = (TimePicker) layout.findViewById(R.id.timePICKER);
            cancelButton = (Button) layout.findViewById(R.id.timeSubmitBUTTON);
            // setup popup to take up entire screen
            timePopup    = new PopupWindow(layout, screenWidth, screenHeight, true);
            // center the popup layout in relation to the screen
            timePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);
            // setup the cancel button depending on which button was pressed
            if (time == 0) {
                cancelButton.setOnClickListener(acceptFromTimeAndClosePopup);
            }
            else {
                cancelButton.setOnClickListener(acceptToTimeAndClosePopup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // setup popup window's cancel button for fromButton
    private View.OnClickListener acceptFromTimeAndClosePopup = new View.OnClickListener() {
        public void onClick(View v) {
            // must use getCurrentHour for devices older than SDK v23
            String hour   = convertTo12Hour(picker.getCurrentHour());
            String minute = addZeroesToMinute(picker.getCurrentMinute());
            String AMPM   = getAMPM(picker.getCurrentHour());

            fromButton.setText(hour + ":" + minute + AMPM);
            timePopup.dismiss();
        }
    };

    // setup popup window's cancel button for toButton
    private View.OnClickListener acceptToTimeAndClosePopup = new View.OnClickListener() {
        public void onClick(View v) {
            // must use getCurrentHour for devices older than SDK v23
            String hour   = convertTo12Hour(picker.getCurrentHour());
            String minute = addZeroesToMinute(picker.getCurrentMinute());
            String AMPM   = getAMPM(picker.getCurrentHour());

            toButton.setText(hour + ":" + minute + AMPM);
            timePopup.dismiss();
        }
    };

    // add a '0' if minute is less than 10
    public String addZeroesToMinute(int minute) {
        if (minute < 10 ) {
            return "0" + minute;
        }
        else return Integer.toString(minute);
    }

    // convert to 12 hour time from 24 hour TimePicker value
    public String convertTo12Hour(int hour) {
        if (hour > 12) {
            return Integer.toString(hour - 12);
        }
        if (hour == 0) {
            return Integer.toString(12);
        }
        else {
            return Integer.toString(hour);
        }
    }

    // determine AM or PM from 24 hour value
    public String getAMPM(int hour) {
        if (hour > 12) {
            return "PM";
        }
        else if (hour == 0) {
            return "AM";
        }
        else if (hour == 12) {
            return "PM";
        }
        else return "AM";
    }

    // retrieve screen dimensions for view resizing and repositioning
    public void getScreenDimensions() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size      = new Point();
        display.getSize(size);
        screenWidth     = size.x;
        screenHeight    = size.y;
    }
}
