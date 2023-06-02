package com.zhxin.admin.controller.system;

import com.google.common.base.Charsets;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.web.SystemInfoVo;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName IndexController
 * @Description //IndexController
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 下午 5:05
 **/
@Controller
public class IndexController extends BaseController {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/index")
    public String index(Model model){
        LoginUser loginUser = UserUtil.getLoginUser();


        model.addAttribute("userInfo",loginUser);
        return "/index";
    }

    @GetMapping("/main")
    public String main(ModelMap mmap){

        SystemInfoVo sysInfo = new SystemInfoVo();
        Properties props = System.getProperties();

        sysInfo.setHostIp(EasyUtil.getHostIp());
        sysInfo.setOsName(props.getProperty("os.name"));
        sysInfo.setProjectDir(props.getProperty("user.dir"));
        sysInfo.setJvmTotal(Runtime.getRuntime().totalMemory());
        sysInfo.setJvmMax(Runtime.getRuntime().maxMemory());
        sysInfo.setJvmFree(Runtime.getRuntime().freeMemory());
        sysInfo.setJdkVersion(props.getProperty("java.version"));
        sysInfo.setJavaHome(props.getProperty("java.home"));
        sysInfo.setJvmName(ManagementFactory.getRuntimeMXBean().getVmName());
        mmap.put("sysInfo",sysInfo);
        return "/system/main";
    }


    @GetMapping("/icons")
    public String icons(Model model) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("icon.txt");
        InputStream file = classPathResource.getInputStream();
        InputStreamReader in = new InputStreamReader(file, Charsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        String contentOfLine ;
        List<String> iconList = new ArrayList<>();
        while((contentOfLine = br.readLine()) != null){
            iconList.add(contentOfLine);
        }
        model.addAttribute("iconList",iconList);
        return "/system/index/icons";
    }


}
