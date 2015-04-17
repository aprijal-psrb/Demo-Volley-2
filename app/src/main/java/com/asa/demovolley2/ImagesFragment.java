package com.asa.demovolley2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 16/04/2015.
 */
public class ImagesFragment extends Fragment {
    private ImageRecordsAdapter mAdapter;

    public ImagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle b){
        super.onActivityCreated(b);
        mAdapter = new ImageRecordsAdapter(getActivity());
        ListView list1 = (ListView)getView().findViewById(R.id.list1);
        list1.setAdapter(mAdapter);
        fetch();
    }

    private void fetch() {
        JsonObjectRequest request = new JsonObjectRequest(
                "http://cblunt.github.io/blog-android-volley/response2.json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // TODO: Parse the JSON
                        try {
                            List<ImageRecord> imageRecords = parse(jsonObject);
                            mAdapter.swapImageRecords(imageRecords);
                        }catch (JSONException e){
                            Toast.makeText(getActivity(), "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private List<ImageRecord> parse(JSONObject json) throws JSONException{
        ArrayList<ImageRecord> records = new ArrayList<>();
        JSONArray jsonImages = json.getJSONArray("images");

        for(int i = 0; i < jsonImages.length(); i++){
            JSONObject jsonImage = jsonImages.getJSONObject(i);
            String title = jsonImage.getString("title");
            String url = jsonImage.getString("url");
            ImageRecord imageRecord = new ImageRecord(url, title);
            records.add(imageRecord);
        }
        return records;
    }
}