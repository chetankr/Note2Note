package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RetrieveImages extends AppCompatActivity {
    private byte[] bitmapdata;
    private List<Bitmap> photos = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getimages);

        getPhotos();

//        Intent intent = getIntent();
//        byte[] bitmapdata = intent.getByteArrayExtra("bytes");
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.outWidth = 750;
//        options.outHeight = 1000;
//        List<Bitmap> photos = new ArrayList<>();
//        photos.add(BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length, options));
///

    }
    public void getPhotos() {
        ParseQuery query = new ParseQuery<ParseObject>("NoteDB");
        query.orderByDescending("createdAt");
        query.setLimit(8);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        Log.d("Update: ", "Object: " + parseObject.getObjectId().toString());
                        ParseFile pf = (ParseFile) parseObject.get("File");
                        String s = (String) parseObject.get("Tag");
                        try {
                            bitmapdata = pf.getData();
                            photos.add(BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length));
                            tags.add(s);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }

                    ImageButton img1 = (ImageButton) findViewById(R.id.image1);
                    ImageButton img2 = (ImageButton) findViewById(R.id.image2);
                    ImageButton img3 = (ImageButton) findViewById(R.id.image3);
                    ImageButton img4 = (ImageButton) findViewById(R.id.image4);
                    ImageButton img5 = (ImageButton) findViewById(R.id.image5);
                    ImageButton img6 = (ImageButton) findViewById(R.id.image6);
                    ImageButton img7 = (ImageButton) findViewById(R.id.image7);
                    ImageButton img8 = (ImageButton) findViewById(R.id.image8);

                    int width = img1.getWidth();
                    int height = img1.getHeight();


                    Bitmap b1 = photos.get(0);
                    Bitmap b2 = photos.get(1);
                    Bitmap b3 = photos.get(2);
                    Bitmap b4 = photos.get(3);
                    Bitmap b5 = photos.get(4);
                    Bitmap b6 = photos.get(5);
                    Bitmap b7 = photos.get(6);
                    Bitmap b8 = photos.get(7);

/*
                    b1 = getResizedBitmap(b1,  width,  height);
                    b2 = getResizedBitmap(b2,  width,  height);
                    b3 = getResizedBitmap(b3,  width,  height);
                    b4 = getResizedBitmap(b4,  400,  530);
                    b5 = getResizedBitmap(b5,  400,  530);
                    b6 = getResizedBitmap(b6,  400,  530);
                    b7 = getResizedBitmap(b7,  400,  530);
                    b8 = getResizedBitmap(b8,  400,  530);

*/


                    img1.setImageBitmap(b1);
                    img2.setImageBitmap(b2);
                    img3.setImageBitmap(b3);
                    img4.setImageBitmap(b4);
                    img5.setImageBitmap(b5);
                    img6.setImageBitmap(b6);
                    img7.setImageBitmap(b7);
                    img8.setImageBitmap(b8);

                    img1.setRotation(90);
                    img2.setRotation(90);
                    img3.setRotation(90);
                    img4.setRotation(90);
                    img5.setRotation(90);
                    img6.setRotation(90);
                    img7.setRotation(90);
                    img8.setRotation(90);

                    TextView tv1 = (TextView) findViewById(R.id.tv1);
                    TextView tv2 = (TextView) findViewById(R.id.tv2);
                    TextView tv3 = (TextView) findViewById(R.id.tv3);
                    TextView tv4 = (TextView) findViewById(R.id.tv4);
                    TextView tv5 = (TextView) findViewById(R.id.tv5);
                    TextView tv6 = (TextView) findViewById(R.id.tv6);
                    TextView tv7 = (TextView) findViewById(R.id.tv7);
                    TextView tv8 = (TextView) findViewById(R.id.tv8);

                    tv1.setText(tags.get(0));
                    tv2.setText(tags.get(1));
                    tv3.setText(tags.get(2));
                    tv4.setText(tags.get(3));
                    tv5.setText(tags.get(4));
                    tv6.setText(tags.get(5));
                    tv7.setText(tags.get(6));
                    tv8.setText(tags.get(7));









                } else {
                    Toast.makeText(RetrieveImages.this, "Error: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }



    public void click1(View view) {
        Bitmap b = photos.get(0);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click2(View view) {
        Bitmap b = photos.get(1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click3(View view) {
        Bitmap b = photos.get(2);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click4(View view) {
        Bitmap b = photos.get(3);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click5(View view) {
        Bitmap b = photos.get(4);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click6(View view) {
        Bitmap b = photos.get(5);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click7(View view) {
        Bitmap b = photos.get(6);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }
    public void click8(View view) {
        Bitmap b = photos.get(7);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(this, ExpandImageActivity.class);
        intent.putExtra("pic", byteArray);
        startActivity(intent);
    }


}
