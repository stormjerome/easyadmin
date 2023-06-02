package com.zhxin.common.utils.file;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.zhxin.common.config.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName AliYunOSSUtil
 * @Description //阿里云OSS工具类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/25 0025 上午 11:52
 **/
@Service
public class AliYunOSSUtil {

    @Autowired
    private OssProperties ossProperties;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 上传
     * @param file
     * @return
     */
    public  String upload(MultipartFile file){

        String endPoint = ossProperties.getEndPoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret =ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();
        String fileDir = ossProperties.getFileDir();

        String dateStr = format.format(new Date());

        if(null == file) return null;

        OSSClient ossClient = new OSSClient(endPoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            //创建文件路径
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".")); // .jpg

            String fileUrl = fileDir +"/" + dateStr + "/"
                    + UUID.randomUUID().toString().replace("-","") + suffix;

            InputStream instream = file.getInputStream();

            //上传文件
            PutObjectResult result = ossClient.putObject(bucketName, fileUrl, instream);
            //PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, instream));
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if(null != result) return fileUrl;
        }catch (OSSException | ClientException | IOException oe){
            //
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }


    /**
     * 删除
     * @param fileKey
     * @return
     */
    public  String deleteFile(String fileKey){

        String endPoint= ossProperties.getEndPoint();
        String accessKeyId= ossProperties.getKeyId();
        String accessKeySecret=ossProperties.getKeySecret();
        String bucketName=ossProperties.getBucketName();

        try {
            OSSClient ossClient = new OSSClient(endPoint,accessKeyId,accessKeySecret);

            if(!ossClient.doesBucketExist(bucketName)){
                return "您的Bucket不存在";
            } else {
                ossClient.deleteObject(bucketName,fileKey);
                return "文件删除成功："+fileKey;
            }
        }catch (Exception ex){
            return "文件删除失败";
        }
    }

    /**
     * 查询文件名列表
     * @param bucketName
     * @return
     */
    public List<String> getObjectList(String bucketName,String fileDir){
        List<String> listRe = new ArrayList<>();
        String endPoint= ossProperties.getEndPoint();
        String accessKeyId= ossProperties.getKeyId();
        String accessKeySecret=ossProperties.getKeySecret();

        try {

            OSSClient ossClient = new OSSClient(endPoint,accessKeyId,accessKeySecret);
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
            //列出fileDir文件
            listObjectsRequest.setPrefix(fileDir + "/" +format.format(new Date())+"/");
            ObjectListing list = ossClient.listObjects(listObjectsRequest);
            for(OSSObjectSummary objectSummary : list.getObjectSummaries()){
                System.out.println(objectSummary.getKey());
                listRe.add(objectSummary.getKey());
            }
            return listRe;
        }catch (Exception ex){

            return new ArrayList<>();
        }
    }

}
