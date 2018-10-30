package com.energysh.loginsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.energysh.library.interfaces.OnekeyLoginManager;
import com.energysh.library.interfaces.Result;
import com.energysh.library.interfaces.ResultBean;

public class VerificationCodeLoginActivity extends AppCompatActivity {

    private EditText etPhoneNumber;

    private EditText etVerificationCode;

    private Button btnCode;

    private Button btnCheckCode;

    private TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code_login);
        setTitle("短信验证码登录");
        etPhoneNumber = findViewById(R.id.et_phoneNumber);
        etVerificationCode = findViewById(R.id.et_verification_code);
        btnCode = findViewById(R.id.get_code);
        btnCheckCode = findViewById(R.id.btn_check);
        tvMessage = findViewById(R.id.tv_msg);

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhoneNumber.getText().toString().isEmpty()) {
                    //自行验证正确的手机号码
                    Toast.makeText(VerificationCodeLoginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                OnekeyLoginManager.sendVerificationCode(etPhoneNumber.getText().toString(), new Result<ResultBean>() {
                    @Override
                    public void onResult(ResultBean resultBean) {
                        tvMessage.setText("success: " + resultBean.getSuccess()  +" msg:" + resultBean.getMsg() +"\n" + tvMessage.getText().toString());
                    }
                });
            }
        });

        btnCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyLoginManager.checkVerificationCode(etPhoneNumber.getText().toString(), etVerificationCode.getText().toString(), new Result<ResultBean>() {
                    @Override
                    public void onResult(ResultBean resultBean) {
                        tvMessage.setText("success:" + resultBean.getSuccess() + "msg:" + resultBean.getMsg() +"\n" + tvMessage.getText().toString());
                    }
                });
            }
        });

    }
}
