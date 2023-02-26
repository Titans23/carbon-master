package com.xxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.mapper.CompanyMapper;
import com.xxx.model.Company;
import com.xxx.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper,Company> implements CompanyService {
}
