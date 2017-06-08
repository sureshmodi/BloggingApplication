package org.cisco.cmad.BloggingApp.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMsg {
	
	private String errormsg;
	private int errorcode;
		
	public ErrorMsg () {
		
	}
	
	public ErrorMsg(String errormsg, int errorcode) {
		
		this.errormsg = errormsg;
		this.errorcode = errorcode;
		
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	
}
