package order.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import order.infrastructure.po.MerchantContractSignedPO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 商户合同签订表dao
 * @author liangxifeng
 * @date 2021-12-23
 */
@Mapper
public interface IMerchantContractSignedMapper extends BaseMapper<MerchantContractSignedPO> {
}
