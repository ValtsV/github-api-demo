package com.vala.github.api.demo;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class FileLineCounter {
//    attr
    URL url;

//    constr


    public FileLineCounter() {
    }

    public FileLineCounter(URL url) {
        this.url = url;
    }

//    meth

    public int countLines() throws IOException {
        int count = 0;
        try (InputStream in = url.openStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((reader.readLine()) != null) {
                count++;
            }
        }
        return count;
    }
}
