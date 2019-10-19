package com.vikash.restraunt.library;

public class ResponseLibrary {

    private boolean error;
    private String message;
    private int status;
    private Object data;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "ResponseLibrary [error=" + error + ", message=" + message + ", status=" + status + ", data=" + data
				+ "]";
	}
}
