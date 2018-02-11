package com.codingexcercise.vikram.tlv;

public class UpperCase implements InputType{

	  @Override   
	  public String isValid(String str) {
	        return str.toUpperCase();
	    }

}
