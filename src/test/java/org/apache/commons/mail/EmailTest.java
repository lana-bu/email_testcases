package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	
	private static final String[] TEST_EMAILS = { "ab@bc.com", "a.b@c.org", 
			"abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd" };
	
//	private String[] testValidChars = { "", "a", "A", "\uc5ec", "0123456789", 
//			"012345678901234567890", "\n" };
	
	private EmailStub email;
	
	@Before
	public void setUpEmailTest() throws Exception {
		email = new EmailStub();
	}
	
	@After
	public void tearDownEmailTest() throws Exception {
		
	}
	
	/*
	 * Test addBcc(String email) function
	 */
	@Test
	public void testAddBcc() throws Exception {
		email.addBcc(TEST_EMAILS);
		
		assertEquals(3, email.getBccAddresses().size());
		
		email.addBcc(); // invalid address list should throw exception
	}
	
	/*
	 * Test addCc(String email) function
	 */
	@Test
	public void testAddCc() throws Exception {
		email.addCc("test.email@abc.com");
		
		assertEquals(1, email.getCcAddresses().size());
	}
	
	/*
	 * Test addHeader(String name, String value) function
	 */
	@Test
	public void testAddHeader() throws Exception {
		email.addHeader("First Last", "My Header");
		
		email.addHeader("My Name", "");
	}
	
	/*
	 * Test addReplyTo(String email, String name) function
	 */
	@Test
	public void testAddReplyTo() throws Exception {
		email.addReplyTo("fakeemail@domain.net", "Fake Name");
		
		assertEquals(1, email.getReplyToAddresses().size());
	}
	
//
//	Email   addReplyTo(String email, String name)
//
//	void     buildMimeMessage()
//
//	String  getHostName()
//
//	Session getMailSession()
//
//	Date    getSentDate()
//
//	int        getSocketConnectionTimeout()
//
//	Email   setFrom(String email)
}
