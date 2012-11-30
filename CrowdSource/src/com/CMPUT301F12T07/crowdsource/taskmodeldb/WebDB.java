package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;

import com.google.gson.Gson;

// https://github.com/abramhindle/CrowdSourcer/blob/master/README
/**
 * CrowdSource Service Client (Teaser)
 * @author Victor Guana - guana[at]ualberta.ca
 * University of Alberta, Department of Computing Science
 */
public class WebDB {
	
	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();
	//private AndroidHttpClient httpclient = AndroidHttpClient.newInstance("Android");
	// JSON Utilities
	private Gson gson = new Gson();
	
	
	// POST Request
	HttpPost httpPost = new HttpPost("http://crowdsourcer.softwareprocess.es/F12/CMPUT301F12T07/");
	// http://crowdsourcer.softwareprocess.es/F12/CMPUT301F12T07/?action=list
	
	/**
	 * Sends messages to the crowd service and retrieves its responses
	 */
	public void testService(){

		try {
			//this.testCreateMethods();
			//this.deleteTask("547b05c5d47a8f54f7a036060361c9b825032ef5");
			//this.testUpdateMethods("05d6023314c80dbafbaeb2b7d252d7a5e17fcfb6");
			String lot= this.listTasks();
			System.out.println("List of webTasks in the CrowdSourcer -> " + lot);

		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		  //  httpPost.releaseConnection();
		}
	}
	
	public void testCreateMethods(){

		Task task = new Task(1111, "user1", "cat", "funny videos of cats", "2012-02-03", "2012-04-05", "video", 0, 15);
		WebTask webTask = new WebTask(task);
		
		try {
			this.createTask(webTask);

		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		    //httpPost.releaseConnection();
		}
	}

	public void testUpdateMethods(String id){

		Task task = new Task(1112, "user1-up", "cat-up", "funny videos of cats-up", "2012-02-03-up", "2012-04-05-up", "video-up", 1, 100);
		WebTask webTask = new WebTask(task, id);

		try {
			this.updateTask(webTask);
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
		    //httpPost.releaseConnection();
		}
	}
	/**
	 * Initializes a simple mock task
	 * @return
	 */

	/*
	 * To convert the InputStream to String we use the BufferedReader.readLine()
	 * method. We iterate until the BufferedReader return null which means
	 * there's no more data to read. Each line will appended to a StringBuilder
	 * and returned as String.
	 * (c) public domain: http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/11/a-simple-restful-client-at-android/
	 */
	private  String convertStreamToString(InputStream is) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				is.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public String listTasks() throws Exception{
		
		String jsonStringVersion = new String();
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "list"));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);
	    
	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();
	    
	    System.out.println(status);
	    
	    if (entity != null) {
	        InputStream is = entity.getContent();
	        jsonStringVersion = convertStreamToString(is);
	    }
	    
	    // and ensure it is fully consumed
	    //EntityUtils.consume(entity);
	    return jsonStringVersion;
	}
	
	// Parse json string into light weight Task objects.
	public List<Task> parseJson(String jsonStringVersion) {
		// parse the string to get the list.!!!!!!!!!!!!!!!!!!!!!!
		// Json has summary and id, parse one by one and store as a Task object which has only title and wid.
		List<Task> taskList = new ArrayList();
		return taskList;
	}
	
	
	public WebTask getTask(String wid) throws Exception{
		
		WebTask responseTask = new WebTask();
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "get"));
		nvps.add(new BasicNameValuePair("id", wid));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);
	    
	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();
	    
	    System.out.println(status);
	    
	    if (entity != null) {
	        InputStream is = entity.getContent();
	        String jsonStringVersion = convertStreamToString(is);
	        Type taskType = WebTask.class;     
	        responseTask = gson.fromJson(jsonStringVersion, taskType);
	    }
	    //EntityUtils.consume(entity);
        return responseTask;
	    
	}
	   
	public WebTask createTask(WebTask webTask) throws Exception{
		
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "post"));
		nvps.add(new BasicNameValuePair("summary", webTask.getSummary()));
		//nvps.add(new BasicNameValuePair("description", webTask.getDescription()));
		nvps.add(new BasicNameValuePair("content", gson.toJson(webTask.getContent())));
		
		
		//HttpClient httpclient = new DefaultHttpClient(); // debug
		//HttpPost httpPost = new HttpPost("http://crowdsourcer.softwareprocess.es/F12/CMPUT301F12T07/");
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);
	    
	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();
	    
	    System.out.println(status);
	    
	    if (entity != null) {
	        InputStream is = entity.getContent();
	        String jsonStringVersion = convertStreamToString(is);
	        Type taskType = WebTask.class;     
	        webTask = gson.fromJson(jsonStringVersion, taskType);
	    }
	    //EntityUtils.consume(entity);
        return webTask;
	}
	
	public void updateTask(WebTask webTask) throws Exception{
		
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "update"));
		nvps.add(new BasicNameValuePair("id", webTask.getId()));
		nvps.add(new BasicNameValuePair("summary", webTask.getSummary()));
		nvps.add(new BasicNameValuePair("description", webTask.getDescription()));
		nvps.add(new BasicNameValuePair("content", gson.toJson(webTask.getContent())));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);
	    
	    String status = response.getStatusLine().toString();
	    System.out.println(status);
		
	}
	
	public WebTask deleteTask(String wid) throws Exception{

		WebTask responseTask = new WebTask();
		List <BasicNameValuePair> nvps = new ArrayList <BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("action", "remove"));
		nvps.add(new BasicNameValuePair("id", wid));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpclient.execute(httpPost);
	    
	    String status = response.getStatusLine().toString();
	    HttpEntity entity = response.getEntity();
	    
	    System.out.println(status);
	    
	    if (entity != null) {
	        InputStream is = entity.getContent();
	        String jsonStringVersion = convertStreamToString(is);
	        Type taskType = WebTask.class;     
	        responseTask = gson.fromJson(jsonStringVersion, taskType);
	    }
	    //EntityUtils.consume(entity);
        return responseTask;
	}
	
	
	// Main Test
//	public static void main(String...args){
		
//		WebDB webDB = new WebDB();
//		webDB.testService();
//	}
}
