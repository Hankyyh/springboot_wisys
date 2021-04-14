package com.nebula.wisys.service;

import java.util.List;

import com.nebula.wisys.model.BaseDevice;

public interface BaseDeviceService {
	List<BaseDevice> getBaseDeviceByPID(String pidStr);
	BaseDevice createBaseDevice(BaseDevice baseDevice);
	BaseDevice updateBaseDevice(BaseDevice baseDevice);
	List<BaseDevice> deleteBaseDeviceByPID(String pidStr);
}