package io.namoosori.travelclub.step4.util;

public class NoSuchBoardException extends RuntimeException {
	//
	private static final long serialVersionUID = 5867172506387382920L;

	public NoSuchBoardException(String message) {
		super(message); 
	}
}