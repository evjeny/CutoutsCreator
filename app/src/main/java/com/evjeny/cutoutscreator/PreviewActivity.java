package com.evjeny.cutoutscreator;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Evjeny on 24.03.2017.
 */

public class PreviewActivity extends AppCompatActivity {
    ImageView v1, v2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
        v1 = (ImageView) findViewById(R.id.prev_f);
        v2 = (ImageView) findViewById(R.id.prev_s);
        String[] extra = getIntent().getStringArrayExtra("values");
        ImageCreator ic = new ImageCreator(this, Integer.valueOf(extra[0]), extra[2], extra[1],
                Integer.valueOf(extra[3]), Integer.valueOf(extra[4]), "");
        Bitmap[] src = ic.getPreviews();
        v1.setImageBitmap(src[0]);
        v2.setImageBitmap(src[1]);
    }
}
