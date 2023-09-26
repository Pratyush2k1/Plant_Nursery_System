package com.amdocs.pns.exception;

public class WrongIDException extends Exception {
	String str;
	public WrongIDException(String s) {
		str = s;
	}
	
	public String toString() {
		return ("Error : " + str);
	}
}

