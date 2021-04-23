package com.nebula.wisys.persistence.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nebula.wisys.utils.ObjectIdJsonSerializer;

@Document(collection = "base_device")
public class BaseDevice {

    @Id @JsonProperty("pid")
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
	private ObjectId pid;
    @Field("snum") @JsonProperty("snum")
	private String serialNum;
    @Field("mac") @JsonProperty("mac")
	private String macAddr;

    public BaseDevice() {}

    public BaseDevice(ObjectId pid, String serialNum, String macAddr) {
        this.pid = pid;
        this.serialNum = serialNum;
        this.macAddr = macAddr;
    }

    public ObjectId getPID() {
        return this.pid;
    }

    public void setPID(ObjectId pid) {
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
        return String.format("%s <ID:%s|SN:%s|MAC:%s>\n", this.getClass().getSimpleName(), 
            Objects.toString(this.pid, "null"), Objects.toString(this.serialNum, "null"), Objects.toString(this.macAddr, "null"));
	}
}
