package com.atjl.eg;


import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.service.DataCoverteService;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryAfterType;
import org.springframework.stereotype.Service;

@Service(EgConstant.COV_SERVICE)
public class AreaCovService extends DataCoverteService {
    @Override
    public DataCoverteConfig getConfig() {
        return DataTestUtil.getCovConfig();
    }

    @Override
    public PageOption getInitOption() {
        PageOption opt = new PageOption();
        opt.setServiceName(EgConstant.COV_SERVICE);
        opt.setPageSize(10);
        opt.setBatchProcess(true);
        opt.setAfterType(RetryAfterType.NONE);
        opt.setGetDataType(GetDataType.CUSTOM_BATCH_USEPAGE);
        return opt;
    }
}
