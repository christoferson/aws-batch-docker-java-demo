package demo;

import demo.client.AwsS3Client;
import software.amazon.awssdk.regions.Region;

public class BasicProcessor {

	public static void main(String[] args) {
		System.out.printf("Args.Length: %s %n", args.length);
		for (String arg : args) {
			System.out.printf("Arg[x]: %s %n", arg);
		}
		System.out.printf("Env.APP_ENV: %s %n", System.getenv("APP_ENV"));
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Foo " + i);
				Thread.sleep(1000);
				System.out.println("Bar " + i);
			}
			
			process();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private static void process() {

		String awsRegion = System.getenv("APP_AWS_REGION");
		
		Region region = (awsRegion == null)? Region.US_EAST_1 : Region.of(awsRegion);

		AwsS3Client s3 = new AwsS3Client(region);
		
		s3.list();
		
	}

}
