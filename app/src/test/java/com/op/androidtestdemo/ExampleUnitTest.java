package com.op.androidtestdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.op.androidtestdemo.entity.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    User user;

    @Mock
    TextView mockTV;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTV() {
        mockTV.setText("test");
        // 验证某个方法是否被调用，如这里的setText
        Mockito.verify(mockTV).setText("test");

        when(mockTV.getText()).thenReturn("password");

        System.out.println(mockTV.getText());

        // 注意区分不同返回类型的写法
        // 非void方法
        when(mockTV.getText()).thenThrow(new RuntimeException("非void方法"));
        // void方法
        Mockito.doThrow(new RuntimeException("void方法")).when(mockTV).setText(null);

        // 异步方式
//        Mockito.when(mockTV.getText()).thenAnswer(new Answer<String>() {
//            @Override
//            public String answer(InvocationOnMock invocation) {
//                System.out.println("custom answer");
//                return "test";
//            }
//        });

//        System.out.println(mockTV.getText());
//        mockTV.setText(null);
    }

    /**
     * 参数匹配器
     */
    @Test
    public void testParameter() {
        Mockito.doNothing().when(user).setPhone(Mockito.argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return false;
            }
        }));

    }


    @Test
    public void testIntentShouldBeCreated() {
        Context context = mock(Context.class);
        Intent intent = MainActivity.createQuery(context,
                "query", "value");
        assertNotNull(intent);
        Bundle bundle = intent.getExtras();
        assertNotNull(bundle);
        assertEquals("query", bundle.getString("QUERY"));
        assertEquals("value", bundle.getString("VALUE"));
    }

    @Mock
    TwitterClient twitterClient;

    @Test
    public void testSendingTweet() {
        ITweet iTweet = mock(ITweet.class);
        when(iTweet.getMessage()).thenReturn("Using mockito is great");
        twitterClient.sendTweet(iTweet);
        System.out.println(iTweet.getMessage());
        verify(iTweet).getMessage();
    }
}