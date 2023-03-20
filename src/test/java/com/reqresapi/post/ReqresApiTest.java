package com.reqresapi.post;

import static com.reqresapi.post.AppConstant.REQRES_API_GET;
import static com.reqresapi.post.AppConstant.REQRES_API_GET_BY_ID;
import static com.reqresapi.post.AppConstant.REQRES_API_POST;
import static com.reqresapi.post.AppConstant.REQRES_API_POST_REGISTER;
import static com.reqresapi.post.AppConstant.REQRES_API_POST_LOGIN;
import static com.reqresapi.post.AppConstant.REQRES_API_GET_USER_LIST;
import static com.reqresapi.post.AppConstant.REQRES_API_GET_USER_SINGLE;
import static com.reqresapi.post.AppConstant.REQRES_API_GET_SINGLE_USER_NOTF;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
public class ReqresApiTest {

	@Test
	public void getRequestStatusTest() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET).asString();
		assertEquals(response.getStatus(), 200);
	}

	@Test
	public void getRequestBodyIdTest() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET).asString();
		String b = response.getBody();
		assertTrue(b.contains("id"));

	}

	@Test
	public void getRequestBodyFname() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET).asString();
		String b = response.getBody();
		assertTrue(b.contains("first_name"));

	}

	@Test
	public void getRequestBodyNameTest() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET).asString();
		String b = response.getBody();
		assertTrue(b.contains("name"));

	}
	@Test
	public void getRequestStatusTestSingleUser() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET_USER_SINGLE).asString();
		assertEquals(response.getStatus(), 200);
	}
	@Test
	public void getRequestBodyIdTestSingleUser() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET_USER_SINGLE).asString();
		String b = response.getBody();
		assertTrue(b.contains("id"));

	}

	@Test
	public void getRequestBodyLnameSingleUser() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET_USER_SINGLE).asString();
		String b = response.getBody();
		assertTrue(b.contains("last_name"));

	}
	@Test
	public void getRequestStatusTestNotf() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_GET_SINGLE_USER_NOTF).asString();
		assertEquals(response.getStatus(), 200);//402 is valid for this request
	}

	@Test
	public void getHeaderContantTypeTest() throws Exception {

		HttpResponse<String> response = Unirest.get(REQRES_API_POST).asString();
		Headers obj = response.getHeaders();
		String con = obj.getFirst("Content-Type");
		assertEquals(con.split(";")[0], "application/json");

	}

	@Test
	public void postRequestBodyStatusTest() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(REQRES_API_POST).header("Content-Type", "application/json")
				.body("{\"name\": \"morpheus\",\"job\": \"leader\"}").asJson();
		assertEquals(response.getStatus(), 201);
	}

	@Test
	public void postRequestRegisterUserStatus() throws Exception {
		HttpResponse<String> response = Unirest.get(REQRES_API_POST_REGISTER).asString();
		assertEquals(response.getStatus(), 200);

	}

	@Test
	public void postrequestLogin() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(REQRES_API_POST_LOGIN).header("Content-Type", "application/json")
				.body(" { \"email100\": \"eve.holt@reqres.in\",\"password\": \"cityslickaa\"}").asJson();
		assertEquals(response.getStatus(), 201);
	}

	@Test
	public void postLoginUserUnsuc() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(REQRES_API_POST_LOGIN).header("Content-Type", "application/json")
				.body("{\"email\": \"peter@klaven\"}").asJson();
		assertEquals(response.getStatus(), 400);

	}
	@Test
	public void putRequestUpdate() throws UnirestException {
		HttpResponse<String> response =Unirest.put(REQRES_API_GET_BY_ID)
				.body("{\"name\": \"morpheus\",\"job\": \"zion resident\"}").asString();
		assertEquals(response.getStatus(), 200);
		}
	@Test
	public void getRequestUserListResource() throws UnirestException{
		HttpResponse<String> response = Unirest.get(REQRES_API_GET_USER_LIST).asString();
		String obj =response.getBody();
		assertTrue(obj.contains("total_pages"));
		
	}
	@Test
	public void getRequestSingleUser() throws UnirestException{
		HttpResponse<String> response = Unirest.get(REQRES_API_GET_USER_SINGLE).asString();
		String b=response.getBody();
 		assertTrue(b.contains("color"));
 		assertEquals(response.getStatus(), 200);
		
	}
}
