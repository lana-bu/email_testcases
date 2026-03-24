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
}
