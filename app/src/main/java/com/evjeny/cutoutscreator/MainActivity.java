package com.evjeny.cutoutscreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText bgColor, sqSize, sqColor,
            points, files, saveto, quality;
    ImageCreator ic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bgColor = (EditText) findViewById(R.id.bg_color);
        sqSize = (EditText) findViewById(R.id.sq_size);
        sqColor = (EditText) findViewById(R.id.sq_color);
        points = (EditText) findViewById(R.id.points);
        files = (EditText) findViewById(R.id.files);
        saveto = (EditText) findViewById(R.id.saveto);
        quality = (EditText) findViewById(R.id.quality);
    }
    public void doit(View v) {
        if(checkState()) {
            File f = new File(saveto.getText().toString());
            f.mkdir();
            ic = new ImageCreator(this, Integer.valueOf(t(sqSize)), t(sqColor), t(bgColor),
                    Integer.valueOf(t(points)), Integer.valueOf(t(quality)), f.getPath());

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < Integer.valueOf(t(files)); i++) {
                        ic.generateAndSaveImage(String.valueOf(i)+".jpg");
                    }
                }
            };
            Thread thread = new Thread(r);
            thread.start();

        }
    }

    public void prev(View v) {
        if(checkState()) {
            String[] extra = new String[] {t(sqSize), t(bgColor), t(sqColor), t(points), t(quality)};
            Intent prev = new Intent(this, PreviewActivity.class);
            prev.putExtra("values", extra);
            startActivity(prev);
        }
    }

    private boolean checkState() {
        if(c(bgColor)&&c(sqColor)&&c(sqSize)&&
                c(points)&&c(files)&&c(saveto)&&c(quality)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean c(EditText todo) {
        if(todo.getText().toString().equals("")||todo.getText().toString().equals(" ")) {
            return false;
        } else {
            return true;
        }
    }
    private String t(EditText todo) {
        return todo.getText().toString();
    }
}
