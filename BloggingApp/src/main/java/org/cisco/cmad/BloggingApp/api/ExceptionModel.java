package org.cisco.cmad.BloggingApp.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionModel {
	
	private String errormsg;
	private int errorcode;
	private String referdoc;
	
	public ExceptionModel () {
		
	}
	
	public ExceptionModel(String errormsg, int errorcode, String referdoc) {
		
		this.errormsg = errormsg;
		this.errorcode = errorcode;
		this.referdoc = referdoc;
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
	public String getReferdoc() {
		return referdoc;
	}
	public void setReferdoc(String referdoc) {
		this.referdoc = referdoc;
	}
	
}
