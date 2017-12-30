package com.nyf.uneasyguys.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Service.ServiceHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.*;

/**
 * Created by sesan on 2017-12-29.
 */

public class PostActivity extends AppCompatActivity {
    private Button postSaveButton;
    private EditText postEditText;
    private TextView postAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postSaveButton = findViewById(R.id.post_save_button);
        postEditText = findViewById(R.id.post_edit_text);
        postAddressText = findViewById(R.id.post_address_text);

        postSaveButton.setOnClickListener(new OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                postAddress();
                                              }
                                          }
        );
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
        ServiceHelper.getInstance().postArticle(1000000002,postTextString).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    Log.d("response: " , response.body());
                }
                if (response.errorBody() != null) {
                    Log.d("response: " , response.errorBody().toString());
                }
                System.out.println(response.message());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        ServiceHelper.getInstance().getAllCategory().enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                System.out.println(response.body().size());
                List<ArticleModel> articleModels = response.body();
                for (ArticleModel articleModel : articleModels){
                    System.out.println(articleModel.getText());

                }
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {

            }
        });
    }
}
