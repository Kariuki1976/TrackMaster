package com.example.phonetracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.phonetracker.ui.Products;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ProductsTest {
    private Products products;

    @Before
    public void setUp(){
        products = Robolectric.buildActivity(Products.class)
                .create()
                .start()
                .resume()
                .get();

    }
    @Test
    public  void userInput(){
        EditText editText = products.findViewById(R.id.editTextTextPersonName);
        assertEquals("name", editText.getHint());

    }
    @Test
    public void validateButton(){
        ImageButton button = products.findViewById(R.id.contactUs);
        assertNotNull(button);

    }
}
