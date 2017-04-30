package com.ejunhai.trace.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.trace.common.base.BaseController;
import com.ejunhai.trace.common.base.Pagination;
import com.ejunhai.trace.common.errors.JunhaiAssert;
import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.model.ProductInfo;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductInfoService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;
import com.ejunhai.trace.utils.SessionManager;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private ProductBatchService productBatchService;

    @Resource
    private ProductTraceCodeService productTraceCodeService;

    @RequestMapping("/productInfoList")
    public String productInfoList(HttpServletRequest request, ProductInfoDto productInfoDto, ModelMap modelMap) {
        productInfoDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = productInfoService.queryProductInfoCount(productInfoDto);
        Pagination pagination = new Pagination(productInfoDto.getPageNo(), iCount);

        // 获取分页数据
        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
        if (iCount > 0) {
            productInfoDto.setOffset(pagination.getOffset());
            productInfoDto.setPageSize(pagination.getPageSize());
            productInfoList = productInfoService.queryProductInfoList(productInfoDto);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("productInfoDto", productInfoDto);
        modelMap.put("productInfoList", productInfoList);
        return "product/productInfoList";
    }

    @RequestMapping("/toProductInfo")
    public String toProductInfo(HttpServletRequest request, ProductInfo productInfo, ModelMap modelMap) {
        if (productInfo.getId() != null) {
            productInfo = productInfoService.read(productInfo.getId());
        }

        // 新增或编辑信息
        modelMap.put("productInfo", productInfo);
        return "product/productInfoEdit";
    }

    @RequestMapping("/saveProductInfo")
    @ResponseBody
    public String saveProductInfo(HttpServletRequest request, ProductInfoDto productInfoDto) {
        JunhaiAssert.notBlank(productInfoDto.getProductName(), "产品名称不能为空");

        Integer merchantId = SessionManager.get(request).getMerchantId();
        ProductInfo productInfo = new ProductInfo();
        if (productInfoDto.getId() != null) {
            productInfo = productInfoService.read(productInfoDto.getId());
            JunhaiAssert.isTrue(productInfo != null, "无效基地ID");
            JunhaiAssert.isTrue(productInfo.getMerchantId() == merchantId, "非法操作");
        } else {
            productInfo.setMerchantId(merchantId);
        }

        productInfo.setProductName(productInfoDto.getProductName());
        productInfo.setLogoUrl(productInfoDto.getLogoUrl());
        productInfo.setBrandName(productInfoDto.getBrandName());
        productInfo.setPicUrls(productInfoDto.getPicUrls());
        productInfo.setRemark(productInfoDto.getRemark());
        productInfoService.save(productInfo);
        return jsonSuccess();
    }

    @RequestMapping("/deleteProductInfo")
    @ResponseBody
    public String deleteProductInfo(HttpServletRequest request, ProductInfoDto productInfoDto) {
        JunhaiAssert.notNull(productInfoDto.getId(), "id不能为空");
        productInfoService.delete(productInfoDto.getId());
        return jsonSuccess();
    }

    @RequestMapping("/importProductInfo")
    @ResponseBody
    public String importProductInfo(HttpServletRequest request, ProductInfoDto productInfoDto) {
        JunhaiAssert.notNull(productInfoDto.getId(), "id不能为空");
        productInfoService.delete(productInfoDto.getId());
        return jsonSuccess();
    }

}
