package com.nebula.wisys.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nebula.wisys.model.BaseDevice;
import com.nebula.wisys.service.BaseDeviceService;
import com.nebula.wisys.utils.HttpUrlParamPID;

@Service
public class BaseDeviceServiceImpl implements BaseDeviceService {

    final static Logger logger = LoggerFactory.getLogger(BaseDeviceServiceImpl.class);

    @Override
    public List<BaseDevice> getBaseDeviceByPID(String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		HttpUrlParamPID paramPID = new HttpUrlParamPID(pidStr);
		logger.debug(paramPID.toString());
		if (paramPID.isPIDForAll()) {
			baseDeviceList.add(new BaseDevice(1L, "SN-TEST1", "MAC-TEST1"));
			baseDeviceList.add(new BaseDevice(2L, "SN-TEST2", "MAC-TEST2"));
		} else if (paramPID.isPIDRegular()) {
			baseDeviceList.add(new BaseDevice(paramPID.getPID(), "SN-TEST" + paramPID.getPID().toString(), "MAC-TEST" + paramPID.getPID().toString()));
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid Long number", pidStr));
		}
		return baseDeviceList;
    }

    @Override
	public BaseDevice createBaseDevice(BaseDevice baseDevice) {
		logger.debug("[POST-BEGIN] " + baseDevice.toString());
        baseDevice.setSerialNum("SN-POST-CHANGE");
        baseDevice.setMacAddr("MAC-POST-CHANGE");
		logger.debug("[POST-END] " + baseDevice.toString());
		return baseDevice;
    }

    @Override
	public BaseDevice updateBaseDevice(BaseDevice baseDevice) {
		logger.debug("[PUT-BEGIN] " + baseDevice.toString());
        baseDevice.setSerialNum("SN-PUT-CHANGE");
        baseDevice.setMacAddr("MAC-PUT-CHANGE");
		logger.debug("[PUT-END] " + baseDevice.toString());
		return baseDevice;
    }

    @Override
	public List<BaseDevice> deleteBaseDeviceByPID(String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		HttpUrlParamPID paramPID = new HttpUrlParamPID(pidStr);
		logger.debug(paramPID.toString());
		if (paramPID.isPIDForAll()) {
			baseDeviceList.add(new BaseDevice(1L, "SN-TEST1", "MAC-TEST1"));
			baseDeviceList.add(new BaseDevice(2L, "SN-TEST2", "MAC-TEST2"));
		} else if (paramPID.isPIDRegular()) {
			baseDeviceList.add(new BaseDevice(paramPID.getPID(), "SN-TEST" + paramPID.getPID().toString(), "MAC-TEST" + paramPID.getPID().toString()));
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid Long number", pidStr));
		}
		return baseDeviceList;
    }
    
}
