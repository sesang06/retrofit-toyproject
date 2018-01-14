package com.nyf.uneasyguys.test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Service.ServiceHelper;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("포스트");
        postEditText = findViewById(R.id.post_edit_text);
        postAddressText = findViewById(R.id.post_address_text);
        Intent intent = new Intent(this.getIntent());

        point_x=   intent.getLongExtra("point_x", 0);
        point_y=   intent.getLongExtra("point_y", 0);
        postAddressText.setText("point_x : "+point_x+" point_y: "+point_y);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

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

        ServiceHelper.getInstance().postArticle(point_x * 1000000000 + point_y,postTextString).enqueue(new Callback<ArticleModel>() {
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
