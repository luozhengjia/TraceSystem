package com.ejunhai.trace.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.trace.common.base.BaseController;
import com.ejunhai.trace.common.base.Pagination;
import com.ejunhai.trace.common.errors.JunhaiAssert;
import com.ejunhai.trace.common.utils.DateUtil;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;
import com.ejunhai.trace.merchant.service.ProductionBaseInfoService;
import com.ejunhai.trace.merchant.utils.MerchantUtil;
import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.model.ProductInfo;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductInfoService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;
import com.ejunhai.trace.product.utils.ProductUtil;
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

    @Resource
    private ProductionBaseInfoService productionBaseInfoService;

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

    @RequestMapping("/productBatchList")
    public String productBatchList(HttpServletRequest request, ProductBatchDto productBatchDto, ModelMap modelMap) {
        productBatchDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = productBatchService.queryProductBatchCount(productBatchDto);
        Pagination pagination = new Pagination(productBatchDto.getPageNo(), iCount);

        // 获取分页数据
        List<ProductBatch> productBatchList = new ArrayList<ProductBatch>();
        if (iCount > 0) {
            productBatchDto.setOffset(pagination.getOffset());
            productBatchDto.setPageSize(pagination.getPageSize());
            productBatchList = productBatchService.queryProductBatchList(productBatchDto);

            // 获取产品ID-产品映射关系
            List<Integer> productIdList = ProductUtil.getProductIdList(productBatchList);
            List<ProductInfo> productInfoList = productInfoService.getProductInfoListByIds(productIdList);
            Map<String, ProductInfo> productInfoMap = ProductUtil.getProductInfoMap(productInfoList);
            modelMap.put("productInfoMap", productInfoMap);

            // 获取基地ID-基地映射关系
            List<Integer> baseIdList = ProductUtil.getBaseIdList(productBatchList);
            List<ProductionBaseInfo> productionBaseInfoList = productionBaseInfoService.getProductionBaseInfoListByIds(baseIdList);
            Map<String, ProductionBaseInfo> productionBaseInfoMap = MerchantUtil.getProductionBaseInfoMap(productionBaseInfoList);
            modelMap.put("productionBaseInfoMap", productionBaseInfoMap);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("productBatchDto", productBatchDto);
        modelMap.put("productBatchList", productBatchList);
        return "product/productBatchList";
    }

    @RequestMapping("/toProductBatch")
    public String toProductBatch(HttpServletRequest request, ProductBatch productBatch, ModelMap modelMap) {
        if (productBatch.getId() != null) {
            productBatch = productBatchService.read(productBatch.getId());
        } else {
            productBatch.setBatchNo(DateUtil.getCurrentDateTimeToStr());
        }

        // 获取产品列表
        Integer merchantId = SessionManager.get(request).getMerchantId();
        List<ProductInfo> productInfoList = productInfoService.getProductInfoListByMerchantId(merchantId);
        modelMap.put("productInfoList", productInfoList);

        // 获取基地列表
        List<ProductionBaseInfo> productionBaseInfoList = productionBaseInfoService.getProductionBaseInfoListByMerchantId(merchantId);
        modelMap.put("productionBaseInfoList", productionBaseInfoList);

        // 新增或编辑信息
        modelMap.put("productBatch", productBatch);
        return "product/productBatchEdit";
    }

    @RequestMapping("/saveProductBatch")
    @ResponseBody
    public String saveProductBatch(HttpServletRequest request, ProductBatchDto productBatchDto) {
        JunhaiAssert.notBlank(productBatchDto.getBatchNo(), "产品批次号不能为空");

        Integer merchantId = SessionManager.get(request).getMerchantId();
        ProductBatch productBatch = new ProductBatch();
        if (productBatchDto.getId() != null) {
            productBatch = productBatchService.read(productBatchDto.getId());
            JunhaiAssert.isTrue(productBatch != null, "无效基地ID");
            JunhaiAssert.isTrue(productBatch.getMerchantId() == merchantId, "非法操作");
        } else {
            productBatch.setMerchantId(merchantId);
            productBatch.setBatchNo(productBatchDto.getBatchNo());
        }

        productBatch.setProductId(productBatchDto.getProductId());
        productBatch.setBaseId(productBatchDto.getBaseId());
        productBatch.setProductionDate(productBatchDto.getProductionDate());
        productBatch.setExpireTime(productBatchDto.getExpireTime());
        productBatch.setIssueAmount(productBatchDto.getIssueAmount());
        productBatch.setHasIssueNum(0);
        productBatchService.save(productBatch);
        return jsonSuccess();
    }

    @RequestMapping("/deleteProductBatch")
    @ResponseBody
    public String deleteProductBatch(HttpServletRequest request, ProductBatchDto productBatchDto) {
        JunhaiAssert.notNull(productBatchDto.getId(), "id不能为空");
        productBatchService.delete(productBatchDto.getId());
        return jsonSuccess();
    }

    @RequestMapping("/exportTraceCodes")
    public String exportTraceCodes(HttpServletRequest request, ProductBatchDto productBatchDto) {
        JunhaiAssert.notNull(productBatchDto.getId(), "id不能为空");
        productBatchService.delete(productBatchDto.getId());
        return jsonSuccess();
    }

}
