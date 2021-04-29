package com.nebula.wisys.web.service;

import java.util.List;

import com.nebula.wisys.persistence.model.BaseDevice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Qualifier("BaseDeviceService")
public interface BaseDeviceService {
	List<BaseDevice> getBaseDeviceByPID(String pidStr) throws MissingServletRequestParameterException;
	BaseDevice createBaseDevice(BaseDevice baseDevice);
	BaseDevice updateBaseDevice(BaseDevice baseDevice) throws MissingServletRequestParameterException;
	List<BaseDevice> deleteBaseDeviceByPID(String pidStr) throws MissingServletRequestParameterException;
}