package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import net.minidev.json.JSONArray;

@RestController
@RequestMapping("/yelp")
public class ReviewsController {

	@RequestMapping("/getreviews")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping
	@ResponseBody
	public ResponseEntity<JSONObject> getReviewsData() throws ParseException, IOException  {
		
		  String SAMPLE_URL=
		  "https://api.yelp.com/v3/businesses/search?location=West+Allis&term=Double+B’s+BBQ&radius=4000&latitude=43.0169&longitude=-88.00531&categories=burgers";
		  HttpClient client = new DefaultHttpClient(); HttpGet request = new
		  HttpGet(SAMPLE_URL); request.setHeader(HttpHeaders.CONTENT_TYPE,
		  "application/x-www-form-urlencoded");
		  request.setHeader(HttpHeaders.AUTHORIZATION,
		  "Bearer 5YHQudCkUX2czo-OQy8oahClcMdy9A6ZOoJbTTOB9niEmf2O_3wrNryFn7REg28Mq8azd7E3CtmEt-_pH2wBA4BLi6ISQwfWQ1qWk3Dw-L6fXpIlfkN5D4li0vatXXYx"
		  ); HttpResponse response=client.execute(request);
		  
		  
		  HttpEntity httpEntity = response.getEntity(); String jsonString =
		  EntityUtils.toString(httpEntity); System.out.println(jsonString);
		  
		  JsonObject convertedObject = new Gson().fromJson(jsonString,
		  JsonObject.class);
		  
		  JsonArray totalPages = convertedObject.get("businesses").getAsJsonArray();
		  System.out.println(totalPages); String id =
		  totalPages.get(0).getAsJsonObject().get("id").getAsString();
		  
		  String
		  REVIEWS_SAMPLE_URL="https://api.yelp.com/v3/businesses/"+id+"/reviews";
		  System.out.println(REVIEWS_SAMPLE_URL); HttpClient reviewsClient = new
		  DefaultHttpClient(); HttpGet reviewsRequest = new
		  HttpGet(REVIEWS_SAMPLE_URL);
		  reviewsRequest.setHeader(HttpHeaders.CONTENT_TYPE,
		  "application/x-www-form-urlencoded");
		  reviewsRequest.setHeader(HttpHeaders.AUTHORIZATION,
		  "Bearer 5YHQudCkUX2czo-OQy8oahClcMdy9A6ZOoJbTTOB9niEmf2O_3wrNryFn7REg28Mq8azd7E3CtmEt-_pH2wBA4BLi6ISQwfWQ1qWk3Dw-L6fXpIlfkN5D4li0vatXXYx"
		  ); HttpResponse reviewsResponse=client.execute(reviewsRequest); HttpEntity
		  reviewshttpEntity = reviewsResponse.getEntity(); String reviewsJsonString =
		  EntityUtils.toString(reviewshttpEntity);
		  
		  JSONObject reviewdata = new JSONObject(reviewsJsonString);
		  System.out.println(reviewdata);
		  org.json.JSONArray reviews =  reviewdata.getJSONArray("reviews");
		  
		  System.out.println("Review Array"+reviews);
		  List<Reviews> reviewsList = new Gson().fromJson(reviews.toString(), new TypeToken<ArrayList<Reviews>>(){}.getType());
		  
		  System.out.println(reviewsList);
		  
		/*
		 * String json = new Gson().toJson(reviewsList); System.out.println(json);
		 */
		  JSONObject js=new JSONObject();
		 js.put("name", reviewsList.get(0).getName());
		 js.put("name", reviewsList.get(0).getRating());
		 js.put("name", reviewsList.get(0).getImage_url());
		 js.put("name", reviewsList.get(0).getText());

return new ResponseEntity<Reviews>().ok().body(js);
		
	} 
    
     } 
    

     
	 

