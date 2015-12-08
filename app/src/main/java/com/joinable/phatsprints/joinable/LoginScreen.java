package com.joinable.phatsprints.joinable;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginScreen extends FragmentActivity {
    TextView        title;
    Button          loginButton;
//    LoginButton     facebookLogin;
//    CallbackManager callbackManager;

    // screen sizes for resizing/repositioning views
    int screenWidth  = 0;
    int screenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(this.getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.login_screen);

        linkGUI();
        setupLoginButton();
    }

    // manipulate UI views once the elements are fully rendered and sizes
    // are obtainable
    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getScreenDimensions();
            repositionUI();
            resizeUI();
        }
    }

    // link the UI to their respective layouts
    public void linkGUI() {
        title       = (TextView) findViewById(R.id.joinableTEXT);
        loginButton = (Button) findViewById(R.id.loginBUTTON);
    }

    // simple button to begin the main activity
    public void setupLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(login);
                finish();
            }
        });
    }

    // reposition UI elements based on screen size
    public void repositionUI() {
        title.setY((screenHeight/20)*8);
        loginButton.setY((screenHeight/20)*12);
    }

    // resize UI elements based on screen size
    public void resizeUI() {
        title.setTextSize(screenWidth/10);
    }

    // retrieve the screen dimensions
    public void getScreenDimensions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size      = new Point();
        display.getSize(size);
        screenWidth     = size.x;
        screenHeight    = size.y;
    }
}
