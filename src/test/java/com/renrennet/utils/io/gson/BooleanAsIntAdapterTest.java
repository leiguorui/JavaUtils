package com.renrennet.utils.io.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

/**
 * Created by Green Lei on 2015/8/21 11:27.
 */
public class BooleanAsIntAdapterTest {
    @Test
    public void booleanAsInt(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, new BooleanAsIntAdapter())
                .registerTypeAdapter(boolean.class, new BooleanAsIntAdapter())
                .create();

        boolean resutl = gson.fromJson("1", boolean.class);
    }
}
