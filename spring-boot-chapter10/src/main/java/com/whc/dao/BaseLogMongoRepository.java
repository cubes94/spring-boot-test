package com.whc.dao;

import com.whc.log.BaseLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by whc on 2017/4/6.
 */
@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface BaseLogMongoRepository extends MongoRepository<BaseLog, String> {

}
