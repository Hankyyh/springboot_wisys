package com.nebula.wisys.controller;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.wisys.model.BaseDevice;

@RestController
@RequestMapping(path = "/device")
public class DeviceController {

    final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    
	public DeviceController() {}

	protected Long convertPIDFromStringToLong(String pidStr) {
		Long pid = -1L;
		if (pidStr.equals("all")) {
			pid = 0L;
		} else {
			try {
				pid = Long.parseLong(pidStr);
				pid = (pid <= 0L) ? -1L : pid;
			} catch(NumberFormatException e) {
				pid = -1L;
			}
		}
		return pid;
	}

	@RequestMapping(value = "", method = RequestMethod.GET) 
	public List<BaseDevice> get(@RequestParam(name = "pid") String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		Long pid = this.convertPIDFromStringToLong(pidStr);
		logger.debug(pid.toString());
		if (pid == 0L) {
			baseDeviceList.add(new BaseDevice(1L, "SN-TEST1", "MAC-TEST1"));
			baseDeviceList.add(new BaseDevice(2L, "SN-TEST2", "MAC-TEST2"));
		} else if (pid > 0L) {
			baseDeviceList.add(new BaseDevice(pid, "SN-TEST" + pid.toString(), "MAC-TEST" + pid.toString()));
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid Long number", pidStr));
		}
		return baseDeviceList;
	}

	@RequestMapping(value = "", method = RequestMethod.POST) 
	public BaseDevice create(@RequestBody BaseDevice baseDevice) {
		logger.debug("[POST-BEGIN] " + baseDevice.toString());
        baseDevice.setSerialNum("SN-POST-CHANGE");
        baseDevice.setMacAddr("MAC-POST-CHANGE");
		logger.debug("[POST-END] " + baseDevice.toString());
		return baseDevice;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT) 
	public BaseDevice update(@RequestBody BaseDevice baseDevice) {
		logger.debug("[PUT-BEGIN] " + baseDevice.toString());
        baseDevice.setSerialNum("SN-PUT-CHANGE");
        baseDevice.setMacAddr("MAC-PUT-CHANGE");
		logger.debug("[PUT-END] " + baseDevice.toString());
		return baseDevice;
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE) 
	public List<BaseDevice> delete(@RequestParam(name = "pid") String pidStr) {
		List<BaseDevice> baseDeviceList = new ArrayList<BaseDevice>();
		Long pid = this.convertPIDFromStringToLong(pidStr);
		logger.debug(pid.toString());
		if (pid == 0L) {
			baseDeviceList.add(new BaseDevice(1L, "SN-TEST1", "MAC-TEST1"));
			baseDeviceList.add(new BaseDevice(2L, "SN-TEST2", "MAC-TEST2"));
		} else if (pid > 0L) {
			baseDeviceList.add(new BaseDevice(pid, "SN-TEST" + pid.toString(), "MAC-TEST" + pid.toString()));
		} else {
			logger.error(String.format("Failed to convert PID string %s to a valid Long number", pidStr));
		}
		return baseDeviceList;
	}
}
