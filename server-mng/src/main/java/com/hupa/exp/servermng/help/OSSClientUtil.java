package com.hupa.exp.servermng.help;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import com.hupa.exp.servermng.config.aliyunoss.OSSClientConfig;
import com.hupa.exp.servermng.enums.ImgExceptionCode;
import com.hupa.exp.servermng.exception.ImgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OSSClientUtil {

    @Autowired
    private OSSClientConfig ossClientConfig;
    public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
    //改成读配置了
//    // endpoint
//    private String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//    // accessKey
//    private String accessKeyId = "LTAI4FwGXXjNbNhYwtDzk9kD";
//    private String accessKeySecret = "wQPEZJzzrpcYQEorAbASop3AWZ4i8j";
//    // 空间
//    private String bucketName = "gte-images";
//    // 文件存储目录
//    private String filedir = "information/";

    private OSSClient ossClient;

//    public OSSClientUtil() {
//        ossClient = new OSSClient(ossClientConfig.getEndpoint(),ossClientConfig.getAccessKeyId(),ossClientConfig.getAccessKeySecret());
//    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(ossClientConfig.getEndpoint(),ossClientConfig.getAccessKeyId(),ossClientConfig.getAccessKeySecret());
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     *
     * @param url
     * @throws ImgException
     */
    public void uploadImg2Oss(String url) throws ImgException {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
        }
    }

    public String uploadImg2Oss(MultipartFile file) throws ImgException {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
        }
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        System.out.println(fileUrl);
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(ossClientConfig.getFiledir() + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 根据key删除OSS服务器上的文件
//     * @param ossClient  oss连接
//     * @param bucketName  存储空间
//     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public void deleteFile(String key){
        ossClient.deleteObject(ossClientConfig.getBucketName(), ossClientConfig.getFiledir() + key);
        logger.info("删除" + ossClientConfig.getBucketName() + "下的文件" + ossClientConfig.getFiledir() + key + "成功");
    }

    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     *
     * @param instream
     *            文件流
     * @param fileName
     *            文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(ossClientConfig.getBucketName(), ossClientConfig.getFiledir() + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param
     *
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10

        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(ossClientConfig.getBucketName(), key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


}
