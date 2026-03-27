package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
	
	/*
	 * Test buildMimeMessage() function
	 */
	@Test
	public void testBuildMimeMessageValid() throws Exception { // successfully builds a mime message
		// required to build a mime message
		email.setHostName("Host"); // valid hostname required
		email.setBounceAddress("bounce@email.com"); // From address required
		email.addTo("recipient.email@domain.org");// receiver address(es) required
		
		// optional add-ons (for more coverage)
		email.setFrom("professional789@random.org");
		email.setSubject("TestSubject");
		email.setCharset("utf-8");
		email.addCc(TEST_EMAILS);
		email.addBcc("jane.doe@fakeemail.net");
		email.addReplyTo("personal@emailme.com");
		email.addHeader("Mister Mango", "Professional Email Sender");
		//email.setPopBeforeSmtp(true, "smtp.ethereal.email", "testUsername", "testPassword");
		Object contentObject = new Object();
		email.setContent(contentObject, "business email");
		
		email.buildMimeMessage();
		
		assertEquals("TestSubject", email.getMimeMessage().getSubject());
		
		email.buildMimeMessage();
	}
	
	@Test
	public void testBuildMimeMessageInvalid() throws Exception { // throws exception before building mime message
		email.setHostName("Host");
		// no bounce address or to address set, so exception gets thrown
		
		email.buildMimeMessage();
	}
	
	/*
	 * Test getHostName() function
	 */
	@Test
	public void testGetHostName() throws Exception {
		assertEquals(null, email.getHostName());
		
		email.setHostName("Host");
		
		assertEquals("Host", email.getHostName());
	}
	
	/*
	 * Test getMailSession() function
	 */
	@Test
	public void testGetMailSession() throws Exception {
		email.setSSLOnConnect(true);
		email.setHostName("Host");
		
		assertEquals("Host", email.getMailSession().getProperties().getProperty("mail.smtp.host")); // value of MAIL_HOST in EmailConstants
	}
	
	/*
	 * Test getSentDate() function
	 */
	@Test
	public void testGetSentDate() throws Exception {
		assertEquals(new Date(), email.getSentDate());
		
		Date specificDate = new Date(2005 - 1900, 9, 31);
		email.setSentDate(specificDate);
		
		assertEquals(specificDate, email.getSentDate());
	}
	
	/*
	 * Test getSocketConnectionTimeout() function
	 */
	@Test
	public void testGetSocketConnectionTimeout() throws Exception {
		assertEquals(60000, email.getSocketConnectionTimeout()); // value of SOCKET_TIMEOUT_MS in EmailConstants
	}
	
	/*
	 * Test setFrom(String email) function
	 */
	@Test
	public void testSetFrom() throws Exception {
		email.setFrom("testing.email@notreal.org");
		
		assertEquals("testing.email@notreal.org", email.getFromAddress().getAddress()); // getAddress gets email as String from InternetAddress object returned by getFromAddress
	}

}
