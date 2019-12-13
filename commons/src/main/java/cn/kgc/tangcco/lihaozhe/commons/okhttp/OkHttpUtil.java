package cn.kgc.tangcco.lihaozhe.commons.okhttp;


import java.io.IOException;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;


import okhttp3.*;


public class OkHttpUtil {
	//private static Gson gson = new Gson();
	private static final String MEDIA_TYPE = "application/json; charset=utf-8";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	//private static final String METHOD_PUT = "PUT";
	//private static final String METHOD_DELETE = "DELETE";
	private static OkHttpClient okHttpClient;

	//private static OkHttpClient.Builder builder;

	private OkHttpUtil() {
		/*
		 * if (okHttpClient == null) { synchronized (HttpUtils.class) { if (builder ==
		 * null) { builder = new OkHttpClient.Builder(); builder.readTimeout(10000,
		 * TimeUnit.MILLISECONDS); builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
		 * builder.connectTimeout(10000, TimeUnit.MILLISECONDS); builder.build(); } } }
		 */
	}

	/*
	 * public static void init() { OkHttpClient.Builder builder = new
	 * OkHttpClient.Builder(); builder.readTimeout(10000, TimeUnit.MILLISECONDS);
	 * builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
	 * builder.connectTimeout(10000, TimeUnit.MILLISECONDS); okHttpClient =
	 * builder.build(); }
	 */
	
	
	//// 实体内容  样本
	//RequestBody requestBody = new FormBody.Builder().add("account", "ghost").add("password", "root").build();
	
	
	
	/**
	 * 异步post方法  需要回调
	 *  请求体   
	 * @param url
	 * @param    requestBody
	 * @param 
	 */
	public static void enqueuePost(String url,RequestBody requestBody ) {
		okHttpClient=new OkHttpClient();
		Request request = createRequest(url, METHOD_POST, requestBody );
		okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// 异步请求失败之后的回调	
				System.out.println("异步Post失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// 异步请求成功之后的回调
				// 解析服务器响应内容
				String content = response.body().string();
				// 输入服务器响应内容
				System.out.println(content);
				// 解析json
				//Person person = JSON.parseObject(content, Person.class);
				JSON.parseObject(content,Object.class);
			}	
		});
	}

	
	
	/**
	 * 异步get方法  需要回调
	 * @param url
	 * @param callback
	 */
	public static void enqueueGet(String url) {
		okHttpClient=new OkHttpClient();
		Request request = createRequest(url, METHOD_GET, null);	
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// 异步请求失败之后的回调	
				System.out.println("异步Git失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// 异步请求成功之后的回调
				// 解析服务器响应内容
				String content = response.body().string();
				// 输入服务器响应内容
				System.out.println(content);
				// 解析json
				//Person person = JSON.parseObject(content, Person.class);
				JSON.parseObject(content,Object.class);
			}	
		});
	}

	/**
	 * 同步post方法   post  写实体内容
	 *请求体   
	 * @param url  requestBody
	 */
	public static Response executePost(String url,RequestBody requestBody) throws IOException {
		okHttpClient=new OkHttpClient();
		Request request = createRequest(url, METHOD_POST, requestBody);
		Call call = okHttpClient.newCall(request);
		Response response = call.execute();
		return response;
	}

	/**
	 * 同步get方法 
	 *
	 * @param url
	 */
	public static Response executeGet(String url) throws IOException {
		System.out.println("进入同步get");
		OkHttpClient okHttpClient=new OkHttpClient();
		Request request = createRequest(url, METHOD_GET, null);
		Call call = okHttpClient.newCall(request);
		Response response = call.execute();	
		
		System.out.println("get的同步");
		return response;
	}
	
	/**
	 * 创建request对象
	 *
	 * @param url
	 * @param method
	 * @param baseRequest 项目中自己的的baseRequest对象
	 * @return
	 */
	private static Request createRequest(String url, String method,RequestBody requestBody ) {	
		Request.Builder builder = new Request.Builder().url(url);
		Request request = null;
		switch (method) {
		case METHOD_GET:
			request = builder.get().build();
			break;
		case METHOD_POST:
			request = builder.post(requestBody).build();
			break;
		}
		return request;
	}
}
