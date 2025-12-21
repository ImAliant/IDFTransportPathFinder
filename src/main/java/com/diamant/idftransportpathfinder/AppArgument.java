package com.diamant.idftransportpathfinder;

public enum AppArgument {
	EXTRACT_ONLY("--extract-only");

	private final String value;

	AppArgument(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
