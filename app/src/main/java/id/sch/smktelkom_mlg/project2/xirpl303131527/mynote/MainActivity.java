package id.sch.smktelkom_mlg.project2.xirpl303131527.mynote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            changePage(id);
            return true;

        }

    };
    private void changePage(int id) {
        Fragment fragment = null;
        if (id == R.id.navigation_home) {
            fragment = new my();
        }
        else if(id == R.id.navigation_dashboard) {
            fragment = new reminder();
        }
        else if(id == R.id.navigation_notifications){
            fragment = new about();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commitNow();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changePage(R.id.navigation_home);
    }

}
