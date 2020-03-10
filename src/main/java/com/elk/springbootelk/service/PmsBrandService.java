package com.elk.springbootelk.service;

import com.elk.springbootelk.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author zmf
 * @version 1.0
 * @ClassName PmsBrandService
 * @Description: 品牌业务实体类
 * @date 2020/3/9 14:41
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
