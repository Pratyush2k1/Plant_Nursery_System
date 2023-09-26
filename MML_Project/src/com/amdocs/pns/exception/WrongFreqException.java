package com.amdocs.pns.exception;

public class WrongFreqException extends Exception {
	String str;
	public WrongFreqException(String s) {
		str = s;
		}
	public String toString() {
		return ("Error : " + str);
		}
	}
