package com.ejunhai.trace.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejunhai.trace.common.errors.JunhaiAssert;
import com.ejunhai.trace.merchant.model.MerchantInfo;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;
import com.ejunhai.trace.merchant.service.MerchantService;
import com.ejunhai.trace.merchant.service.ProductionBaseInfoService;
import com.ejunhai.trace.product.model.ProductAccessLog;
import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.model.ProductInfo;
import com.ejunhai.trace.product.model.ProductTraceCode;
import com.ejunhai.trace.product.service.ProductAccessLogService;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductInfoService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;
import com.ejunhai.trace.utils.FrontUtil;

@Controller
@RequestMapping("")
public class HomeController {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private ProductBatchService productBatchService;

    @Resource
    private ProductTraceCodeService productTraceCodeService;

    @Resource
    private ProductionBaseInfoService productionBaseInfoService;

    @Resource
    private ProductAccessLogService productAccessLogService;

    @Resource
    private MerchantService merchantService;

    @RequestMapping("/qcode")
    public String qcode(HttpServletRequest request, String p, HttpServletResponse response, ModelMap modelMap) {
        ProductTraceCode productTraceCode = productTraceCodeService.getProductTraceCodeByCode(p);
        JunhaiAssert.notNull(productTraceCode, "无效溯源码");

        // 根据批次号获取批次信息
        ProductBatch productBatch = productBatchService.getProductBatchByBatchNo(productTraceCode.getBatchNo());

        // 获取产品信息
        ProductInfo productInfo = productInfoService.read(productBatch.getProductId());

        // 获取生产基地信息
        ProductionBaseInfo productionBaseInfo = productionBaseInfoService.read(productBatch.getBaseId());

        // 获取企业信息
        MerchantInfo merchantInfo = merchantService.read(productTraceCode.getMerchantId());

        // 记录访问日志
        productTraceCode.setAccessTimes(productTraceCode.getAccessTimes() + 1);
        productTraceCodeService.update(productTraceCode);
        ProductAccessLog productAccessLog = new ProductAccessLog();
        productAccessLog.setMerchantId(productTraceCode.getMerchantId());
        productAccessLog.setBatchNo(productTraceCode.getBatchNo());
        productAccessLog.setProductId(productBatch.getProductId());
        productAccessLog.setTraceCode(productTraceCode.getTraceCode());
        productAccessLog.setSourceIp(FrontUtil.getIpAddress(request));
        productAccessLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        productAccessLogService.insert(productAccessLog);

        modelMap.put("productTraceCode", productTraceCode);
        modelMap.put("productBatch", productBatch);
        modelMap.put("productInfo", productInfo);
        modelMap.put("productionBaseInfo", productionBaseInfo);
        modelMap.put("merchantInfo", merchantInfo);
        return "index";
    }
}
