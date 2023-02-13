package demo.client;

import java.time.Duration;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

public class AwsS3Client {

	private S3Client client;
	
	public AwsS3Client(AwsCredentialsProvider credentialsProvider, Region region) {
		
		this.client = S3Client.builder()
				  .credentialsProvider(credentialsProvider)
				  .region(region)
				  .overrideConfiguration(builder -> builder
					.apiCallTimeout(Duration.ofMinutes(2))
					//.apiCallAttemptTimeout(/* 15 min */)
					//.retryPolicy(RetryMode.STANDARD)
					.build())
				  .build();
	}
	
	public void list() {
		ListBucketsRequest request = ListBucketsRequest.builder().build();
		ListBucketsResponse response = this.client.listBuckets(request);
		for (Bucket bucket : response.buckets()) {
			System.out.println(bucket.name());
		}
	}
	
}
