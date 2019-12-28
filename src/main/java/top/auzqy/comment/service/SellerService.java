package top.auzqy.comment.service;

import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.model.SellerModel;

import java.util.List;

public interface SellerService {

    SellerModel create(SellerModel sellerModel);
    SellerModel get(Integer id);
    List<SellerModel> selectAll();
    SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException;

}
