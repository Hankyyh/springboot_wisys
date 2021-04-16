package com.nebula.wisys.utils;

import java.util.Objects;

import org.bson.types.ObjectId;

public class HttpUrlParamPID {

    private String pidStr;
    private ObjectId pid;
    private HttpUrlParamPIDType pidType;

	private void convertPIDFromStringToLong() {
		if (pidStr.equals("all")) {
			this.pidType = HttpUrlParamPIDType.PID_TYPE_ALL;
		} else {
			try {
				this.pid = new ObjectId(this.pidStr);
				this.pidType = HttpUrlParamPIDType.PID_TYPE_REGULAR;
			} catch(IllegalArgumentException e) {
				this.pidType = HttpUrlParamPIDType.PID_TYPE_INVALID;
			}
		}
	}

	private void updatePID(String pidStr) {
        this.pidStr = pidStr;
        this.pid = null;
		this.pidType = HttpUrlParamPIDType.PID_TYPE_INVALID;
		this.convertPIDFromStringToLong();
	}

    public HttpUrlParamPID(String pidStr) {
		this.updatePID(pidStr);
    }

	public ObjectId getPID() {
		return this.pid;
	}

	public HttpUrlParamPIDType getPIDType() {
		return this.pidType;
	}
	
	public boolean isPIDValid() {
		return (this.pidType == HttpUrlParamPIDType.PID_TYPE_INVALID);
	}

	public boolean isPIDForAll() {
		return (this.pidType == HttpUrlParamPIDType.PID_TYPE_ALL);
	}

	public boolean isPIDRegular() {
		return (this.pidType == HttpUrlParamPIDType.PID_TYPE_REGULAR);
	}

    @Override
	public String toString() {
        return String.format("%s <Str:%s|Type:%s|ObjectId:%s>\n", this.getClass().getSimpleName(),
			Objects.toString(pidStr, "null"), Objects.toString(pidType, "null"), Objects.toString(pid, "null"));
	}
}
