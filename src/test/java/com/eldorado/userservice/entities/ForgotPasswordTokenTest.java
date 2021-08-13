package com.eldorado.userservice.entities;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ForgotPasswordTokenTest {
	private ForgotPasswordToken token;
	
	@BeforeEach
	void setToken() {
		token = new ForgotPasswordToken();
		token.setTokenid(1L);
		token.setForgotPassToken("TestToken@123");
		token.setCreatedDateTime(LocalDateTime.now());
		token.setUser(null);
	}
	
	@Test
	void should_Get_TokenId() {
		//given
		Long tokenId = 1L;
		
		//when
		Long recievedTokenId = token.getTokenid();
		
		//then
		assertEquals(tokenId, recievedTokenId);
	}
	
	@Test
	void should_Get_ForgotPasswordToken() {
		//given
		String forgotPasswordToken = "TestToken@123";
		
		//when
		String recievedForgotPasswordToken = token.getForgotPassToken();
		
		//then
		assertEquals(forgotPasswordToken, recievedForgotPasswordToken);
	}
	
	@Test
	void should_Get_CreatedDateTime() {
		//given
		
		//when
		LocalDateTime tokenTime = token.getCreatedDateTime();
		
		//then
		assertNotNull(tokenTime);
	}
	
	@Test
	void should_Get_User() {
		//given
		
		//when
		
		//then
		assertNull(token.getUser());
	}
	
}
