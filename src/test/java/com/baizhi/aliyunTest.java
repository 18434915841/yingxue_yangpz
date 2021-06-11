package com.baizhi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import org.junit.jupiter.api.Test;

import java.util.List;

public class aliyunTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    String accessKeyId ="LTAI5tScYQxVjSLwprWbnauA";
    String accessKeySecret ="IxaCND2Ds6c35abLoOhyPeI60hfhai";
    String bucketName = "2010-test";
    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    @Test
    public void test(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId ="LTAI5tScYQxVjSLwprWbnauA";
        String accessKeySecret ="IxaCND2Ds6c35abLoOhyPeI60hfhai";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void createbuck(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId ="LTAI5tScYQxVjSLwprWbnauA";
        String accessKeySecret ="IxaCND2Ds6c35abLoOhyPeI60hfhai";
        String bucketName = "2010-test";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建存储空间。
        ossClient.createBucket(bucketName);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void count(){
// 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
