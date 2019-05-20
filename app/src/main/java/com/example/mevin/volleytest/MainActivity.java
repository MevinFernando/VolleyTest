package com.example.mevin.volleytest;

import android.app.ProgressDialog;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView retailerName;
    private TextView returnDate;

    private RecyclerView mList;
    private RecyclerView sList;


    private LinearLayoutManager linearLayoutManager1;
    private LinearLayoutManager linearLayoutManager2;

    private DividerItemDecoration dividerItemDecoration1;
    private DividerItemDecoration dividerItemDecoration2;

    private List<Item> itemList;
    private List<Status> statusList;
    private RecyclerView.Adapter itemAdapter;
    private RecyclerView.Adapter statusAdapter;


    private String url = "http://hulrevlog.herokuapp.com/api/returns";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retailerName =(TextView)findViewById(R.id.retailer_name);
        returnDate =(TextView)findViewById(R.id.return_date);

        mList = findViewById(R.id.main_list);
        sList = findViewById(R.id.status_list);

        itemList = new ArrayList<>();
        statusList = new ArrayList<>();


        itemAdapter = new ItemAdapter(getApplicationContext(),itemList);
        statusAdapter = new StatusAdapter(getApplicationContext(),statusList);

        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager2 = new LinearLayoutManager(this);

        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        dividerItemDecoration1 = new DividerItemDecoration(mList.getContext(), linearLayoutManager1.getOrientation());
        dividerItemDecoration2 = new DividerItemDecoration(sList.getContext(), linearLayoutManager2.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager1);
        mList.addItemDecoration(dividerItemDecoration1);
        mList.setAdapter(itemAdapter);


        sList.setHasFixedSize(true);
        sList.setLayoutManager(linearLayoutManager2);
        sList.addItemDecoration(dividerItemDecoration2);
        sList.setAdapter(statusAdapter);

        getData();


    }


    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(0);

                        retailerName.setText(jsonObject.getString("retailerName"));
                        returnDate.setText(jsonObject.getString("returnDate"));

                        JSONArray itemsArray = jsonObject.getJSONArray("items");
                        for (int j = 0; j < itemsArray.length(); j++) {
                            //gets each JSON object within the JSON array
                            JSONObject itemsObject = itemsArray.getJSONObject(j);


                            Item item = new Item();
                            item.setName(itemsObject.getString("name"));
                            item.setPkd(itemsObject.getString("pkd"));
                            item.setMrp(itemsObject.getString("mrp"));
                            item.setQty(itemsObject.getString("qty"));
                            item.setReason(itemsObject.getString("reason"));
                            itemList.add(item);
                        }



                        JSONArray statusArray = jsonObject.getJSONArray("status");
                        for (int j = 0; j < statusArray.length(); j++) {
                            //gets each JSON object within the JSON array
                            JSONObject statusObject = statusArray.getJSONObject(j);


                            Item item = new Item();
                            item.setName(statusObject.getString("description"));
                            item.setPkd(statusObject.getString("time"));
                            itemList.add(item);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                itemAdapter.notifyDataSetChanged();
                statusAdapter.notifyDataSetChanged();

                progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}