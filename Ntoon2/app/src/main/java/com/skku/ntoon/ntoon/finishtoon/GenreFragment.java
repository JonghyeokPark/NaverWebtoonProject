package com.skku.ntoon.ntoon.finishtoon;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.skku.ntoon.ntoon.ListPage.ListAdapter;
import com.skku.ntoon.ntoon.ListPage.ListFragment;
import com.skku.ntoon.ntoon.R;
import com.skku.ntoon.ntoon.Util.JSONParser;

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
public class GenreFragment extends Fragment {
    // Store instance variables
    private ArrayList<FinishData> fdata;
    private GenreAdapter gadapter;
    private GridView list_webtoon;
    public int page;
    private String genre;

    ArrayList<HashMap<String,String>> webtoonList = new ArrayList<>();;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_WEBTOON = "webtoon";
    private static final String TAG_NAME = "name";

    JSONParser jParser = new JSONParser();
    JSONArray webtoons = null;

    private static String url_all_products = "http://52.88.69.12/mysql_connect/show_webtoon.php";

    // newInstance constructor for creating fragment with arguments
    public GenreFragment(int page) {
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        this.page = page;
        switch (page){
            case(0):
                genre = "에피소드";
                break;
            case(1):
                genre = "옴니버스";
                break;
            case(2):
                genre = "스토리";
                break;
            case(3):
                genre = "일상";
                break;
            case(4):
                genre = "개그";
                break;
            case(5):
                genre = "판타지";
                break;
            case(6):
                genre = "액션";
                break;
            case(7):
                genre = "드라마";
                break;
            case(8):
                genre = "순정";
                break;
            case(9):
                genre = "감성";
                break;
            case(10):
                genre = "스릴러";
                break;
            case(11):
                genre = "시대극";
                break;
            case(12):
                genre = "스포츠";
                break;
        }
        this.setArguments(args);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //new LoadAllWebtoons().execute();

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_fragment_finish, container, false);

        HashMap<String,String> webtoon;
        fdata = new ArrayList<>();

        new LoadAllWebtoons().execute();
//
        for(int i = 0;i < webtoonList.size(); i++){
            webtoon = webtoonList.get(i);

            Log.d("WEBTOON","WEBTOON");
            Log.d("WEBTOON name", webtoon.get("name"));
            Log.d("WEBTOON isend",webtoon.get("isend"));
            Log.d("WEBTOON genre",webtoon.get("genre"));


            if(webtoon.get("isend").equals("1")) {
                Log.d("==============", "1");
                if (genre.equals(webtoon.get("genre"))) {
                    Log.d("==============", "inside");

                    Log.d("WEBTOON name", webtoon.get("name"));
                    Log.d("WEBTOON author", webtoon.get("author"));
                    Log.d("WEBTOON star", webtoon.get("star"));

                    fdata.add(new FinishData(webtoon.get("name"),webtoon.get("author"),webtoon.get("star"),webtoon.get("thumbnail")));
                }
            }
        }

        //fdata.add(new FinishData(genre, genre, genre,null));
        //fdata.add(new FinishData(genre, genre, genre,null));
        //fdata.add(new FinishData(genre, genre, genre,null));
        //fdata.add(new FinishData(genre, genre, genre,null));

        Log.i("FinishPage " + genre, "FinishAdapter started!");

        list_webtoon = (GridView) view.findViewById(R.id.gridView);
        gadapter = new GenreAdapter(getActivity(),fdata);
        list_webtoon.setAdapter(gadapter);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                FinishData fd = fdata.get(pos);
                FragmentManager fm = getParentFragment().getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, new ListFragment(fd.getName()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // ��� ?????????
        list_webtoon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long iD) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog);

                TextView text = (TextView) dialog.findViewById(R.id.textView);
                text.setText("sample");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
                image.setImageResource(R.drawable.sample);
                dialog.show();
                return true;
            }
        });

        return view;
    }

    class LoadAllWebtoons extends AsyncTask<String, String, String > {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            Log.d("TAG : ","LoadAllWebtoons 1");

            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
            Log.d("TAG : ","LoadAllWebtoons 2");

            Log.d("TAG : ", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                Log.d("TAG",String.valueOf(success));
                if(success == 1){
                    webtoons = json.getJSONArray(TAG_WEBTOON);

                    for(int i =0; i < webtoons.length(); i++){
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

                        String wid = c.getString("wid");
                        String name = c.getString("name");
                        String star = c.getString("star");
                        String author = c.getString("author");
                        String genre = c.getString("genre");
                        String intro = c.getString("intro");
                        String day = c.getString("day");
                        String thumbnail = c.getString("thumbnail");
                        String likes = c.getString("likes");
                        String isend = c.getString("isend");
                        String isstop = c.getString("isstop");
                        String startdate = c.getString("startdate");

                        //Log.d("TAG", name);
                        //Log.d("TAG", author);
                        //Log.d("TAG", star);


                        HashMap<String, String> map = new HashMap<>();
                        map.put("wid",wid);
                        map.put("name",name);
                        map.put("star",star);
                        map.put("author",author);
                        map.put("genre",genre);
                        map.put("intro",intro);
                        map.put("day",day);
                        map.put("thumbnail",thumbnail);
                        map.put("likes",likes);
                        map.put("isend",isend);
                        map.put("isstop",isstop);
                        map.put("startdate",startdate);

                        //Log.i("[MAIN RESULT]", name + "//" + author);

                        webtoonList.add(map);
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