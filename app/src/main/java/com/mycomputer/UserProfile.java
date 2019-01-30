package com.mycomputer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycomputer.facebooklogin.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;


public class UserProfile extends AppCompatActivity {
    JSONObject response, profile_pic_data, profile_pic_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        Intent intent = getIntent();


        String jsondata = DnaPrefs.getString(this, Constants.UserProfile);
        Log.w("Jsondata", jsondata);
        TextView user_name = (TextView) findViewById(R.id.UserName);
        ImageView user_picture = (ImageView) findViewById(R.id.profilePic);
        TextView user_email = (TextView) findViewById(R.id.email);
        try {
            response = new JSONObject(jsondata);
            String name = response.getString("name");
            String email = response.getString("id");

            user_email.setText(name);
            user_name.setText(email);

            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(user_picture);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.profile_edit1)
        {
            Intent intent=new Intent(UserProfile.this,MainActivity.class);
            DnaPrefs.putBoolean(UserProfile.this,Constants.LoginCheck,false);
            startActivity(intent);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }
}


