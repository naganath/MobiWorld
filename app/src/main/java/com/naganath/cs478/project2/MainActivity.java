package com.naganath.cs478.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mobile> dataArray;

    public static final String IMAGE_KEY = "image_id";
    public static final String WEB_KEY = "website_uri";
    public static final String SPECS = "specs";


    private ListView.OnItemClickListener shortClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Mobile mobileDetails = dataArray.get(position);
            Intent i = new Intent(getApplication(), ImageDisplayActivity.class);
            i.putExtra(IMAGE_KEY, mobileDetails.getImage());
            i.putExtra(WEB_KEY, mobileDetails.getWebsite());
            startActivity(i);
        }
    };

    @Override
    public void  onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.view1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menus, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String a = "http://www.google.com";
        long itemPos = info.id;
        Mobile mdetails = dataArray.get((int)itemPos);
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.specs:
                intent.putExtra(SPECS, mdetails.getSpecId());
                intent.setClass(getApplicationContext(),SpecsActivity.class );
                break;
            case R.id.webpage:
                intent.putExtra(WEB_KEY, mdetails.getWebsite());
                intent.setClass(getApplicationContext(),WebsiteDisplayActivity.class );
                break;
            case R.id.pic:
                 intent.setClass(getApplicationContext(), ImageDisplayActivity.class);
                intent.putExtra(IMAGE_KEY, mdetails.getImage());
                intent.putExtra(WEB_KEY, mdetails.getWebsite());
                break;
        }
        super.onContextItemSelected(item);
        startActivity(intent);
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        createData();
        ListView listView =  findViewById(R.id.view1);
        ItemViewAdapter adapter = new ItemViewAdapter(this, R.layout.list_item, dataArray);
        listView.setOnItemClickListener(shortClickListener);
        registerForContextMenu(listView);
        listView.setAdapter(adapter);
    }

    private void createData() {
        dataArray = new ArrayList<>();
        ArrayList<Integer> mThumbIdsMobiles = new ArrayList<>(Arrays.asList(R.drawable.image1, R.drawable.image2,
                R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7,
                R.drawable.image8));
        List<Integer> specIdList = Arrays.asList(R.array.iphone_xr, R.array.pixel_2, R.array.galaxy_note9,
        R.array.redminote5, R.array.v35thinqphone, R.array.Oneplus6t, R.array.MotoG7, R.array.iphone8);
        int i =0 ;
        ArrayList<String> titleArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.names)));
        ArrayList<String> detailArray= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.details)));
        ArrayList<String> webArray= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.websites)));
        for(Integer image : mThumbIdsMobiles) {
            Mobile mobile = new Mobile();
            mobile.setImage(image);
            mobile.setSpecId(specIdList.get(i));
            dataArray.add(mobile);
            dataArray.get(i).setName(titleArray.get(i));
            dataArray.get(i).setDetails(detailArray.get(i));
            dataArray.get(i).setWebsite(webArray.get(i));
            i++;

        }
    }
}
