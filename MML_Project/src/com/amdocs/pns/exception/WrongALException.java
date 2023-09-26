package com.amdocs.pns.exception;

public class WrongALException extends Exception {
	String str;
	public WrongALException(String s) {
		str = s;
		}
	public String toString() {
		return ("Error : " + str);
		}
	}

