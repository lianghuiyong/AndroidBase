package net.liang.androidbaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.baseRecyclerViewActivity)
    public void onClick() {
        Intent intent = new Intent(this,BaseRecyclerViewActivity.class);
        startActivity(intent);
    }
}
