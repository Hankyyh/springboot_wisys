package com.nebula.wisys.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;

import com.nebula.wisys.model.BaseDevice;

@Qualifier("baseDeviceRepo")
public interface BaseDeviceRepo {
    // GET
    public List<BaseDevice> findAll();
    public List<BaseDevice> findByPID(ObjectId PID);

    // POST
    public BaseDevice create(BaseDevice baseDevice);

    // PUT
    public BaseDevice update(BaseDevice baseDevice);

    // DELETE
    public List<BaseDevice> deleteAll();
    public List<BaseDevice> deleteByPID(ObjectId PID);
}
