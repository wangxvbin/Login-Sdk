package com.energysh.loginsdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.energysh.library.interfaces.OnekeyLoginManager;
import com.energysh.library.interfaces.Result;
import com.energysh.library.interfaces.ResultBean;


public class MainActivity extends AppCompatActivity {

    private Button mbtnVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("手机号一键登录");
        OnekeyLoginManager.init(this, "TEST01", "k46J2Q4z3uwwT6nK");

        mbtnVerificationCode = findViewById(R.id.btn_verification_vode);
        mbtnVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VerificationCodeLoginActivity.class));
            }
        });
    }

    public void login(View view) {
        OnekeyLoginManager.quickLogin(this, new Result<ResultBean>() {
            @Override
            public void onResult(ResultBean o) {
                switch (o.getSuccess()) {
                    case "000":
                        Toast.makeText(MainActivity.this, o.getSuccess() + " " + o.getMsg() + " " + o.getPhoneNumber(), Toast.LENGTH_SHORT).show();

                        break;
                    case "102121":
                        Toast.makeText(MainActivity.this, "取消一键登录", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(MainActivity.this, o.getSuccess() +"  " + o.getMsg(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
