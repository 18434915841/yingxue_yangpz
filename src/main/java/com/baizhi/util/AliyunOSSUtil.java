package com.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AliyunOSSUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";

    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
    // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
   private static String accessKeyId ="LTAI5tScYQxVjSLwprWbnauA";
   private static String accessKeySecret ="IxaCND2Ds6c35abLoOhyPeI60hfhai";


    /**
     *将本地文件上传至阿里云
     *  参数:
     *  bucketName:指定存储空间名
     *  fileName:文件名
     *  localPath:本地文件路径
    * */
    public static void uploadlocalFileAliyun(String bucketName,String fileName,String localPath){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 填写Bucket名称、Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(localPath));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     *将文件以转为字节数组上传至阿里云
     *  参数:
     *  bucketName(String):指定存储空间名
     *  fileName(String):文件名
     *  headImg(MultipartFile):MultipartFile类型的文件
     * */
    public static void uploadByteFileAliyun(String bucketName, String objectName, MultipartFile headImg){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Byte数组。

        byte[] bytes = new byte[0];
        try {
            bytes = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    public static void main(String[] args) {
        String bucketName="yingx-2010"; //指定存储空间名
        String fileName="video/1.jpg"; //文件名
        String localPath="C:\\Users\\Administrator\\Desktop\\video\\1.jpg";    //本地文件路径
        uploadlocalFileAliyun(bucketName,fileName,localPath);
    }
}
