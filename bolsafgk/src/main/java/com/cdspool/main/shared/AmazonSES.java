package com.cdspool.main.shared;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;


@Service
public class AmazonSES {

	// ESTA DIRECCION DEBE SER VERIFICADA CON AMAZON SES.
	final String FROM = "yacayo@gmail.com";
	
	// ASUNTO DEL MENSAJE A MOSTRAR
	final String SUBJECT = "";
	
	final String PASSWORD_RESET_SUBJECT = "Solicitud de restablecimiento de contraseña";
	
	// CUERPO DEL MENSAJE EN HTML
	
	final String HTMLBODY = "<h1>Please verify your email address</h1>"
			+ "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " click on the following link: "
			+ "<a href='http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue'>"
			+ "Final step to complete your registration" + "</a><br/><br/>"
			+ "Thank you! And we are waiting for you inside!";
	
	// El cuerpo del correo electrónico para destinatarios con clientes de correo electrónico no HTML.
	final String TEXTBODY = "Please verify your email address. "
			+ "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " open then the following URL in your browser window: "
			+ " http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue"
			+ " Thank you! And we are waiting for you inside!";
	
	final String PASSWORD_RESET_HTMLBODY = "<h1>A request to reset your password</h1>"
		      + "<p>Hi, $firstName!</p> "
		      + "<p>Someone has requested to reset your password with our project. If it were not you, please ignore it."
		      + " otherwise please click on the link below to set a new password: " 
		      + "<a href='http://localhost:8080/verification-service/password-reset.html?token=$tokenValue'>"
		      + " Click this link to Reset Password"
		      + "</a><br/><br/>"
		      + "Thank you!";
	
	final String PASSWORD_RESET_TEXTBODY = "A request to reset your password "
		      + "Hi, $firstName! "
		      + "Someone has requested to reset your password with our project. If it were not you, please ignore it."
		      + " otherwise please open the link below in your browser window to set a new password:" 
		      + " http://localhost:8080/verification-service/password-reset.html?token=$tokenValue"
		      + " Thank you!";
	
	public void verifyEmail(TemporalDTO temporalDto) {
		UserDto userDto = new UserDto();
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceAsyncClientBuilder.standard().withRegion(Regions.CA_CENTRAL_1).build();
		
		String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", temporalDto.getClavet());
		String textBodyWithToken = TEXTBODY.replace("$tokenValue", temporalDto.getClavet());
		
		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
								.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);
		client.sendEmail(request);
		
		System.out.println("Email sent!");
	}
	
	public boolean sendPasswordResetRequest(String email, String token) {
		boolean returnValue = false;
		
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceAsyncClientBuilder.standard().withRegion(Regions.CA_CENTRAL_1).build();
		
		String htmlBodyWithToken = PASSWORD_RESET_HTMLBODY.replace("$tokenValue", token);
			   
		String textBodyWithToken = PASSWORD_RESET_TEXTBODY.replace("$tokenValue", token);
		
		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(email))
				.withMessage(new Message()
						.withBody(new Body()
								.withHtml(new Content()
										.withCharset("UTF-8").withData(htmlBodyWithToken))
								.withText(new Content()
										.withCharset("UTF-8").withData(textBodyWithToken)))
						.withSubject(new Content()
								.withCharset("UTF-8").withData(PASSWORD_RESET_SUBJECT)))
				.withSource(FROM);
		
		SendEmailResult result = client.sendEmail(request);
		if (result != null && (result.getMessageId() != null && !result.getMessageId().isEmpty())) {
			returnValue =  true;
		}
		return returnValue;
	}
}
