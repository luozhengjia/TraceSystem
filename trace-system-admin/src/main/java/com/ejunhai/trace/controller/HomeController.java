package com.ejunhai.trace.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.trace.common.errors.ErrorType;
import com.ejunhai.trace.common.utils.DateUtil;
import com.ejunhai.trace.common.utils.PropertyConfigurer;
import com.ejunhai.trace.product.dto.ProductAccessLogDto;
import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.dto.ProductTraceCodeDto;
import com.ejunhai.trace.product.service.ProductAccessLogService;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductInfoService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;
import com.ejunhai.trace.utils.FrontUtil;
import com.ejunhai.trace.utils.SessionManager;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

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
    private ProductAccessLogService productAccessLogService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Integer merchantId = SessionManager.get(request).getMerchantId();

        // 1.产品数量
        ProductInfoDto productInfoDto = new ProductInfoDto();
        productInfoDto.setMerchantId(merchantId);
        Integer productCount = productInfoService.queryProductInfoCount(productInfoDto);

        // 2.产品批次数
        ProductBatchDto productBatchDto = new ProductBatchDto();
        productBatchDto.setMerchantId(merchantId);
        Integer batchCount = productBatchService.queryProductBatchCount(productBatchDto);

        // 3.溯源码总数
        ProductTraceCodeDto productTraceCodeDto = new ProductTraceCodeDto();
        productTraceCodeDto.setMerchantId(merchantId);
        Integer traceCodeCount = productTraceCodeService.queryProductTraceCodeCount(productTraceCodeDto);

        // 4.访问总次数
        ProductAccessLogDto productAccessLogDto = new ProductAccessLogDto();
        productAccessLogDto.setMerchantId(merchantId);
        Integer accessCount = productAccessLogService.queryProductAccessLogCount(productAccessLogDto);

        // 5.访问趋势
        Date endTime = new Date();
        Date startTime = DateUtil.diffDate(endTime, 30);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] dates = new String[] {};
        for (int i = 0; i < 30; i++) {
            dates[i] = simpleDateFormat.format(DateUtil.addDate(startTime, i));
        }
        List<Map<String, Object>> resultList = productAccessLogService.getProductAccessLogStatList(merchantId, startTime, endTime);

        modelMap.put("productCount", productCount);
        modelMap.put("batchCount", batchCount);
        modelMap.put("traceCodeCount", traceCodeCount);
        modelMap.put("accessCount", accessCount);
        modelMap.put("dates", dates);
        modelMap.put("resultList", resultList);
        return "index";
    }

    @RequestMapping("getUptoken")
    @ResponseBody
    public String getUptoken(HttpServletRequest request, ModelMap modelMap) throws Exception {
        String bucketName = PropertyConfigurer.getContextProperty("qiniu.bucket.name");
        String accessKey = PropertyConfigurer.getContextProperty("qiniu.access.key");
        String secretKey = PropertyConfigurer.getContextProperty("qiniu.secret.key");

        Mac mac = new Mac(accessKey, secretKey);
        PutPolicy putPolicy = new PutPolicy(bucketName);
        return "{ \"uptoken\": \"" + putPolicy.token(mac) + "\" }";
    }

    @RequestMapping("/forbidden")
    public String forbidden(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理异步请求-重定向到统一异常处理
        if (FrontUtil.isAjax(request)) {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Integer errorCode = ErrorType.SYSTEM_FORBIDDEN.getValue();
            String errorMsg = ErrorType.SYSTEM_FORBIDDEN.getTitle();
            writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
            writer.flush();
            return null;
        }

        return "errors/error-403";
    }
}
