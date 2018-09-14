package com.example.codeweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static void sendHttpRequest(final String adress, final HttpCallBackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection con = null;
				try {
					URL url = new URL(adress);
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.setReadTimeout(8000);
					con.setConnectTimeout(8000);
					InputStream in = con.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					String line = "";
					StringBuilder response = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						listener.onfinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				} finally {
					if (con != null) {
						con.disconnect();
					}
				}
			}
		}).start();
	}
}
