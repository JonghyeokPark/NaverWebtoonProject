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

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_fragment_finish, container, false);

        HashMap<String,String> webtoon;
        fdata = new ArrayList<>();

//        new LoadAllWebtoons().execute();
//
//        for(int i = 0;i < webtoonList.size(); i++){
//            webtoon = webtoonList.get(i);
//
//            if(webtoon.get("isend").equals("1")) {
//                if (genre.equals(webtoon.get("genre"))) {
//                    fdata.add(new FinishData(webtoon.get("name"),webtoon.get("author"),webtoon.get("star")));
//                }
//            }
//        }

        fdata.add(new FinishData(genre, genre, genre));
        fdata.add(new FinishData(genre, genre, genre));
        fdata.add(new FinishData(genre, genre, genre));
        fdata.add(new FinishData(genre, genre, genre));

        Log.i("FinishPage " + genre, "FinishAdapter started!");

        list_webtoon = (GridView) view.findViewById(R.id.gridView);
        gadapter = new GenreAdapter(getActivity(),fdata);
        list_webtoon.setAdapter(gadapter);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                FinishData fd = fdata.get(pos);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, new ListFragment(fd.getName()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

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
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            Log.d("DEBUG[MAIN]","IS RIGHT?");

            // Check your log cat for JSON reponse
//            Log.d("All Webtoons: ", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if(success == 1){
                    webtoons = json.getJSONArray(TAG_WEBTOON);

                    for(int i =0; i < webtoons.length(); i++){
                        JSONObject c = webtoons.getJSONObject(i);

                        String name = c.getString("name");
                        String author = c.getString("author");
                        String star = c.getString("star");

                        HashMap<String, String> map = new HashMap<>();
                        map.put("name",name);
                        map.put("author",author);
                        map.put("star",star);

                        Log.i("[MAIN RESULT]", name + "//" + author);

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