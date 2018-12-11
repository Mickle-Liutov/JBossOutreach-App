package com.community.jboss.outreach.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.jboss.outreach.ApiContribHandler;
import com.community.jboss.outreach.R;
import com.community.jboss.outreach.utils.KeyStore;
import com.squareup.picasso.Picasso;

public class ContribPodiumActivity extends ReceiveContribsBaseActivity {
    TextView name_1;
    ImageView picture_1;
    TextView number_1;
    RelativeLayout chart_1;

    TextView name_2;
    ImageView picture_2;
    TextView number_2;
    RelativeLayout chart_2;

    TextView name_3;
    ImageView picture_3;
    TextView number_3;
    RelativeLayout chart_3;

    Button see_more_button;
    String repository_name;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors_podium);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        see_more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent see_more_intent = new Intent(ContribPodiumActivity.this, ContributActivity.class);
                see_more_intent.putExtra(KeyStore.NAME_KEY, repository_name);
                startActivity(see_more_intent);
            }
        });
        repository_name = getIntent().getStringExtra(KeyStore.NAME_KEY);
        new ApiContribHandler(this, repository_name).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLinksListeners(final String[][] data) {


        picture_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[1][3]));
                startActivity(browserIntent);
            }
        });
        name_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[1][3]));
                startActivity(browserIntent);
            }
        });
        picture_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[0][3]));
                startActivity(browserIntent);
            }
        });
        name_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[0][3]));
                startActivity(browserIntent);
            }
        });
        picture_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[2][3]));
                startActivity(browserIntent);
            }
        });
        name_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[2][3]));
                startActivity(browserIntent);
            }
        });
    }

    private void initViews() {
        name_1 = findViewById(R.id.name_1);
        picture_1 = findViewById(R.id.picture_1);
        number_1 = findViewById(R.id.number_1);
        chart_1 = findViewById(R.id.chart_1);

        name_2 = findViewById(R.id.name_2);
        picture_2 = findViewById(R.id.picture_2);
        number_2 = findViewById(R.id.number_2);
        chart_2 = findViewById(R.id.chart_2);

        name_3 = findViewById(R.id.name_3);
        picture_3 = findViewById(R.id.picture_3);
        number_3 = findViewById(R.id.number_3);
        chart_3 = findViewById(R.id.chart_3);

        see_more_button = findViewById(R.id.see_more_button);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void receiveData(String[][] data) {
        Picasso.get().load(data[1][0]).fit().centerCrop().into(picture_1);
        Picasso.get().load(data[0][0]).fit().centerCrop().into(picture_2);
        Picasso.get().load(data[2][0]).fit().centerCrop().into(picture_3);

        name_1.setText(data[1][1]);
        name_2.setText(data[0][1]);
        name_3.setText(data[2][1]);

        number_1.setText(data[1][2]);
        number_2.setText(data[0][2]);
        number_3.setText(data[2][2]);

        progressBar.setVisibility(View.GONE);

        name_1.setVisibility(View.VISIBLE);
        name_2.setVisibility(View.VISIBLE);
        name_3.setVisibility(View.VISIBLE);

        picture_1.setVisibility(View.VISIBLE);
        picture_2.setVisibility(View.VISIBLE);
        picture_3.setVisibility(View.VISIBLE);

        chart_1.setVisibility(View.VISIBLE);
        chart_2.setVisibility(View.VISIBLE);
        chart_3.setVisibility(View.VISIBLE);

        setLinksListeners(data);
    }
}
