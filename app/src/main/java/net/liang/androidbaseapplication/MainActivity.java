package net.liang.androidbaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.baseRecyclerViewActivity, R.id.baseRecyclerViewFragment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.baseRecyclerViewActivity:
                intent.setClass(this,Test_BaseRecyclerViewActivity.class);
                startActivity(intent);
                break;

            case R.id.baseRecyclerViewFragment:
                intent.setClass(this,Test_BaseRecyclerViewFragment.class);
                startActivity(intent);
                break;
        }

    }
}
