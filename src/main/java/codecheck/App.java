package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


public class App {
    public static void main(String[] args) {

        if (args == null || args.equals("")) {
            System.out.println("error");
        }

        String argStr = String.join(" ", args);

        try {
            String apiUrl = "http://challenge-server.code-check.io/api/hash?q=" + URLEncoder.encode(argStr,"UTF-8");

            URL connectUrl = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)connectUrl.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(true);

            InputStream in = con.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;

            while((line = bufReader.readLine()) != null) {

                JSONObject obj = new JSONObject(line);

                Map<String, Object> map = new HashMap<>();
                for(Object key : obj.keySet()) {
                    map.put((String) key, obj.get((String) key));
                }

                System.out.println(map.get("hash"));
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
