package com.nebula.wisys.web.service.implement;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nebula.wisys.persistence.model.BaseDevice;
import com.nebula.wisys.persistence.repository.BaseDeviceRepo;
import com.nebula.wisys.utils.HttpUrlParamPID;
import com.nebula.wisys.web.service.BaseDeviceService;

@Service
public class BaseDeviceServiceImpl implements BaseDeviceService {

    final static Logger logger = LoggerFactory.getLogger(BaseDeviceServiceImpl.class);

	@Qualifier("baseDeviceRepo")
	private BaseDeviceRepo baseDeviceRepo;

	@Autowired
	public BaseDeviceServiceImpl(BaseDeviceRepo baseDeviceRepo) {
		this.baseDeviceRepo = baseDeviceRepo;
	}

    @Override
    public List<BaseDevice> getBaseDeviceByPID(String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		HttpUrlParamPID paramPID = new HttpUrlParamPID(pidStr);
		logger.debug("[GET-START] " + paramPID.toString());
		if (paramPID.isPIDForAll()) {
			baseDeviceList.addAll(baseDeviceRepo.findAll());
		} else if (paramPID.isPIDRegular()) {
			baseDeviceList.addAll(baseDeviceRepo.findByPID(paramPID.getPID()));
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid bson ObjectId", pidStr));
		}
		logger.debug("[GET-END] Result Cnt: " + baseDeviceList.size());
		return baseDeviceList;
    }

    @Override
	public BaseDevice createBaseDevice(BaseDevice baseDevice) {
		logger.debug("[POST-START] " + baseDevice.toString());
		BaseDevice createdDevice = baseDeviceRepo.create(baseDevice);
		logger.debug("[POST-END] " + createdDevice.toString());
		return baseDevice;
    }

    @Override
	public BaseDevice updateBaseDevice(BaseDevice baseDevice) {
		logger.debug("[PUT-BEGIN] " + baseDevice.toString());
		BaseDevice updatedDevice = null;
		if (baseDevice.getPID() == null) {
			logger.error("PID is missing for " + baseDevice.toString());
		} else if (baseDeviceRepo.findByPID(baseDevice.getPID()).size() == 0) {
			logger.error("PID is not found: " + baseDevice.getPID().toString());
		} else {
			updatedDevice = baseDeviceRepo.update(baseDevice);
		}
		logger.debug("[PUT-END] Updated " + Objects.toString(updatedDevice, "no device"));
		return baseDevice.getPID() == null ? null : baseDevice;
    }

    @Override
	public List<BaseDevice> deleteBaseDeviceByPID(String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		HttpUrlParamPID paramPID = new HttpUrlParamPID(pidStr);
		logger.debug("[DELETE-START] " + paramPID.toString());
		if (paramPID.isPIDForAll()) {
			baseDeviceList = baseDeviceRepo.deleteAll();
		} else if (paramPID.isPIDRegular()) {
			baseDeviceList = baseDeviceRepo.deleteByPID(paramPID.getPID());
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid bson ObjectId", pidStr));
		}
		logger.debug("[DELETE-END] " + paramPID.toString());
		return baseDeviceList;
    }
    
}
