package com.skku.ntoon.ntoon.ListPage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.skku.ntoon.ntoon.MainActivity;
import com.skku.ntoon.ntoon.R;
import com.skku.ntoon.ntoon.Util.JSONParser;
import com.skku.ntoon.ntoon.Util.WebtoonList;
import com.skku.ntoon.ntoon.ViewActivity;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luusoo on 16. 1. 5..
 */

public class ListFragment extends Fragment {
    // Store instance variables

    private ListAdapter ladapter;
    private ListView list_webtoon;
    private ArrayList<ListData> ldata = new ArrayList<>();
    private String title;
    private int page;
    String webtoon_name;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_WEBTOON = "webtoon";

    JSONParser jParser = new JSONParser();
    JSONArray webtoons = null;

    private static String url_all_products = "http://52.88.69.12/mysql_connect/show_webtoon.php";

    public ListFragment(String webtoon_name){
        this.webtoon_name = webtoon_name;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //page = getArguments().getInt("someInt", 0);
        //title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        new LoadAllWebtoons().execute();

        ArrayList<HashMap<String,String>> wList = new WebtoonList().getwList();
        ArrayList<HashMap<String,String>> webtoonList = new WebtoonList().getWebtoonList();
        HashMap<String,String> webtoon;
        String wid = new String();

        for(int i = 0; i < webtoonList.size(); i++) {
            webtoon = webtoonList.get(i);

            if(webtoon_name.equals(webtoon.get("name"))) {
                wid = webtoon.get("wid");
                break;
            }
        }

        for(int i = 0;i < wList.size(); i++) {
            webtoon = wList.get(i);

            if(webtoon.get("wid") == wid) {
                ldata.add(new ListData(webtoon.get("title"), webtoon.get("update"), webtoon.get("number"),
                        webtoon.get("star"), webtoon.get("hits"), webtoon.get("thumbnail"), webtoon.get("image"), webtoon.get("imagenum")));
            }
        }

        for(int i = 0;i < ldata.size(); i++){
            for(int j = 0;j < ldata.size()-1-i; j++){
                if(Integer.parseInt(ldata.get(j).getNumber()) > Integer.parseInt(ldata.get(j+1).getNumber())){
                    ListData temp = ldata.get(j);
                    ldata.set(j,ldata.get(+1));
                    ldata.set(j+1,temp);
                }
            }
        }

        list_webtoon = (ListView) view.findViewById(R.id.listView);
        ladapter = new ListAdapter(getActivity(),ldata);
        list_webtoon.setAdapter(ladapter);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent = new Intent(getContext(), ViewActivity.class);
                intent.putExtra("iamge",ldata.get(pos).getImage());
                intent.putExtra("iamgenum",ldata.get(pos).getImagenum());
                startActivity(intent);
            }
        });

        list_webtoon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long iD) {
                return true;
            }
        });

        return view;
    }

    class LoadAllWebtoons extends AsyncTask<String, String, String > {

        ArrayList<HashMap<String,String>> webtoonList = new ArrayList<>();

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            Log.d("TAG : ", "LoadAllWebtoons 1");

            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
            Log.d("TAG : ","LoadAllWebtoons 2");

            Log.d("TAG : ", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                Log.d("TAG",String.valueOf(success));
                if(success == 1){
                    webtoons = json.getJSONArray(TAG_WEBTOON);

                    if(new WebtoonList().isEmpty(1)) {
                        for (int i = 0; i < webtoons.length(); i++) {
                            JSONObject c = webtoons.getJSONObject(i);
                        /* $product["wid"] = $row["wid"];
        $product["name"] = $row["name"];
        $product["star"] = $row["star"];
        $product["author"] = $row["author"];
        $product["genre"] = $row["genre"];
        $product["intro"] = $row["intro"];
        $product["day"] = $row["day"];
        $product["thumbnail"] = $row["thumbnail"];
        $product["likes"] = $row["likes"];
        $product["isend"] = $row["isend"];
        $product["isstop"] = $row["isstop"];
        $product["startdate"] = $row["startdate"];*/

                            String cid = c.getString("cid");
                            String wid = c.getString("wid");
                            String title = c.getString("title");
                            String update = c.getString("update");
                            String number = c.getString("number");
                            String star = c.getString("star");
                            String hits = c.getString("hits");
                            String thumbnail = c.getString("thumbnail");
                            String image = c.getString("image");
                            String imagenum = c.getString("imagenum");


                            HashMap<String, String> map = new HashMap<>();
                            map.put("cid", cid);
                            map.put("wid", wid);
                            map.put("title", title);
                            map.put("update", update);
                            map.put("number", number);
                            map.put("star", star);
                            map.put("hits", hits);
                            map.put("thumbnail", thumbnail);
                            map.put("image", image);
                            map.put("imagenum",imagenum);

                            webtoonList.add(map);
                        }

                        new WebtoonList().setwList(webtoonList);
                    }
                }
                else {
                    Log.i("[MAIN RESULT]", "WEBTOON NOT FOUND!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}