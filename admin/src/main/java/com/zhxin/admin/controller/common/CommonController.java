package com.zhxin.admin.controller.common;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.RedisUtil;
import com.zhxin.common.utils.VerifyGenUtil;
import com.zhxin.common.utils.file.FileUploadUtil;
import com.zhxin.common.utils.sign.Base64;
import com.zhxin.core.web.model.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CommonController
 * @Description //通用类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/21 0021 上午 8:55
 **/
@RestController
public class CommonController {

    @Autowired
    RedisUtil redisUtil;

    @Value("${easyadmin.baseUrl}")
    String fileBaseUrl;

    @GetMapping("/captcha")
    public ResponseVo makeCaptcha() throws IOException {

        // 验证码字符串生成
        String verifyCode = VerifyGenUtil.generateVerifyCode(4);

        // uuid redis_key
        String uuid = EasyUtil.uuid(16);
        String verifyRedisKey = EasyConstants.CAPTCHA_CODE_KEY + uuid;

        redisUtil.setCacheObject(verifyRedisKey, verifyCode, EasyConstants.CAPTCHA_EXPIRES_IN, TimeUnit.MINUTES);

        // 生成验证码图片
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyGenUtil.outputImage(EasyConstants.DEFAULT_CAPTCHA_WIDTH,EasyConstants.DEFAULT_CAPTCHA_HEIGHT,stream, verifyCode);
        try{
            ResponseVo result = ResponseVo.success();
            result.put("uuid", uuid);
            result.put("img", Base64.encode(stream.toByteArray()));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVo.error(e.getMessage());
        }finally {
            stream.close();
        }
    }

    @PostMapping("/common/upload")
    @ResponseBody
    @ApiOperation(value = "通用文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName",value = "文件名",required = true),
            @ApiImplicitParam(name = "delete",value = "是否删除临时文件",required = true,dataType ="boolean")
    })
    public ResponseVo uploadFile(MultipartFile file){
        try {
            // 上传并返回新文件名称
            String fileName = FileUploadUtil.upload(file);
            String url = fileBaseUrl + "/" + fileName;
            ResponseVo result = ResponseVo.success();
            result.put("fileName", fileName);
            result.put("url", url);
            return result;
        } catch (Exception e) {
            return ResponseVo.error(e.getMessage());
        }
    }

}
