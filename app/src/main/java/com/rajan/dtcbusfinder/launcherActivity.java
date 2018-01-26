package com.rajan.dtcbusfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by user on 4/11/2016.
 */
public class launcherActivity extends Activity implements OnClickListener {

    Button bLocate , bFindBusTrain ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.rajan.dtcbusfinder.R.layout.launcher_activity);
        bLocate = (Button) findViewById(com.rajan.dtcbusfinder.R.id.bLocation);
        bFindBusTrain = (Button) findViewById(com.rajan.dtcbusfinder.R.id.bGoToFind);

        bLocate.setOnClickListener(this);
        bFindBusTrain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case com.rajan.dtcbusfinder.R.id.bLocation :

                Intent i = new Intent(getApplicationContext(),MyActivity.class);
                startActivity(i);

                break;

            case com.rajan.dtcbusfinder.R.id.bGoToFind:

                Intent j = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(j);

                break;


        }
    }
}
