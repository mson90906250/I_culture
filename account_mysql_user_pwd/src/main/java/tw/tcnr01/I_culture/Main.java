package tw.tcnr01.I_culture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private FragmentTransaction mTransaction;
    private ImageView img01;
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;
    private AboutUsFragment aboutusFragment;
    private MapFragment mapFragment;
    private MoreFragment moreFragment;
    private FavoriteFragment favoriteFragment;
    private ShopFragment shopFragment;
    private NewsFragment newsFragment;
    private BusFragment busFragment;
    private  UbikeFragment ubikeFragment;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("I文創 審計新村");

        //用不到
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //用不到
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        //將content_account的layout替換成home_fragment
        homeFragment = new HomeFragment();
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.container,homeFragment).commit();


        setupViewComponent();
    }

    private void setupViewComponent() {
        //做fragment的findVIewById();
        aboutFragment = new AboutFragment();
        aboutusFragment = new AboutUsFragment();
        busFragment = new BusFragment();
        favoriteFragment = new FavoriteFragment();
        mapFragment = new MapFragment();
        moreFragment= new MoreFragment();
        shopFragment = new ShopFragment();
        ubikeFragment = new UbikeFragment();
        newsFragment = new NewsFragment();


        //用巨集做側邊欄按鈕及監聽
        for(int i =0; i<10 ;i++){
            String idName = "btn"+i;
            int id = getResources().getIdentifier(idName,"id",getPackageName());
            Button btn = (Button)findViewById(id);
            btn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0://回首頁
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,homeFragment).commit();
                toolbar.setTitle("I文創 審計新村");
                break;

            case R.id.btn1://回關於審計
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,aboutFragment).commit();
                toolbar.setTitle("關於審計");
                break;

            case R.id.btn2://回近期活動
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,newsFragment).commit();
                toolbar.setTitle("近期活動");
                break;

            case R.id.btn3://回店家介紹
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,shopFragment).commit();
                toolbar.setTitle("店家介紹");
                break;

            case R.id.btn4://回我的最愛
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,favoriteFragment).commit();
                toolbar.setTitle("我的最愛");
                break;

            case R.id.btn5://回探索更多
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,moreFragment).commit();
                toolbar.setTitle("探索更多");
                break;

            case R.id.btn6://回公車資訊
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,busFragment).commit();
                toolbar.setTitle("公車資訊");
                break;

            case R.id.btn7://回附近Ubike
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,ubikeFragment).commit();
                toolbar.setTitle("附近Ubike");
                break;

            case R.id.btn8://回導航到審計
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,mapFragment).commit();
                toolbar.setTitle("導航到審計");
                break;

            case R.id.btn9://回關於我們
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.replace(R.id.container,aboutusFragment).commit();
                toolbar.setTitle("關於我們");
                break;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //menu-------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.login://登入
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Account.class);
                startActivity(intent);
                break;
        }

        /*       // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }



    //系統生成用不到
    /*  @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
