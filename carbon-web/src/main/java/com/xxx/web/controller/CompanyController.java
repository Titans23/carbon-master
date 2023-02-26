package com.xxx.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxx.common.BaseContext;
import com.xxx.common.JwtUtils;
import com.xxx.common.Resp;
import com.xxx.model.Company;
import com.xxx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 企业注册
     * @param company
     * @return
     */
    @PostMapping("/register")
    public Resp<String> register(@RequestBody Company company){
        // 判断注册的公司、用户名是否已经存在
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Company::getCompany_name,company.getCompany_name());
        wrapper.or().eq(Company::getUsername,company.getUsername());

        Company c = companyService.getOne(wrapper);

        if (c == null){
            // 密码进行md5加密
            company.setPassword(DigestUtils.md5DigestAsHex(company.getPassword().getBytes()));
            companyService.save(company);
            return Resp.success("注册成功");
        }
        return Resp.error("用户名或公司名已注册");
    }

    /**
     * 企业登录
     * @param company
     * @return
     */
    @PostMapping("/login")
    public Resp<Company> login(HttpServletRequest request, @RequestBody Company company){
        //获取密码进行md5加密
        String password = company.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //判断密码是否正确
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Company::getUsername,company.getUsername()).
                or().eq(Company::getCompany_name,company.getCompany_name());
        Company one = companyService.getOne(wrapper);
        if (one == null){
            return Resp.error("该账号不存在");
        }
        if(!one.getPassword().equals(password)){
            return Resp.error("密码错误");
        }

        HashMap<String, String> payload = new HashMap<>();
        payload.put("id",one.getId().toString());
        payload.put("companyName",one.getCompany_name());
        payload.put("role","company");
        String token = JwtUtils.createToken(payload);
        // 登录后设置全局id.
        BaseContext.setCurrentId(one.getId());

        one.setPassword(null);
        Resp<Company> success = Resp.success(one);
        success.add("token",token);
        return success;
    }

    @PostMapping("/other")
    public Resp<String> other(){

        return Resp.success("other request");
    }

}
