package com.robogust.examBackend;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class GameController {

    @GetMapping("/words")
    public String getWords() {
        try {
            // Set up the URL and open a connection
            URL url = new URL("https://random-word-api.vercel.app/api?words=6");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Read the response from the API into a StringBuilder
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(response.toString());

            // Loop through each object in the array and print it out
            for (int i = 0; i < jsonArray.length(); i++) {
                // Get the word from the current object
                String word = jsonArray.getString(i);
                System.out.println(word);
            }
            return response.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
