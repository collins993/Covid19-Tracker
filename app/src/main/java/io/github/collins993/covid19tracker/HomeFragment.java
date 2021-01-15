package io.github.collins993.covid19tracker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private static final String STATS_URL = "https://api.covid19api.com/summary";


    Context context;
    private ProgressBar progressBar;
    private TextView totalCaseTxt, newCasesTxt, totalDeathsTxt, newDeathsTxt, totalRecoveredTxt, newRecoveredTxt;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        totalCaseTxt = view.findViewById(R.id.total_cases_txt);
        newCasesTxt = view.findViewById(R.id.new_cases_txt);
        totalDeathsTxt = view.findViewById(R.id.total_deaths_txt);
        newDeathsTxt = view.findViewById(R.id.new_deaths_txt);
        totalRecoveredTxt = view.findViewById(R.id.total_recovered_txt);
        newRecoveredTxt = view.findViewById(R.id.new_recovered_txt);

        progressBar.setVisibility(View.INVISIBLE);

        loadHomeData();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        loadHomeData();
    }

    private void loadHomeData() {
        //show progress
        progressBar.setVisibility(View.VISIBLE);

        //JSON String request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject globalJo = jsonObject.getJSONObject("Global");

            String newConfirmed = globalJo.getString("NewConfirmed");
            String totalConfirmed = globalJo.getString("TotalConfirmed");
            String newDeaths = globalJo.getString("NewDeaths");
            String totalDeaths = globalJo.getString("TotalDeaths");
            String newRecovered = globalJo.getString("NewRecovered");
            String totalRecovered = globalJo.getString("TotalRecovered");

            totalCaseTxt.setText(totalConfirmed);
            newCasesTxt.setText(newConfirmed);
            totalDeathsTxt.setText(totalDeaths);
            newDeathsTxt.setText(newDeaths);
            totalRecoveredTxt.setText(totalRecovered);
            newRecoveredTxt.setText(newRecovered);

            progressBar.setVisibility(View.GONE);

        }
        catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}