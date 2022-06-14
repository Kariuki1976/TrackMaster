package com.example.phonetracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.widget.Button;
import android.widget.TextView;

import com.example.phonetracker.ui.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setUp(){
        mainActivity= Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();

    }
    @Test
    public  void userInput(){
        TextInputLayout textInputLayout = mainActivity.findViewById(R.id.EditText);
        assertEquals("Enter Your Name", textInputLayout.getEditText().getHint());

    }
    @Test
    public void validateButton(){
        Button button = mainActivity.findViewById(R.id.submitButton);
        assertEquals("Submit", button.getText());

    }
    @Test
    public void validateTextViewHead(){
        TextView textView3 = mainActivity.findViewById(R.id.textView3);
        assertTrue("Welcome to Our Mobile".equals(textView3.getText().toString()));
    }


}
