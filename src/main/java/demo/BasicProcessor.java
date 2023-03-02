package demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;

import demo.client.AwsS3Client;
import demo.csv.CommonCsvProcessor;
import demo.file.CommonFileProcessor;
import demo.mail.AwsSesSmtpClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

public class BasicProcessor {

	public static void main(String[] args) {
		
		ResourceBundle resource = ResourceBundle.getBundle("application");
		System.out.println(resource.getString("s3.bucket.name"));

		demoArguments(args);

		demoEnvironmentVariables();

		//demoSimulateWork();
		
		demoS3ListBucket();
		
		demoS3ListObject(resource);
		
		demoS3GetObject(resource);
		
		//CommonFileProcessor.demoReadFileLineByLine("data/input.txt");
		
		//CommonXmlProcessor.demoParseXmlFile("data/input.txt");
		/*{
			List<Invoice> invoiceList = CommonXmlProcessor.demoParseXmlFile("data/output-xml.txt", Invoice.class);
			for (Invoice invoice : invoiceList) {
				System.out.println(invoice);
			}
		}*/
		/*{
			List<FSHINV01> invoiceList = CommonXmlProcessor.demoParseXmlFile("data/output-xml.txt", FSHINV01.class);
			for (FSHINV01 invoice : invoiceList) {
				System.out.println(invoice);
			}
		}*/

		CommonFileProcessor.demoWriteFileLineByLine("output.txt", Arrays.asList("1", "2", "漢字"));
		
		CommonCsvProcessor.writeLineByLine("output-csv.txt", Arrays.asList(
				new String[] {"1", "2", "漢字"}, new String[] {"1,2,3,A", "2\"'", "漢字"}));
		
		//demoSmtpEmail();

	}
	
	private static void demoArguments(String[] args) {
		
		System.out.printf("Args.Length: %s %n", args.length);
		for (String arg : args) {
			System.out.printf("Arg[x]: %s %n", arg);
		}

	}
	
	private static void demoEnvironmentVariables() {
		
		System.out.printf("Env.APP_ENV: %s %n", System.getenv("APP_ENV"));
		
	}
	
	private static void demoSmtpEmail() {
		AwsSesSmtpClient smtp = new AwsSesSmtpClient("xxx", "yyyy", "email-smtp.us-west-1.amazonaws.com");
		smtp.sendMessage("mail:from", "mail:to", 
				"test-subject-with-attachment", 
				"<html><body>See attachment.</body></html>", 
				new File("output-csv.txt"));
	}
	
	private static void demoSimulateWork() {
		
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Foo " + i);
				Thread.sleep(1000);
				System.out.println("Bar " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	private static void demoS3ListBucket() {
		
		String awsRegion = System.getenv("APP_AWS_REGION");
		
		if (awsRegion == null || awsRegion.isBlank()) {
			System.out.printf("Skip Listing Buckets... %n");
			return;
		}

		try {
			
			System.out.printf("Listing Buckets: Region=%s %n", awsRegion);
	
			AwsS3Client s3 = newS3ClientInstance();
			
			s3.listBucket();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static void demoS3ListObject(ResourceBundle resource) {
		
		String awsRegion = System.getenv("APP_AWS_REGION");
		
		if (awsRegion == null || awsRegion.isBlank()) {
			System.out.printf("Skip Listing Objects... %n");
			return;
		}

		try {
			
			String bucket = resource.getString("s3.bucket.name");
			System.out.printf("Listing Object: Region=%s Bucket=%s %n", awsRegion, bucket);
	
			AwsS3Client s3 = newS3ClientInstance();
			
			s3.listObject(bucket);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static void demoS3GetObject(ResourceBundle resource) {
		
		String awsRegion = System.getenv("APP_AWS_REGION");
		
		if (awsRegion == null || awsRegion.isBlank()) {
			System.out.printf("Skip Get Object... %n");
			return;
		}

		try {
			
			String key = resource.getString("s3.bucket.object.name");
			String bucket = resource.getString("s3.bucket.name");
			System.out.printf("Get Object: Region=%s Bucket=%s Key=%s %n", awsRegion, bucket, key);
	
			AwsS3Client s3 = newS3ClientInstance();
			
			Path path = Paths.get("data", key);
			
			Files.deleteIfExists(path);
			
			s3.getObject(bucket, key, path);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static AwsS3Client newS3ClientInstance() {
		String awsKey = System.getenv("APP_AWS_KEY");
		String awsSecret = System.getenv("APP_AWS_SECRET");
		String awsRegion = System.getenv("APP_AWS_REGION");
		Region region = Region.of(awsRegion);
		AwsS3Client s3 = null;
		if (awsKey != null && awsSecret != null) {
			AwsCredentials credentials = AwsBasicCredentials.create(awsKey, awsSecret); 
			AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
			s3 = new AwsS3Client(credentialsProvider, region);
		} else {
			s3 = new AwsS3Client(region);
		}
		return s3;
	}

}
