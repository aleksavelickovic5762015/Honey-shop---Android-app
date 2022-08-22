package com.example.pcelarksakuca_android10;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Funkcije extends AppCompatActivity {
    public void izgled(ActionBar actionBar, Window window, Resources resursi, LinearLayout ll, Object thisInst ){
        //ActionBar actionBar = getSupportActionBar();
        //Window window = this.getWindow();
        //TRANSPARENT ACTION BAR
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //LOGO
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        //actionBar.setHomeAsUpIndicator(R.drawable.logo_lat_1);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_lat_1);//logo_lat_1_40p
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.LEFT
                | Gravity.CENTER_VERTICAL);
        //layoutParams.rightMargin = 40;
        //
        imageView.setLayoutParams(layoutParams);
        //
        imageView.setAdjustViewBounds(true);
        imageView.getLayoutParams().height = 175;
        //
        //imageView.setClickable(true);
        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });*/
        //
        actionBar.setCustomView(imageView);
        ////////////////////////////////////////////

        ///////Boja status bara
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(resursi.getColor(R.color.statusBarColor));
        ///////

        //slika
        ll.setBackgroundResource(R.drawable.pozadina3);
    }
}

