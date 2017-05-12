package com.ejunhai.trace.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ejunhai.trace.common.base.BaseController;
import com.ejunhai.trace.common.base.Pagination;
import com.ejunhai.trace.common.errors.JunhaiAssert;
import com.ejunhai.trace.common.utils.DateUtil;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;
import com.ejunhai.trace.merchant.service.ProductionBaseInfoService;
import com.ejunhai.trace.merchant.utils.MerchantUtil;
import com.ejunhai.trace.product.dto.ProductAccessLogDto;
import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.dto.ProductTraceCodeDto;
import com.ejunhai.trace.product.model.ProductAccessLog;
import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.model.ProductInfo;
import com.ejunhai.trace.product.model.ProductTraceCode;
import com.ejunhai.trace.product.service.ProductAccessLogService;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductInfoService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;
import com.ejunhai.trace.product.utils.ProductAccesslogUtil;
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

    @Resource
    private ProductAccessLogService productAccessLogService;

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

    @RequestMapping("/downloadExcelTemplate")
    public ResponseEntity<byte[]> downloadExcelTemplate(HttpServletRequest request) throws Exception {
        String filepath = request.getSession().getServletContext().getRealPath("/") + File.separator + "template" + File.separator + "产品批量导入模板.xlsx";
        File file = new File(filepath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("GBK"), "iso-8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping("/importProductInfo")
    public String importProductInfo(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        Integer merchantId = SessionManager.get(request).getMerchantId();
        Integer creator = SessionManager.get(request).getId();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);

        // 解析excel，讲数据插入到数据库
        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
        for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
            HSSFRow row = sheet.getRow(j);
            String title = row.getCell(1).getRichStringCellValue().getString();
            if (StringUtils.isNotBlank(title)) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setMerchantId(merchantId);
                productInfo.setProductName(title);
                productInfo.setBrandName(row.getCell(2).getRichStringCellValue().getString());
                productInfo.setRemark(row.getCell(3).getRichStringCellValue().getString());
                productInfo.setCreator(creator);
                productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                productInfoList.add(productInfo);
            }
        }

        // 保存产品
        for (ProductInfo productInfo : productInfoList) {
            productInfoService.save(productInfo);
        }

        return "redirect:/product/productInfoList.jhtml";
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

    @RequestMapping("/generateTraceCodes")
    public String generateTraceCodes(HttpServletRequest request, ProductBatchDto productBatchDto) {
        JunhaiAssert.notNull(productBatchDto.getId(), "id不能为空");
        productBatchService.generateTraceCodes(productBatchDto.getId());
        return jsonSuccess();
    }

    @RequestMapping("/exportTraceCodes")
    public void exportTraceCodes(HttpServletRequest request, String batchNo, HttpServletResponse response) throws IOException {
        JunhaiAssert.notNull(batchNo, "batchNo不能为空");

        // 根据批次查询溯源码列表
        ProductTraceCodeDto productTraceCodeDto = new ProductTraceCodeDto();
        productTraceCodeDto.setMerchantId(SessionManager.get(request).getMerchantId());
        productTraceCodeDto.setBatchNo(batchNo);
        productTraceCodeDto.setOffset(0);
        productTraceCodeDto.setPageSize(Integer.MAX_VALUE);
        List<ProductTraceCode> productTraceCodeList = productTraceCodeService.queryProductTraceCodeList(productTraceCodeDto);

        response.reset();
        response.setContentType("application/csv;charset=UTF-8");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = batchNo + "_溯源码" + simpleDateFormat.format(new Date()) + ".csv";
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso-8859-1"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        for (ProductTraceCode productTraceCode : productTraceCodeList) {
            out.println(productTraceCode.getTraceCode());
        }
        out.flush();
        out.close();
    }

    @RequestMapping("/queryTraceCodes")
    public String productTraceCodeList(HttpServletRequest request, ProductTraceCodeDto productTraceCodeDto, ModelMap modelMap) {
        productTraceCodeDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = productTraceCodeService.queryProductTraceCodeCount(productTraceCodeDto);
        Pagination pagination = new Pagination(productTraceCodeDto.getPageNo(), iCount);

        // 获取分页数据
        List<ProductTraceCode> productTraceCodeList = new ArrayList<ProductTraceCode>();
        if (iCount > 0) {
            productTraceCodeDto.setOffset(pagination.getOffset());
            productTraceCodeDto.setPageSize(pagination.getPageSize());
            productTraceCodeList = productTraceCodeService.queryProductTraceCodeList(productTraceCodeDto);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("productTraceCodeDto", productTraceCodeDto);
        modelMap.put("productTraceCodeList", productTraceCodeList);
        return "product/productTraceCodeList";
    }

    @RequestMapping("/discardTraceCode")
    @ResponseBody
    public String discardTraceCode(HttpServletRequest request, ProductTraceCodeDto productTraceCodeDto, ModelMap modelMap) {
        ProductTraceCode productTraceCode = productTraceCodeService.getProductTraceCodeByCode(productTraceCodeDto.getTraceCode());
        JunhaiAssert.isTrue(productTraceCode != null, "溯源码不存在");
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(productTraceCode.getMerchantId() == merchantId, "非法操作");
        productTraceCode.setStatus(productTraceCodeDto.getStatus());
        productTraceCodeService.update(productTraceCode);
        return jsonSuccess();
    }

    @RequestMapping("/productAccessLogList")
    public String productAccessLogList(HttpServletRequest request, ProductAccessLogDto productAccessLogDto, ModelMap modelMap) {
        productAccessLogDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = productAccessLogService.queryProductAccessLogCount(productAccessLogDto);
        Pagination pagination = new Pagination(productAccessLogDto.getPageNo(), iCount);

        // 获取分页数据
        List<ProductAccessLog> productAccessLogList = new ArrayList<ProductAccessLog>();
        if (iCount > 0) {
            productAccessLogDto.setOffset(pagination.getOffset());
            productAccessLogDto.setPageSize(pagination.getPageSize());
            productAccessLogList = productAccessLogService.queryProductAccessLogList(productAccessLogDto);

            // 获取产品ID-产品映射关系
            List<Integer> productIdList = ProductAccesslogUtil.getProductIdList(productAccessLogList);
            List<ProductInfo> productInfoList = productInfoService.getProductInfoListByIds(productIdList);
            Map<String, ProductInfo> productInfoMap = ProductUtil.getProductInfoMap(productInfoList);
            modelMap.put("productInfoMap", productInfoMap);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("productAccessLogDto", productAccessLogDto);
        modelMap.put("productAccessLogList", productAccessLogList);
        return "product/productAccessLogList";
    }
}
