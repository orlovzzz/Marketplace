package com.example.service;

import com.example.entity.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ProductService {

//    0 - book
//    1 - phone
//    2 - machine

    public List<Products> getProducts(int flag, String urlPart) throws IOException {
        URL url = new URL("http://localhost:8080/" + urlPart);
        Type listType;
        if (flag == 0) {
            listType = new TypeToken<List<Book>>() {}.getType();
        } else if (flag == 1) {
            listType = new TypeToken<List<Telephone>>() {}.getType();
        } else {
            listType = new TypeToken<List<WashingMachine>>() {}.getType();
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        List<Products> products = null;
        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            Gson gson = new Gson();
            products = gson.fromJson(String.valueOf(response), listType);
        }
        return products;
    }


    public Products getCurrentProduct(int flag, String urlPart) throws IOException {
        URL url = new URL("http://localhost:8080й" + urlPart);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Products products = null;
        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            Gson gson = new Gson();
            if (flag == 0) products = gson.fromJson(String.valueOf(response), Book.class);
            else if (flag == 1) products = gson.fromJson(String.valueOf(response), Telephone.class);
            else products = gson.fromJson(String.valueOf(response), WashingMachine.class);
        }
        return products;
    }

}
