package s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public class ListBuckets {

    public static void main(String[] args) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        List<Bucket> bucketList = s3.listBuckets();
        bucketList.stream().forEach(k -> System.out.println(k.getName()));

    }
}
