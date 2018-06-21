package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


public class App {
    public static void main(String[] args) {
        if (args == null || args.equals("")) {
            System.out.println("error");
        }

        String apiUrl = "http://challenge-server.code-check.io/api/hash?q=" + String.join(" ", args);
        URL connectUrl;
        try {
            connectUrl = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)connectUrl.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(true);

            final InputStream in = con.getInputStream();
            final InputStreamReader inReader = new InputStreamReader(in);
            final BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            // 1行ずつテキストを読み込む
            while((line = bufReader.readLine()) != null) {

                JSONObject obj = new JSONObject(line);

                Map<String, Object> map = new HashMap<>();
                for(Object key : obj.keySet()) {
                    map.put((String) key, obj.get((String) key));
                }

                System.out.println(map.get("hash"));
//
//
//                System.out.println(line);
//                JSONArray jsonArray = new JSONArray(line);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                  //JSONオブジェクトをパースして、レコードのname属性をログ出力
//                  JSONObject jsonObject = jsonArray.getJSONObject(i);
//                  System.out.println(jsonObject.getString());
//                }
            }
            bufReader.close();
            inReader.close();
            in.close();




//            System.out.println("レスポンスヘッダ:");
//            System.out.println("レスポンスコード[" + con.getResponseCode() + "] " +
//                                        "レスポンスメッセージ[" + con.getResponseMessage() + "]");
//            System.out.println("レスポンスボディ[" + con.getContent());
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
