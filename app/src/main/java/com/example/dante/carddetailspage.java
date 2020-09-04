package com.example.dante;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;

public class carddetailspage extends AppCompatActivity {

    int indexnumber;
    ImageView carddetailsimage, imgholder, firstimg, secondimg;
    CardView front, back;
    TextView cardname;
    EditText cardcode;
    Button redeem;
    boolean status;
    Intent intent;
    String tag, code, firstimagepath, secondimagepath;
    String cards[];
    int imagesss[] = {R.drawable.googlecardimg, R.drawable.itunesimg, R.drawable.offgamersimg, R.drawable.amazonimg, R.drawable.vanillaimg, R.drawable.steamcard, R.drawable.btccard};
    Bitmap bitmap;
    //array holding images
    String[] attachFiles = new String[2];
    Uri targetUri,intentfstimg,intentscdimg;


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carddetailspage);



        Intent i = getIntent();
        indexnumber = i.getIntExtra("number", 0);
        Log.e(tag, String.valueOf(indexnumber));

        firstimg = findViewById(R.id.firstpicture);
        secondimg = findViewById(R.id.secondpicture);
        carddetailsimage = findViewById(R.id.imageView2);
        front = findViewById(R.id.frontimg);
        back = findViewById(R.id.backimg);
        cardname = findViewById(R.id.cardname);
        cardcode = findViewById(R.id.cardcode);
        redeem = findViewById(R.id.redeembtn);
        intent = new Intent(carddetailspage.this, orderpage.class);
        cards = getResources().getStringArray(R.array.card_names);

        verifyStoragePermissions(this);

        //assigns the image and cardname
        Log.e(tag, String.valueOf(indexnumber));
        carddetailsimage.setImageResource(imagesss[indexnumber]);
        cardname.setText(cards[indexnumber]);


        cardcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardcode.getText().toString().equals("Input Code Here")){
                    cardcode.setText("");
                }
            }
        });




        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = true;
                Log.e(tag, String.valueOf(status));
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

        secondimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = false;
                Log.e(tag, String.valueOf(status));
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
                if(!status){
                    secondimg.setImageBitmap(bitmap);
                }

            }
        });

        //redeem onclicklistener
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(v.getContext(), accountdetailspage.class);
                intent2.putExtra("number",indexnumber);
                intent2.putExtra("cardcode",cardcode.getText().toString().trim());
                intent2.putExtra("firstcardimage", intentfstimg);
                intent2.putExtra("secondcardimage",intentscdimg);
                startActivity(intent2);
              //  code = cardcode.getText().toString().trim();
                //sendMail();
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void sendMail() {
        String mail = "ezeokoliwisdom@gmail.com";
        String message = code ;
        String subject = Utils.useremail + " " + "card code: " + code;
        attachFiles[0] = firstimagepath;
        attachFiles[1] = secondimagepath;


        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message, attachFiles);
        javaMailAPI.execute();
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            targetUri = data.getData();
            File finalFile = new File(getRealPathFromURI(targetUri));
            // Log.e(tag, finalFile.toString());

            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                if(status){
                    firstimg.setImageBitmap(bitmap);
                    firstimagepath = finalFile.toString();
                    intentfstimg = targetUri;
                    bitmap = null;
                    status = Boolean.parseBoolean(null);

                }else if(!status){
                    secondimg.setImageBitmap(bitmap);
                    secondimagepath = finalFile.toString();
                    intentscdimg = targetUri;
                    bitmap = null;
                    status = Boolean.parseBoolean(null);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

}