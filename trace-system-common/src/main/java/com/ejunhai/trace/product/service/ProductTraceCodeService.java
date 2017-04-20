package com.ejunhai.trace.product.service;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductTraceCodeDto;
import com.ejunhai.trace.product.model.ProductTraceCode;

public interface ProductTraceCodeService {

    /**
     * 根据Id获取ProductTraceCode
     * 
     * @param id
     * @return
     */
    public ProductTraceCode read(Integer id);

    /**
     * 新增ProductTraceCode
     * 
     * @param merchant
     */
    public void insert(ProductTraceCode productTraceCode);

    /**
     * 更新ProductTraceCode
     * 
     * @param merchant
     */
    public void update(ProductTraceCode productTraceCode);

    /**
     * 删除ProductTraceCode
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询ProductTraceCode数量
     * 
     * @param merchant
     * @return
     */
    public Integer queryProductTraceCodeCount(ProductTraceCodeDto productTraceCodeDto);

    /**
     * 查询ProductTraceCode列表
     * 
     * @param merchant
     * @return
     */
    public List<ProductTraceCode> queryProductTraceCodeList(ProductTraceCodeDto productTraceCodeDto);

}
