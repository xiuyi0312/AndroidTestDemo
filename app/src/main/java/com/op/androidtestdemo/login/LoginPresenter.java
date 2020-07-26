package com.op.androidtestdemo.login;

import android.text.TextUtils;

import com.op.androidtestdemo.manager.UserManager;
import com.op.androidtestdemo.util.PasswordValidator;

public class LoginPresenter {
    private UserManager userManager = new UserManager();
    private PasswordValidator passwordValidator = new PasswordValidator();

    public void login(String userName, String password) {
        if (userName == null || userName.length() <= 0) return;
        if (!passwordValidator.validatePassword(password)) return;
        userManager.performLogin(userName, password);
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public PasswordValidator getPasswordValidator() {
        return passwordValidator;
    }

    public void setPasswordValidator(PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }
}
