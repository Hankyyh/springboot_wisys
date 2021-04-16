package com.nebula.wisys.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;

import com.nebula.wisys.model.BaseDevice;

public interface BaseDeviceRepository extends MongoRepository<BaseDevice, ObjectId> {

    @Query(value = "{'_id' : ?0}")
    List<BaseDevice> findByPID(ObjectId PID);

    @Query(value = "{'_id' : ?0}", delete = true)
    List<BaseDevice> deleteByPID(ObjectId PID);
}
