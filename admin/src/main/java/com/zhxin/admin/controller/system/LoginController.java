package com.zhxin.admin.controller.system;

import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName LoginController
 * @Description //登录模块
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/14 0014 下午 5:12
 **/
@Controller
public class LoginController extends BaseController {

    @RequestMapping("/")
    public RedirectView loginPage() {

        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String login(){
        Authentication auth = UserUtil.getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/index";
        }
        return "/login";
    }

}
