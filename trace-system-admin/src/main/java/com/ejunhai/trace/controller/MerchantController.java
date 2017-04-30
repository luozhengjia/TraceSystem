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
import com.ejunhai.trace.merchant.dto.MerchantDto;
import com.ejunhai.trace.merchant.dto.ProductionBaseInfoDto;
import com.ejunhai.trace.merchant.model.MerchantInfo;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;
import com.ejunhai.trace.merchant.service.MerchantService;
import com.ejunhai.trace.merchant.service.ProductionBaseInfoService;
import com.ejunhai.trace.utils.SessionManager;

@Controller
@RequestMapping("merchant")
public class MerchantController extends BaseController {

    @Resource
    private MerchantService merchantService;

    @Resource
    private ProductionBaseInfoService productionBaseInfoService;

    @RequestMapping("/merchantList")
    public String merchantList(HttpServletRequest request, MerchantDto merchantDto, ModelMap modelMap) {
        Integer iCount = merchantService.queryMerchantCount(merchantDto);
        Pagination pagination = new Pagination(merchantDto.getPageNo(), iCount);

        List<MerchantInfo> merchantList = new ArrayList<MerchantInfo>();
        if (iCount > 0) {
            merchantDto.setOffset(pagination.getOffset());
            merchantDto.setPageSize(pagination.getPageSize());
            merchantList = merchantService.queryMerchantList(merchantDto);
        }

        modelMap.put("merchantDto", merchantDto);
        modelMap.put("merchantList", merchantList);
        modelMap.put("pagination", pagination);
        return "merchant/merchantList";
    }

    @RequestMapping("/merchantDetail")
    public String merchantDetail(HttpServletRequest request, MerchantInfo merchant, ModelMap modelMap) {
        if (merchant.getId() != null) {
            merchant = merchantService.read(merchant.getId());
        }

        // 新增或编辑商户信息
        modelMap.put("merchant", merchant);
        return merchant.getId() == null ? "merchant/merchantAdd" : "merchant/merchantEdit";
    }

    @RequestMapping("/addMerchant")
    @ResponseBody
    public String addMerchant(HttpServletRequest request, MerchantDto merchantDto) {
        JunhaiAssert.notBlank(merchantDto.getMerchantName(), "商户名称不能为空");

        MerchantInfo merchant = new MerchantInfo();
        merchant.setMerchantName(merchantDto.getMerchantName());
        merchant.setBusinessLine(merchantDto.getBusinessLine());
        merchant.setPicUrls(merchantDto.getPicUrls());
        merchant.setOrganization(merchantDto.getOrganization());
        merchant.setBusinessLicense(merchantDto.getBusinessLicense());
        merchant.setRecordNumber(merchantDto.getRecordNumber());
        merchant.setContacts(merchantDto.getContacts());
        merchant.setTelephone(merchantDto.getTelephone());
        merchant.setAddress(merchantDto.getAddress());
        merchantService.insert(merchant);
        return jsonSuccess();
    }

    @RequestMapping("/editMerchant")
    @ResponseBody
    public String editMerchant(HttpServletRequest request, MerchantDto merchantDto) {
        JunhaiAssert.notNull(merchantDto.getId(), "商户ID不能为空");
        JunhaiAssert.notBlank(merchantDto.getMerchantName(), "商户名称不能为空");
        MerchantInfo merchant = merchantService.read(merchantDto.getId());
        JunhaiAssert.notNull(merchant, "商户ID不合法");

        // 更新商户信息
        merchant.setMerchantName(merchantDto.getMerchantName());
        merchant.setBusinessLine(merchantDto.getBusinessLine());
        merchant.setPicUrls(merchantDto.getPicUrls());
        merchant.setOrganization(merchantDto.getOrganization());
        merchant.setBusinessLicense(merchantDto.getBusinessLicense());
        merchant.setRecordNumber(merchantDto.getRecordNumber());
        merchant.setContacts(merchantDto.getContacts());
        merchant.setTelephone(merchantDto.getTelephone());
        merchant.setAddress(merchantDto.getAddress());
        merchantService.update(merchant);
        return jsonSuccess();
    }

    @RequestMapping("/deleteMerchant")
    @ResponseBody
    public String deleteMerchant(HttpServletRequest request, MerchantDto merchantDto) {
        JunhaiAssert.notNull(merchantDto.getId(), "id不能为空");
        merchantService.delete(merchantDto.getId());
        return jsonSuccess();
    }

    @RequestMapping("/profile")
    public String profile(HttpServletRequest request, ModelMap modelMap) {
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.notNull(merchantId, "商户ID不能为空");

        MerchantInfo merchant = merchantService.read(merchantId);
        modelMap.put("merchant", merchant);
        return "merchant/profile";
    }

    @RequestMapping("/productionBaseInfoList")
    public String productionBaseList(HttpServletRequest request, ProductionBaseInfoDto productionBaseInfoDto, ModelMap modelMap) {
        productionBaseInfoDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = productionBaseInfoService.queryProductionBaseInfoCount(productionBaseInfoDto);
        Pagination pagination = new Pagination(productionBaseInfoDto.getPageNo(), iCount);

        List<ProductionBaseInfo> productionBaseInfoList = new ArrayList<ProductionBaseInfo>();
        if (iCount > 0) {
            productionBaseInfoDto.setOffset(pagination.getOffset());
            productionBaseInfoDto.setPageSize(pagination.getPageSize());
            productionBaseInfoList = productionBaseInfoService.queryProductionBaseInfoList(productionBaseInfoDto);
        }

        modelMap.put("productionBaseInfoDto", productionBaseInfoDto);
        modelMap.put("productionBaseInfoList", productionBaseInfoList);
        modelMap.put("pagination", pagination);
        return "merchant/productionBaseInfoList";
    }

    @RequestMapping("/productionBaseInfoDetail")
    public String productionBaseInfoDetail(HttpServletRequest request, ProductionBaseInfo productionBaseInfo, ModelMap modelMap) {
        if (productionBaseInfo.getId() != null) {
            productionBaseInfo = productionBaseInfoService.read(productionBaseInfo.getId());
        }

        // 新增或编辑企业基地信息
        modelMap.put("productionBaseInfo", productionBaseInfo);
        return "merchant/productionBaseInfoEdit";
    }

    @RequestMapping("/saveProductionBaseInfo")
    @ResponseBody
    public String saveProductionBaseInfo(HttpServletRequest request, ProductionBaseInfoDto productionBaseInfoDto) {
        JunhaiAssert.notBlank(productionBaseInfoDto.getBaseName(), "基地名称不能为空");

        Integer merchantId = SessionManager.get(request).getMerchantId();
        ProductionBaseInfo productionBaseInfo = new ProductionBaseInfo();
        if (productionBaseInfoDto.getId() != null) {
            productionBaseInfo = productionBaseInfoService.read(productionBaseInfo.getId());
            JunhaiAssert.isTrue(productionBaseInfo != null, "无效基地ID");
            JunhaiAssert.isTrue(productionBaseInfo.getMerchantId() == merchantId, "非法操作");
        } else {
            productionBaseInfo.setMerchantId(merchantId);
        }

        productionBaseInfo.setBaseName(productionBaseInfoDto.getBaseName());
        productionBaseInfo.setBaseType(productionBaseInfoDto.getBaseType());
        productionBaseInfo.setBaseArea(productionBaseInfoDto.getBaseArea());
        productionBaseInfo.setBaseAddress(productionBaseInfoDto.getBaseAddress());
        productionBaseInfo.setBaseEnvInfo(productionBaseInfoDto.getBaseEnvInfo());
        productionBaseInfo.setBaseResourceInfo(productionBaseInfoDto.getBaseResourceInfo());
        productionBaseInfo.setBaseBulidTime(productionBaseInfoDto.getBaseBulidTime());
        productionBaseInfo.setPicUrls(productionBaseInfoDto.getPicUrls());
        productionBaseInfoService.save(productionBaseInfo);

        return jsonSuccess();
    }

    @RequestMapping("/deleteProductionBaseInfo")
    @ResponseBody
    public String deleteProductionBaseInfo(HttpServletRequest request, ProductionBaseInfoDto productionBaseInfoDto) {
        JunhaiAssert.notNull(productionBaseInfoDto.getId(), "id不能为空");
        productionBaseInfoService.delete(productionBaseInfoDto.getId());
        return jsonSuccess();
    }

}
