package com.whc.service.impl;

import com.whc.dao.BaseLogMongoRepository;
import com.whc.log.BaseLog;
import com.whc.service.IBaseLogService;
import com.whc.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by whc on 2017/9/18.
 */
@Service("baseLogService")
public class BaseLogServiceImpl implements IBaseLogService {

    @Autowired
    private BaseLogMongoRepository baseLogMongoRepository;

    public BaseLog createBaseLog(BaseLog baseLog) {
        baseLog.setId(UUIDGenerator.create32Key());
        baseLog.setCreateTime(new Date());
        return baseLogMongoRepository.save(baseLog);
    }
}
