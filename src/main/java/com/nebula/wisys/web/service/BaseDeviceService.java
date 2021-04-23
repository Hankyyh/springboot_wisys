package com.nebula.wisys.web.service;

import java.util.List;

import com.nebula.wisys.persistence.model.BaseDevice;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("baseDeviceService")
public interface BaseDeviceService {
	List<BaseDevice> getBaseDeviceByPID(String pidStr);
	BaseDevice createBaseDevice(BaseDevice baseDevice);
	BaseDevice updateBaseDevice(BaseDevice baseDevice);
	List<BaseDevice> deleteBaseDeviceByPID(String pidStr);
}