package com.zhxin.common.utils.file;

import com.zhxin.common.constant.MimeTypeConstants;
import com.zhxin.common.exception.GlobalException;
import com.zhxin.common.utils.StringUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName FileUploadUtil
 * @Description //文件上传工具类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/25 0025 上午 11:52
 **/
public class FileUploadUtil {
    /**
     * 默认大小 50M
     */
    private static final long DEFAULT_MAX_SIZE = 52428800;

    /**
     * 默认的文件名最大长度 100
     */
    private static final int DEFAULT_FILE_NAME_LENGTH = 200;

    /**
     * 默认上传的地址
     */
    private static final String defaultBaseDir = "E:/easyadmin/upload";

    private static int counter = 0;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws IOException {
        try {
            return upload(defaultBaseDir, file, MimeTypeConstants.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            if(StringUtil.isEmpty(baseDir)) baseDir=defaultBaseDir;
            return upload(baseDir, file, MimeTypeConstants.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 返回上传成功的文件名
     * @throws IOException                          比如读写文件出错时
     */
    public static String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws IOException, GlobalException {

        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > DEFAULT_FILE_NAME_LENGTH) {
            throw new GlobalException("超出文件名长度限制");
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        return fileName;
    }

    private static String extractFilename(MultipartFile file) {

        String extension = getExtension(file);
        String filename = format.format(new Date()) + File.separator + UUID.randomUUID().toString().replace("-","") + "." + extension;
        return filename;
    }

    private static File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        File desc = new File(uploadDir + File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            if (desc.createNewFile()) {

            }
        }
        return desc;
    }



    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     */
    private static void assertAllowed(MultipartFile file, String[] allowedExtension) throws GlobalException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            throw new GlobalException("超出上传文件最大限制");
        }
        String filename = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeConstants.IMAGE_EXTENSION) {
                throw new GlobalException("不是图片文件");
            } else if (allowedExtension == MimeTypeConstants.FLASH_EXTENSION) {
                throw new GlobalException("不是flash文件");
            } else if (allowedExtension == MimeTypeConstants.MEDIA_EXTENSION) {
                throw new GlobalException("不是多媒体文件");
            } else {
                throw new GlobalException("非法格式文件");
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    private static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    private static String getExtension(MultipartFile file){
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtil.isEmpty(extension)){
            extension = MimeTypeConstants.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

}
