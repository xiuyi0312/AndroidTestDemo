package com.op.androidtestdemo;

import com.op.androidtestdemo.login.LoginPresenter;
import com.op.androidtestdemo.manager.UserManager;
import com.op.androidtestdemo.util.PasswordValidator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LoginActivityTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testLogin() {
        UserManager userManager = Mockito.mock(UserManager.class);
        LoginPresenter loginPresenter = new LoginPresenter();

        loginPresenter.setUserManager(userManager);

        loginPresenter.login("zhangsan", "123456");
        Mockito.verify(userManager, Mockito.atLeastOnce()).performLogin("zhangsan", "123456");

        loginPresenter.login("zhangsan", "12456");
        Mockito.verify(userManager, Mockito.times(1)).performLogin(Mockito.anyString(), Mockito.anyString());

        loginPresenter.login(null, "123");
        Mockito.verify(userManager, Mockito.times(1)).performLogin(Mockito.anyString(), Mockito.anyString());
    }
}
