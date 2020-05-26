package com.halfsummer.baseframework.enums;


import com.halfsummer.baseframework.exception.BaseInfoInterface;

/**halfsummer
 * @Title: CommonEnum
 * @Description: 公用描述枚举类
 * @Version:1.0.0
 * @author pancm
 * @date 2018年6月25日
 */
public enum CommonEnum implements BaseInfoInterface {
	// 数据操作错误定义
	SUCCESS(200, "成功!"),
	BODY_NOT_MATCH(400,"请求的数据格式不符!"),
	SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
	NOT_FOUND(404, "未找到该资源!"),
	INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
	SERVER_BUSY(503,"服务器正忙，请稍后再试!"),

	ARITHMETIC_ERROR(1100,"数学运算错误!"),
	SAVE_FAILED(101,"保存失败,请重试"),

	not_exist_user(2000,"用户名或密码错误")


	;

	/** 错误码 */
	private Integer resultCode;

	/** 错误描述 */
	private String resultMsg;

	CommonEnum(Integer resultCode, String resultMsg) {
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
