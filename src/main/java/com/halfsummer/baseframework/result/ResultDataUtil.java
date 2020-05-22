package com.halfsummer.baseframework.result;


import com.halfsummer.baseframework.exception.BaseInfoInterface;
import com.halfsummer.baseframework.exception.BizException;

/**
 *  ResponseDataUtil:返回的工具类,主要是方便返回的写法
 *  * 单纯的返回工具类,直接放在这个包
 */
public class ResultDataUtil {

    /**
     * 返回成功描述和数据详情
     * @param baseInfoInterface
     * @param data
     * @return
     */
    public static ResultInfo createSuccess(BaseInfoInterface baseInfoInterface, Object data){
        ResultInfo resultInfo =new ResultInfo();
        resultInfo.setCode(baseInfoInterface.getResultCode());
        resultInfo.setMsg(baseInfoInterface.getResultMsg());
        resultInfo.setData(data);
        return resultInfo;
    }


    /**
     * 返回失败 使用枚举类的方式
     * @param baseInfoInterface
     * @return
     */
    public static ResultInfo createFail(BaseInfoInterface baseInfoInterface){
        ResultInfo resultInfo =new ResultInfo();
        resultInfo.setCode(baseInfoInterface.getResultCode());
        resultInfo.setMsg(baseInfoInterface.getResultMsg());
        return resultInfo;
    }


    /**
     * 失败
     */
    public static ResultInfo createFail(Integer code, String message) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(message);
        return resultInfo;
    }


    /**
     * 创建查询结果信息
     * @param pageInfo
     * @return
     */
    public static DataGridResultInfo createQueryResult(PageInfo pageInfo){
        return new DataGridResultInfo(pageInfo.getTotal(),pageInfo.getList());
    }

    /**ui
     * 抛出异常
     * @param baseInfoInterface
     * @throws
     */
    public static void throwExcepion(BaseInfoInterface baseInfoInterface) throws BizException {
        throw new BizException(baseInfoInterface);
    }

}
