package codecheck;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        String apiUrl = "/api/hash";
        URL connectUrl;
        try {
            connectUrl = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)connectUrl.openConnection();

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(true);

            System.out.println("レスポンスヘッダ:");
            System.out.println("レスポンスコード[" + con.getResponseCode() + "] " +
                                        "レスポンスメッセージ[" + con.getResponseMessage() + "]");
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
