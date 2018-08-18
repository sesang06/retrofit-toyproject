package com.sesang06.retrofit.test;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sesang06.retrofit.test.Model.ArticleModel;
import com.sesang06.retrofit.test.Service.ImageFilePath;
import com.sesang06.retrofit.test.Service.ServiceHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sesan on 2017-12-29.
 */

public class PostActivity extends AppCompatActivity {

    private EditText postEditText;
    private TextView postAddressText;
    private long point_x;
    private long point_y;
    private ProgressDialog progressDialog = null;
    private ImageView postImageView;
    private Uri uri;
    final int REQ_PICK_CODE = 100;
    final int PICK_FROM_GALLERY = 10000;
    final int PICK_FROM_CAMERA = 10001;
    final int REQ_CAMERA_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("포스트");
        postEditText = findViewById(R.id.post_edit_text);
        postAddressText = findViewById(R.id.post_address_text);
        postImageView = findViewById(R.id.post_image);
        Intent intent = new Intent(this.getIntent());

        point_x=   intent.getLongExtra("point_x", 0);
        point_y=   intent.getLongExtra("point_y", 0);
        postAddressText.setText("point_x : "+point_x+" point_y: "+point_y);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent pickerIntent = new Intent(Intent.ACTION_PICK);
                    pickerIntent.setType("image/*");
                    startActivityForResult(pickerIntent, REQ_PICK_CODE);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
            case PICK_FROM_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQ_CAMERA_CODE);
                    }
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }
    public void pickPicture(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);

        }else {
            Intent pickerIntent = new Intent(Intent.ACTION_PICK);
            pickerIntent.setType("image/*");
            startActivityForResult(pickerIntent, REQ_PICK_CODE);
        }
    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public void takePicture(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_CAMERA);

        }else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            getPackageName(),
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQ_CAMERA_CODE);
                }
            }

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == REQ_PICK_CODE){
                Uri uri = data.getData();
                Glide.with(this)
                        .load(uri)
                        .into(postImageView);
                this.uri = uri;
                // ImageView에 선택된 image를 설정
                // mImageView.setImageURI(uri);
            }else if (requestCode == REQ_CAMERA_CODE){
                Uri uri = Uri.fromFile(new File(mCurrentPhotoPath));
               Glide.with(this)
                       .load(mCurrentPhotoPath)
                       .into(postImageView);

               this.uri = uri;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home :
                finish();
                return true;
            case R.id.menu_post:
                postAddress();
                return true;
            case R.id.menu_photo:
                pickPicture();
                return true;
            case R.id.menu_camera:
                takePicture();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void finishActivity(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    private void postAddress(){
        String postTextString = postEditText.getText().toString();
        if (postTextString.equals("")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("알림")
                    .setMessage("포스트할 내용을 채워 주세요")
                    .setCancelable(true)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return;
        }
        progressDialog = ProgressDialog.show(this,"포스트 중...", "", true);
        MultipartBody.Part body = null;
        if (uri != null) {
            File file = new File(ImageFilePath.getPath(this, uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }
        ServiceHelper.getInstance().postArticle(point_x , point_y,postTextString, body).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {

               System.out.println("response success");
                if (progressDialog != null){
                    progressDialog.dismiss();
                }

                finishActivity();
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (progressDialog != null){
                    progressDialog.dismiss();
                }
                    Log.d("response fail: ", "");

            }
        });



    }
}
