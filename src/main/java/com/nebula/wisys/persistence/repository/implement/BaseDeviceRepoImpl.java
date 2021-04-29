package com.nebula.wisys.persistence.repository.implement;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.nebula.wisys.persistence.model.BaseDevice;
import com.nebula.wisys.persistence.repository.BaseDeviceRepo;

@Repository
public class BaseDeviceRepoImpl implements BaseDeviceRepo {

    @Qualifier("BaseDeviceMongoRepo")
	private BaseDeviceMongoRepo baseDeviceMongoRepo;

    @Autowired
    public BaseDeviceRepoImpl(BaseDeviceMongoRepo baseDeviceMongoRepo) {
        this.baseDeviceMongoRepo = baseDeviceMongoRepo;
    }

    // GET
    @Override
    public List<BaseDevice> findAll() {
        return baseDeviceMongoRepo.findAll();
    }

    @Override
    public List<BaseDevice> findByPID(ObjectId PID) {
        return baseDeviceMongoRepo.findByPID(PID);
    }

    // POST
    @Override
    public BaseDevice create(BaseDevice baseDevice) {
        return baseDeviceMongoRepo.insert(baseDevice);
    }

    // PUT
    @Override
    public BaseDevice update(BaseDevice baseDevice) {
        return baseDeviceMongoRepo.save(baseDevice);
    }

    // DELETE
    @Override
    public List<BaseDevice> deleteAll() {
        List<BaseDevice> baseDeviceList = findAll();
        baseDeviceMongoRepo.deleteAll();
        return baseDeviceList;
    }

    @Override
    public List<BaseDevice> deleteByPID(ObjectId PID) {
        return baseDeviceMongoRepo.deleteByPID(PID);
    }
}
