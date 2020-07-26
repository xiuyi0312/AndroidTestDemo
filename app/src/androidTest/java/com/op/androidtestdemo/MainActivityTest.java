package com.op.androidtestdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.op.androidtestdemo.util.FooWrapper;
import com.op.androidtestdemo.util.Util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void clickTest() {

        // 是否不可见
        onView(withId(R.id.tv_content))
                .check(matches(not(isDisplayed())));
        // 点击事件后
        onView(withId(R.id.btn01))
                .check(matches(withText("modify content")))
                .perform(click());
        // 检测点击事件后内容和可见性是否发生了变化
        onView(withId(R.id.tv_content))
                .check(matches(withText("hello espresso")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginTest() throws Exception {
        //先清除editText的内容，然后输入，然后关闭软键盘，最后校验内容
        //这里如果要输入中文，使用replaceText()方法代替typeText()
        onView(withId(R.id.et_01))
                .perform(clearText(), replaceText("hello username"), closeSoftKeyboard())
                .check(matches(withText("hello username")));

        //点击登录
        onView(withId(R.id.btn02))
                .perform(click());

        //校验内容
        onView(withId(R.id.tv_content))
                .check(matches(withText("success")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.et_01))
                .check(matches(withText("")))           //内容是否为""
                .check(matches(withHint("input account")))         //hint内容是否为"请输入账户名"
                .check(matches(withHint(containsString("account"))));       //hint内容是否包含"账户名"

        Thread.sleep(3000);
    }

    @Test
    public void testIntentShouldBeCreated() {
        Context context = mock(Context.class);
        Intent intent = Util.createQuery(context, "query", "value");
        assertNotNull(intent);
        Bundle bundle = intent.getExtras();
        assertNotNull(bundle);
        assertEquals("query", bundle.getString("QUERY"));
        assertEquals("value", bundle.getString("VALUE"));
    }

    @Test
    public void testSendingTweet() {
        TwitterClient twitterClient = new TwitterClient();
        ITweet iTweet = mock(ITweet.class);
        when(iTweet.getMessage()).thenReturn("Using mockito is great");
        twitterClient.sendTweet(iTweet);
        verify(iTweet, times(1)).getMessage();
    }

    @Test
    public void testStaticMethodByWrapper() {
        FooWrapper fooWrapper = mock(FooWrapper.class);
        fooWrapper.staticToMemberMethod();
        verify(fooWrapper, atLeast(1)).staticToMemberMethod();
    }
}
