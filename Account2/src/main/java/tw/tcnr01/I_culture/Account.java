package tw.tcnr01.I_culture;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Account extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText Account_etxt01, Account_etxt02;
    private TextView Account_txt03;
    private Button Account_btn01, Account_btn02, Account_btn03;
    private String input_account, input_password;
    private String test_account, test_pwd, test_mail;//用來模擬抓取註冊後新增的值
    private String TAG = "tcnr01=>";
    private Button test;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout);

        toolbar = findViewById(R.id.head_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("側邊欄");

        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupViewComponent();
    }

    private void setupViewComponent() {
        Account_etxt01 = (EditText) findViewById(R.id.Account_etxt01);//帳號
        Account_etxt02 = (EditText) findViewById(R.id.Account_etxt02);//密碼
        Account_txt03 = (TextView) findViewById(R.id.Account_txt03);//忘記密碼
      //  Account_btn01 = (Button) findViewById(R.id.Account_btn01);//註冊
        Account_btn02 = (Button) findViewById(R.id.Account_btn02);//登入
        Account_btn03 = (Button) findViewById(R.id.Account_btn03);//使用google帳號登入
        test = (Button)findViewById(R.id.test);//測試進入設定頁面用

        Account_txt03.setOnClickListener(this);
//        Account_btn01.setOnClickListener(this);
        Account_btn02.setOnClickListener(this);
        Account_btn03.setOnClickListener(this);
        test.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Account_txt03://忘記密碼
                input_account = Account_etxt01.getText().toString();
                //先確認帳號有無輸入
                if (!input_account.equals("")) {
                    //再確認輸入的帳號是否存在
                    if (checkAccount(input_account)) {
                        //確認有此帳號後,顯示dialog,並在後台將密碼寄到註冊信箱
                        //呼叫getDialog()回傳一個空的dialog
                        getDialog().setMessage(R.string.Account_forgetpwd).show();
                    } else {
                        //確認無此帳號後,顯示dialog
                        getDialog().setMessage(R.string.Account_account_check_error).show();
                    }
                } else {
                    getDialog().setMessage(R.string.Account_account_input_notext).show();
                }
                break;

           /* case R.id.Account_btn01://註冊
                //到註冊的頁面
                Intent it = new Intent(Account.this, Register.class);
                startActivity(it);
                Account.this.finish();
                break;*/

            case R.id.Account_btn02://登入
                //取得輸入的帳號及密碼
                input_account = Account_etxt01.getText().toString();
                input_password = Account_etxt02.getText().toString();

                if (check(input_account, input_password)) {
                    //輸入正確的話,跳出對應的dialog
                    AlertDialog.Builder d = new AlertDialog.Builder(this);
                    d.setTitle("通知!")
                            .setMessage(input_account + getString(R.string.Account_welcome))
                            .setCancelable(false);
                    d.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //按下確定後離開登入頁面,並回到首頁
                            finish();
                        }
                    });
                    d.show();
                } else {
                    //輸入錯誤的話,跳出對應的dialog
                    getDialog().setMessage(R.string.Account_error).show();
                }

                break;

            case R.id.Account_btn03://使用google帳號登入
                break;

            case R.id.test://到設定的頁面
                Intent itn = new Intent(Account.this, Settings.class);
                startActivity(itn);
                Account.this.finish();
                break;

        }
    }

    private AlertDialog.Builder getDialog() {

        AlertDialog.Builder f = new AlertDialog.Builder(this);
        f.setTitle("通知!")
                .setCancelable(false);
        f.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return f;
    }

    private boolean checkAccount(String input_account) {
        String account = "owner";
        if (account.equals(input_account)) {
            return true;
        } else {
            return false;
        }

    }

    //將輸入的帳號及密碼送到後台做判斷
    private boolean check(String input_account, String input_password) {
        //模擬用的
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        if (bundle != null) {
            Log.d(TAG, "account=" + bundle.getString("account"));
            Log.d(TAG, "pwd=" + bundle.getString("pwd"));
            if (bundle.get("account")!=null&&bundle.get("pwd")!=null) {
                if (bundle.getString("account").equals(input_account) && bundle.getString("pwd").equals(input_password)) {
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }

        } else {
            return false;
        }
        //--------------
    /*  String account = "owner01";
      String pwd = "a123456789";

      if (input_account.equals(account)&&input_password.equals(pwd)){
          return true;
      }else {
          return false;
      }*/


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homepage) {
            Toast.makeText(this, "首頁", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
