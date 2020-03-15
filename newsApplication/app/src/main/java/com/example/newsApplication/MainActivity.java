package com.example.frmnewsapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String CHANNEL_ID = "myChannel";
    String CHANNEL_NAME = "Remote Notification Channel";
    private ArrayAdapter<String> dataAdapterForTypes;


    void CreateNotificationChannel()
    {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        CreateNotificationChannel();

        String token = FirebaseInstanceId.getInstance().getToken(); //İlk çalışmada boş dönecektir.

        FirebaseMessaging.getInstance().subscribeToTopic("HBOApp");
        String jsonStr= GetNewsTypes();
        List<String> newsTypeList=new ArrayList<String>();

        try {
            JSONArray jsonArray=new JSONArray(jsonStr);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                String name = jsonobject.getString("NewsTypeName");
                newsTypeList.add(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Bundle intent_extras = getIntent().getExtras();

        if (intent_extras != null)
        {
            Set<String> sets= intent_extras.keySet();
            String body= intent_extras.getString("body");


            News news=new News();
            String newsJsonStr=GetNewsJsonStr(Integer.parseInt(body));

            JSONObject jsonobject = null;
            try {
                jsonobject = new JSONObject(newsJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                int newsId= jsonobject.getInt("NewsId");
                String newsBody=jsonobject.getString("NewsContent");
                String newsHeader=jsonobject.getString("NewsTitle");
                String newsImageUrl=jsonobject.getString("NewsImageUrl");

                news.setNewsId(newsId);
                news.setNewsBody(newsBody);
                news.setNewsHeader(newsHeader);
                news.setNewsUrl(newsImageUrl);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent intent =new Intent(MainActivity.this,NewsDetailActivity.class);
            intent.putExtra("ImageUrl",news.getNewsUrl());
            intent.putExtra("Header",news.getNewsHeader());
            intent.putExtra("Body",news.getNewsBody());
            intent.putExtra("Id",news.getNewsId());

            startActivity(intent);
            //Do the codes
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnGetNewsByType=(Button) findViewById(R.id.buttonGetNewsByCategory);
        final Spinner spnTypes=(Spinner) findViewById(R.id.spinnerType);
        dataAdapterForTypes=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,newsTypeList);
        spnTypes.setAdapter(dataAdapterForTypes);

        btnGetNewsByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedType= spnTypes.getSelectedItem().toString();
                Intent intent =new Intent(MainActivity.this,ListViewActivity.class);
                intent.putExtra("NewsType",selectedType);
                startActivity(intent);
            }
        });





        /*List<News> newsList=GetNewsList();

        final ListView listView=(ListView)findViewById(R.id.listView);
        final CustomAdapter adapter=new CustomAdapter(newsList,this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news=(News) adapter.getItem(position);
               // Toast.makeText(getApplicationContext(),news.getNewsBody(),Toast.LENGTH_LONG).show();

                Intent intent =new Intent(MainActivity.this,NewsDetailActivity.class);
                intent.putExtra("ImageUrl",news.getNewsUrl());
                intent.putExtra("Header",news.getNewsHeader());
                intent.putExtra("Body",news.getNewsBody());
                intent.putExtra("Id",news.getNewsId());

                startActivity(intent);
            }
        });*/


       /* final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               URL url;
                HttpURLConnection urlConnection = null;
                TextView tv=(TextView)findViewById(R.id.txtD);
                StringBuffer stringBuffer=new StringBuffer();
                try {
                    url = new URL("http://192.168.1.103:34/login/deneme");
                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    BufferedReader bufferedReader=new BufferedReader(isw);
                    String line=bufferedReader.readLine();

                    while(line !=null){

                        stringBuffer.append(line);
                        line=bufferedReader.readLine();
                    }
                    
                    String jSonString=stringBuffer.toString();
                    JSONObject obj=new JSONObject(jSonString);

                    SdUser user=new SdUser();
                    user.setPassword(obj.getString("Password"));

                    Toast.makeText(getApplicationContext(),user.getPassword(),Toast.LENGTH_LONG).show();

                    tv.setText(stringBuffer.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }



            }
        });*/

    }


    private List<String> GetNewsTypeList()
    {
        List<String> newsTypeList=new ArrayList<String>();
        newsTypeList.add("Tip1");
        newsTypeList.add("Tip2");
        newsTypeList.add("Tip3");

        /*List<NewsType> newsTypeList=new ArrayList<NewsType>();

        NewsType newsType=new NewsType();
        newsType.setNewsTypeDescription("Açıklama1");
        newsType.setNewsTypeId(1);
        newsType.setNewsTypeName("İsim1");

        NewsType newsType2=new NewsType();
        newsType2.setNewsTypeDescription("Açıklama2");
        newsType2.setNewsTypeId(2);
        newsType2.setNewsTypeName("İsim2");

        newsTypeList.add(newsType);
        newsTypeList.add(newsType2);*/

        return newsTypeList;
    }

    private void PostExample(String[] params)
    {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        OutputStream out = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<News> GetNewsList()
    {
        List<News> list=new ArrayList<News>();

        News news1=new News();
        news1.setNewsBody("İstanbulu Sel Aldı a Yavrııım");
        news1.setNewsHeader("Yimeden Gidesiceler!!");
        news1.setNewsId(1);
        news1.setNewsUrl("https://i.hizliresim.com/Ln22Rb.jpg");

        News news2=new News();
        news2.setNewsHeader("Gümüleyesiceler");
        news2.setNewsId(2);
        news2.setNewsUrl("https://i.hizliresim.com/0B88yB.jpg");
        news2.setNewsBody("3 tane serseri çatışırken gümüleyiveeedi");

        News news3=new News();
        news3.setNewsHeader("Boku çıktı");
        news3.setNewsId(3);
        news3.setNewsUrl("https://i.hizliresim.com/nWYq3a.jpg");
        news3.setNewsBody("Uyurken araba tarafından ezilen bok böceğini boku çıktı");

        list.add(news1);
        list.add(news2);
        list.add(news3);

        return list;
    }

    public String GetNewsTypes()
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/NewsTypes");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
            result=readStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    public String GetNewsJsonStr(int newsId)
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/news/GetNewsById?newsId="+newsId);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
            result=readStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
