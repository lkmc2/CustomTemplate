package linchange.com.customtemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private HeaderBar headerBar; //标题控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerBar = (HeaderBar) findViewById(R.id.headerBar);
        headerBar.setOnClickListener(new HeaderBar.HeaderBarClickListener(){
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "点击了左按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "点击了右按钮", Toast.LENGTH_SHORT).show();
            }
        });

        headerBar.setRightButtonVisibility(false); //设置右按钮不可见
    }
}
