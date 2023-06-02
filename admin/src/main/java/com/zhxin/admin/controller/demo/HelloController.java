package com.zhxin.admin.controller.demo;

import com.zhxin.common.page.TableDataInfo;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.core.security.LoginRequest;
import com.zhxin.core.web.controller.BaseController;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.service.ISysAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HelloController
 * @Description //TODO 测试用
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/14 0014 下午 3:42
 **/
@RestController
public class HelloController extends BaseController {

    @Autowired
    ISysAdminUserService adminUserService;

//    @GetMapping("/hello")
//    public TableDataInfo hello(SysAdminUser user){
//
//        startPage();
//        List<SysAdminUser> list = adminUserService.selectUserList(user);
//        return getDataTable(list);
//
//    }

    @GetMapping("/xxxx")
    public String pwd(){
        String pwd = EasyUtil.encryptPassword("admin123");
        return pwd;
    }
    @PostMapping("/xxx")
    public ResponseVo xxx(LoginRequest loginRequest){
        ResponseVo result = ResponseVo.success(loginRequest);
        return result;
    }
}
