package com.evjeny.cutoutscreator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Evjeny on 29.10.2016.
 * at 19:32
 */
public class ImageCreator {
    private Context context;
    private String path, color, color_bg;
    private int points, size, quality;
    private Random r = new Random();
    public ImageCreator(Context context, int size, String color,
                        String color_bg, int points,int quality, String pathto) {
        this.context = context;
        this.path = pathto;
        this.points = points;
        this.size = size;
        this.color = color;
        this.color_bg = color_bg;
        this.quality = quality;
        File f1 = new File(pathto+"/ans");
        f1.mkdir();
        File f2 = new File(pathto+"/exs");
        f2.mkdir();
    }
    public void generateAndSaveImage(String filename) {
        Bitmap base = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(base);
        Paint p = new Paint();
        c.drawColor(Color.parseColor(color));
        Path path = new Path();
        path.moveTo(0, size/2);
        float one_part = size / points;
        float current_part = one_part;
        for(int i = 0; i < points-1; i++) {
            path.lineTo(current_part, r.nextInt(size));
            current_part += one_part;
        }
        p.setColor(Color.parseColor(color));
        p.setStyle(Paint.Style.FILL);
        path.lineTo(size, size/2);
        Path q = new Path(path);
        q.lineTo(size, 0);
        q.lineTo(0,0);
        q.close();
        Bitmap quest = Bitmap.createBitmap(base);
        Canvas question = new Canvas(quest);
        question.drawColor(Color.parseColor(color_bg));
        question.clipPath(q);
        question.drawRect(0, 0, size, size, p);
        Path a = new Path(path);
        a.lineTo(size, size);
        a.lineTo(0,size);
        a.close();
        Bitmap ans = Bitmap.createBitmap(base);
        Canvas answer = new Canvas(ans);
        answer.drawColor(Color.parseColor(color_bg));
        answer.clipPath(a);
        answer.drawRect(0,0,size,size,p);
        try {
            FileOutputStream fos = new FileOutputStream(new File(this.path+File.separator+"exs/"+filename));
            quest.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.close();
            fos = null;
            fos = new FileOutputStream(new File(this.path+File.separator+"ans/"+filename));
            ans.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bitmap[] getPreviews() {
        Bitmap[] result = new Bitmap[2];
        Bitmap base = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(base);
        Paint p = new Paint();
        c.drawColor(Color.parseColor(color));
        Path path = new Path();
        path.moveTo(0, size/2);
        float one_part = size / points;
        float current_part = one_part;
        for(int i = 0; i < points-1; i++) {
            path.lineTo(current_part, r.nextInt(size));
            current_part += one_part;
        }
        p.setColor(Color.parseColor(color));
        p.setStyle(Paint.Style.FILL);
        path.lineTo(size, size/2);
        Path q = new Path(path);
        q.lineTo(size, 0);
        q.lineTo(0,0);
        q.close();
        Bitmap quest = Bitmap.createBitmap(base);
        Canvas question = new Canvas(quest);
        question.drawColor(Color.parseColor(color_bg));
        question.clipPath(q);
        question.drawRect(0, 0, size, size, p);
        Path a = new Path(path);
        a.lineTo(size, size);
        a.lineTo(0,size);
        a.close();
        Bitmap ans = Bitmap.createBitmap(base);
        Canvas answer = new Canvas(ans);
        answer.drawColor(Color.parseColor(color_bg));
        answer.clipPath(a);
        answer.drawRect(0,0,size,size,p);
        result[0] = quest;
        result[1] = ans;
        return result;
    }
}
