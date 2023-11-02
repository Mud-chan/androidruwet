package com.pramudya.apidatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import model.User;
import retrofit.MethodHTTP;
import retrofit.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class retrofitActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    List<User> listUser;
    private ListView lvUserVollley;

    private void getUserfromAPIRetrofit(){
        ProgressDialog proDialog = new ProgressDialog(this);
        proDialog.setTitle("Retrofit");
        proDialog.setMessage("Silahkan Tunggu");
        proDialog.show();

//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://localhost/")
//                .addConverterFactory()x
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/").build();

        MethodHTTP client = retrofit.create(MethodHTTP.class);


        Call<UserResponse> call = client.getAllUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                proDialog.dismiss();
                if (response.body().getUser_list() != null) {
                    listUser = response.body().getUser_list();
                    textView.setText(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    void getUserFromAPIVolley() {
        ProgressDialog proDialog = new ProgressDialog(this);
        proDialog.setTitle("Retrofit");
        proDialog.setMessage("Silahkan Tunggu");
        proDialog.show();

        String url = "http://10.10.6.56/volley/User_Registration.php";
        Gson gson = new Gson();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                proDialog.dismiss();
                if (response != null){
                    String daftarUser;
                    UserResponse userResponse = gson.fromJson(response.toString(), UserResponse.class);
                    if (userResponse.getCode() == 200) {
                        UserAdapter adapter = new UserAdapter(getApplicationContext(), userResponse.getUser_list());
                        lvUserVollley.setAdapter(adapter);

                        lvUserVollley.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                                Toast.makeText(getApplicationContext(), userResponse.getUser_list().get(i).getUser_fullname(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                proDialog.dismiss();
                Log.e("Koneksi", "Errornya "+ error.getMessage());
                Toast.makeText(getApplicationContext(), "Errornya : "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        textView = findViewById(R.id.txtview);
        lvUserVollley = findViewById(R.id.list);

        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserFromAPIVolley();
            }
        });


    }
}