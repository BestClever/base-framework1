package com.halfsummer.baseframework.enums;


import com.halfsummer.baseframework.exception.BaseInfoInterface;

/**halfsummer
 * @Title: CommonEnum
 * @Description: 公用描述枚举类
 * @Version:1.0.0
 * @author pancm
 * @date 2018年6月25日
 */
public enum ParameterEnum implements BaseInfoInterface {
	// 参数定义
	OUTPATIENT_SERVICE(200, "门诊"),
	RETURN_VISIT(200, "回诊")



	;


	private Integer resultCode;


	private String resultMsg;

	ParameterEnum(Integer resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public Integer getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}
