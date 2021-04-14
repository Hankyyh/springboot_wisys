package com.nebula.wisys.utils;


public class HttpUrlParamPID {

    private String pidStr;
    private Long pid;
    private HttpUrlParamPIDType pidType;

	private void convertPIDFromStringToLong() {
		if (pidStr.equals("all")) {
			this.pidType = HttpUrlParamPIDType.PID_TYPE_ALL;
		} else {
			try {
				this.pid = Long.parseLong(this.pidStr);
				this.pid = (this.pid <= 0L) ? -1L : this.pid;
				this.pidType = HttpUrlParamPIDType.PID_TYPE_REGULAR;
			} catch(NumberFormatException e) {
				this.pidType = HttpUrlParamPIDType.PID_TYPE_INVALID;
			}
		}
	}

	private void updatePID(String pidStr) {
        this.pidStr = pidStr;
        this.pid = -1L;
		this.pidType = HttpUrlParamPIDType.PID_TYPE_INVALID;
		this.convertPIDFromStringToLong();
	}

    public HttpUrlParamPID(String pidStr) {
		this.updatePID(pidStr);
    }

	public Long getPID() {
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
		return (this.pidType == HttpUrlParamPIDType.PID_TYPE_ALL);
	}

    @Override
	public String toString() {
        return String.format("<%s> Str:%s Type:%s Value:%d\n", this.getClass().getSimpleName(), this.pidStr, this.pidType.toString(), this.pid);
	}
}
