package tw.tcnr01.I_culture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText Register_etxt01,Register_etxt02,Register_etxt03,Register_etxt04;
    private Button Register_btn01,Register_btn02;
    private String input_account,input_password,input_checkpwd,input_mail;
    private final int FLAG_ACCOUNT = 0;
    private final int FLAG_PWD = 1;
    private String TAG = "tcnr01=>";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        setupViewComponet();
    }

    private void setupViewComponet() {
        Register_etxt01 = (EditText)findViewById(R.id.Register_etxt01);//帳號
        Register_etxt02 = (EditText)findViewById(R.id.Register_etxt02);//密碼
        Register_etxt03 = (EditText)findViewById(R.id.Register_etxt03);//確認密碼
        Register_etxt04= (EditText)findViewById(R.id.Register_etxt04);//聯絡信箱

        Register_btn01 = (Button)findViewById(R.id.Register_btn01);//取消並返回
        Register_btn02 = (Button)findViewById(R.id.Register_btn02);//取消並返回

        Register_btn01.setOnClickListener(this);
        Register_btn02.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register_btn01://取消並返回
                Intent it = new Intent(Register.this,Account.class);
                startActivity(it);
                Register.this.finish();
                break;

            case R.id.Register_btn02://確認
                //確認帳號,密碼是否符合規定
                input_password = Register_etxt02.getText().toString();
                input_account = Register_etxt01.getText().toString();
                if (check(FLAG_ACCOUNT,input_account)&&check(FLAG_PWD,input_password)){
                    //帳號和密碼都符合規定後,再判斷確認密碼與密碼是否一致
                    if (recheckPWD(input_password)){
                        //最後確認信箱有無符合格式
                        if (checkMail()){
                           AlertDialog.Builder f =  getDialog().setMessage(R.string.Register_register_success);
                            f.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   //將註冊好的帳號,密碼及信箱傳入資料庫,目前先用Intent傳值模擬
                                    Log.d(TAG,"account="+input_account);
                                    Log.d(TAG,"pwd="+input_password);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("account",input_account);
                                    bundle.putString("pwd",input_password);
                                    bundle.putString("mail",input_mail);
                                    Intent it = new Intent(Register.this,Account.class);
                                    it.putExtras(bundle);
                                    startActivity(it);
                                    finish();
                                }
                            });
                           f.show();

                        }else {
                            getDialog().setMessage(R.string.Register_mail_nomatch).show();
                            break;
                        }
                    }else {
                        getDialog().setMessage(R.string.Register_recheck_password).show();
                        break;
                    }

                }else {
                    getDialog().setMessage(R.string.Register_error).show();
                    break;
                }
        }
    }
    //確認信箱有無符合格式
    private boolean checkMail() {
        input_mail = Register_etxt04.getText().toString();
        //利用Pattern.matches()的方法來確認信箱是否符合格式
        if (Pattern.matches(EMAIL_PATTERN,input_mail)){
            return  true;
        }else {
            return  false;
        }
    }

    //密碼和確認密碼的比對
    private boolean recheckPWD(String input_password) {
        input_checkpwd = Register_etxt03.getText().toString();
        if (input_checkpwd.equals(input_password)) {
            return true;
        }else {
            return false;
        }
    }

    //判斷帳號,密碼是否符合規定
    private boolean check(int flag ,String chk ) {
        int i = 0 ;//根據帳號或密碼

        switch (flag){
            case FLAG_ACCOUNT:
                i = 6;
                break;
            case  FLAG_PWD:
                i = 8;
                break;
                
        }
        //確認帳號或密碼的字數
        if(chk.length()>= i &&chk.length()<=20){
            //字數符合後,再判斷有無非數字的字
            //將字串中的數字替換成"",導致僅留下非數字的字,再算其長度是否大於零
            if (chk.replaceAll("\\d+","").length()>0){
                //一切都符合後,retrun true;
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    
    //製作空的AlertDialog
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
}
