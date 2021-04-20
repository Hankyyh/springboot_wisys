package com.nebula.wisys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.wisys.model.BaseDevice;
import com.nebula.wisys.service.BaseDeviceService;

@RestController
@RequestMapping(path = "/device")
public class DeviceController {

    final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	
	@Qualifier("baseDeviceService")
	private BaseDeviceService baseDeviceService;
    
	@Autowired
	public DeviceController(BaseDeviceService baseDeviceService) {
		this.baseDeviceService = baseDeviceService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET) 
	public List<BaseDevice> get(@RequestParam(name = "pid") String pidStr) {
		return baseDeviceService.getBaseDeviceByPID(pidStr);
	}

	@RequestMapping(value = "", method = RequestMethod.POST) 
	public BaseDevice create(@RequestBody BaseDevice baseDevice) {
		return baseDeviceService.createBaseDevice(baseDevice);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT) 
	public BaseDevice update(@RequestBody BaseDevice baseDevice) {
		return baseDeviceService.updateBaseDevice(baseDevice);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE) 
	public List<BaseDevice> delete(@RequestParam(name = "pid") String pidStr) {
		return baseDeviceService.deleteBaseDeviceByPID(pidStr);
	}
}
