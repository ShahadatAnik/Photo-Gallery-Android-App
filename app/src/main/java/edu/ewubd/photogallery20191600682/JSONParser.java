package edu.ewubd.photogallery20191600682;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

@SuppressWarnings("ALL")
public class JSONParser {

    private static JSONParser instance = new JSONParser();
    private JSONParser() {}
    public static JSONParser getInstance() {
        return instance;
    }

    public String makeHttpRequest(String url, String method) {

        HttpURLConnection http = null;
        InputStream stream = null;

        try {
            URL urlc = new URL(url);
            http = (HttpURLConnection) urlc.openConnection();
            http.connect();
            stream = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            stream.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

