//package com.cdspool.main.shared;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Service
//public class AmazonSES {
//	
//	@Autowired
//	private JavaMailSender javaMailSender;
//
////
////	// ESTA DIRECCION DEBE SER VERIFICADA CON AMAZON SES.
////	static final String FROM = "infoyacayo@gmail.com";
////	
////	// ASUNTO DEL MENSAJE A MOSTRAR
////	static final String SUBJECT = "Recuperación";
////	
////	final String PASSWORD_RESET_SUBJECT = "Solicitud de restablecimiento de contraseña";
////	
////	// CUERPO DEL MENSAJE EN HTML
////	
////	final String PASSWORD_RESET_HTMLBODY = "<h1>A request to reset your password</h1>"
////		      + "<p>Hi!</p> "
////		      + "<p>Someone has requested to reset your password with our project. If it were not you, please ignore it."
////		      + " otherwise please click on the link below to set a new password: " 
////		      + "<a href='http://localhost:8080/verification-service/password-reset.html?token=$tokenValue'>"
////		      + " Click this link to Reset Password"
////		      + "</a><br/><br/>"
////		      + "Thank you!";
////	
////	final String PASSWORD_RESET_TEXTBODY = "A request to reset your password "
////		      + "Hi! "
////		      + "Someone has requested to reset your password with our project. If it were not you, please ignore it."
////		      + " otherwise please open the link below in your browser window to set a new password:" 
////		      + " http://localhost:8080/verification-service/password-reset.html?token=$tokenValue"
////		      + " Thank you!";
////	
////	
////	public void verifyEmail(UserDto userDto) {
////		
//////		BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
//////		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//////		                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//////		                        .build();
////		
////		System.setProperty("aws.accessKeyId", "<nedgardo.portillo@gmail.com>");
//////		System.setProperty("aws.secretKey", "<uj48uFTqqjg63C3b5Mh4xqlEoGbLgnU9il0gPmBU>");
////		AmazonSimpleEmailService client = AmazonSimpleEmailServiceAsyncClientBuilder.standard().withRegion(Regions.CA_CENTRAL_1).build();
////		
//////		String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
//////		String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
////		
//////		SendEmailRequest request = new SendEmailRequest()
//////				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
//////				.withMessage(new Message()
//////						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
//////								.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
//////						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
//////				.withSource(FROM);
//////		client.sendEmail(request);
////		
////		System.out.println("Email sent!");
////	}
//	
//	@GetMapping
//	public void sendPasswordResetRequest(String email, String token) throws MessagingException {
//		MimeMessage msg = javaMailSender.createMimeMessage();
//
//		// true = multipart message
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//
//		helper.setTo(email);
//
//		helper.setSubject("Solicitud de restablecimiento de contraseña");
//
//		// default = text/plain
//		// helper.setText("Check attachment for image!");
//
//		// true = text/html
//		helper.setText("Ingrese aqui: <a href='#'>"+token+"</a>", true);
//
//		// hard coded a file path
//		// FileSystemResource file = new FileSystemResource(new
//		// File("C:\\Users\\david.poncefgkss\\Desktop\\Bolsa CDS\\map-marker.png"));
//
////        helper.addAttachment("my_photo.png", new ClassPathResource("map-marker.png"));
//
//		javaMailSender.send(msg);
//	}
//	
//	
//}
