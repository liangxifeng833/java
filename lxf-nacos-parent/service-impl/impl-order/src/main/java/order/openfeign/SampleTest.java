package order.openfeign;

import order.infrastructure.dao.IMerchantContractSignedMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleTest {
    @Autowired
    private IMerchantContractSignedMapper mapper;
}
