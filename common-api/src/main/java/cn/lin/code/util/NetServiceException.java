package cn.lin.code.util;

public class NetServiceException extends Exception {
	private static final long serialVersionUID = -944028857877556140L;

	public NetServiceException(String message, Throwable cause){
		super(message, cause);
	}
}
