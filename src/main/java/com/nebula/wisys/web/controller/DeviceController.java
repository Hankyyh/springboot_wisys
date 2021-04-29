package com.nebula.wisys.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.wisys.persistence.model.BaseDevice;
import com.nebula.wisys.web.response.ApiResponse;
import com.nebula.wisys.web.service.BaseDeviceService;

@RestController
@RequestMapping(path = "/device")
public class DeviceController {

    final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Qualifier("BaseDeviceService")
	private BaseDeviceService baseDeviceService;
    
	@Autowired
	public DeviceController(BaseDeviceService baseDeviceService) {
		this.baseDeviceService = baseDeviceService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET) 
	public ApiResponse get(@RequestParam(name = "pid") String pidStr) throws MissingServletRequestParameterException {
		return new ApiResponse(HttpStatus.OK, null, baseDeviceService.getBaseDeviceByPID(pidStr));
	}

	@RequestMapping(value = "", method = RequestMethod.POST) 
	public ApiResponse create(@RequestBody BaseDevice baseDevice) {
		return new ApiResponse(HttpStatus.OK, null, baseDeviceService.createBaseDevice(baseDevice));
	}

	@RequestMapping(value = "", method = RequestMethod.PUT) 
	public ApiResponse update(@RequestBody BaseDevice baseDevice) throws MissingServletRequestParameterException {
		return new ApiResponse(HttpStatus.OK, null, baseDeviceService.updateBaseDevice(baseDevice));
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE) 
	public ApiResponse delete(@RequestParam(name = "pid") String pidStr) throws MissingServletRequestParameterException {
		return new ApiResponse(HttpStatus.OK, null, baseDeviceService.deleteBaseDeviceByPID(pidStr));
	}
}
