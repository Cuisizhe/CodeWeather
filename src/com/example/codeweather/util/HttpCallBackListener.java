package com.example.codeweather.util;

public interface HttpCallBackListener {
	void onfinish(String response);

	void onError(Exception e);
}
