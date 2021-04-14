package com.nebula.wisys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BaseDevice {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("pid")
	private Long pid;
    @JsonProperty("snum")
	private String serialNum;
    @JsonProperty("mac")
	private String macAddr;

    public BaseDevice() {}

    public BaseDevice(Long pid, String serialNum, String macAddr) {
        this.pid = pid;
        this.serialNum = serialNum;
        this.macAddr = macAddr;
    }

    public Long getPID() {
        return pid;
    }

    public void setPID(Long pid) {
        this.pid = pid;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    } 
    
    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    } 

    @Override
	public String toString() {
        return String.format("[BaseDevice ID-%d Serial Number-%s MAC Address-%s\n", pid, serialNum, macAddr);
	}
}
