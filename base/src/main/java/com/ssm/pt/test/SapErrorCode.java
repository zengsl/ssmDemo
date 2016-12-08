package com.ssm.pt.test;

/**
 * @author zengsl
 *
 */
public enum SapErrorCode {
	IWORK("0"), TEXT("1");

	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	private SapErrorCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String[] args) {
		System.out.println(SapErrorCode.IWORK.getCode());
	}
}
