package demo;

import java.util.Arrays;
import java.util.ResourceBundle;

import demo.client.AwsS3Client;
import demo.file.CommonFileProcessor;
import software.amazon.awssdk.regions.Region;

public class BasicProcessor {

	public static void main(String[] args) {
		
		ResourceBundle resource = ResourceBundle.getBundle("application");
		System.out.println(resource.getString("s3.bucket.name"));

		demoArguments(args);

		demoEnvironmentVariables();

		//demoSimulateWork();
		
		demoS3ListBucket();
		
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

			
			
			Region region = (awsRegion == null)? Region.US_EAST_1 : Region.of(awsRegion);
			
			System.out.printf("Listing Buckets: Region=%s %n", region);
	
			AwsS3Client s3 = new AwsS3Client(region);
			
			s3.list();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
