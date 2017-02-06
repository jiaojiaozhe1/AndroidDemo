package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.johnson.commonlibs.common_utils.BaseActivity;
import com.johnson.commonlibs.common_utils.utils.RegxUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.user_name_textinputlay)
    TextInputLayout userInputLay;
    @Bind(R.id.user_pwd_textinputlay)
    TextInputLayout pwdInputLay;
    @Bind(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        initListener();

        userInputLay.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkPhoneInput(editable.toString());
            }
        });

        pwdInputLay.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkPwdInput(editable.toString());
            }
        });

    }

    private void initListener() {
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == loginBtn) {
            hideKeyboard();
            String userName = userInputLay.getEditText().getText().toString();
            String userPwd = pwdInputLay.getEditText().getText().toString();
            if (!checkPhoneInput(userName)){
                return;
            } else if (!checkPwdInput(userPwd)){
                return;
            }
            MainActivity.startAction(this);
        }
    }

    private boolean checkPwdInput(String value) {
        if (!validatePwd(value)) {
            pwdInputLay.setError("密码不能低于6位");
            return false;
        } else {
            pwdInputLay.setError(null);
            return true;
        }
    }

    private boolean checkPhoneInput(String value) {
        if (!RegxUtils.validatePhone(value)) {
            userInputLay.setError("手机号码格式不对");
            return false;
        } else {
            userInputLay.setError(null);
            return true;
        }
    }

    private boolean validatePwd(String pwd) {
        return pwd.length() > 5;
    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void startLoginAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
