package com.codingexcercise.vikram.tlv;

public class LowerCase implements InputType{
	@Override    
	public String isValid(String str) {
		return str.toLowerCase();
	}
}
