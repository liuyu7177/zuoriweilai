package com.liuyu7177.zuoriweilai.web.baseCode;


import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.framework.web.JsonResultResponseMsg;
import org.springframework.stereotype.Controller;

/**
 * Created by liuyu7177 On 2019/5/9
 */
@Controller
public class JsonBaseController extends BaseController {

    /**
     * 返回错误消息
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiErr() {
        return new JsonResult<T>(false, JsonResultResponseMsg.AjaxErrorMsgTip);
    }

    /**
     * 返回错误消息
     *
     * @param errMsg
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiErr(String errMsg) {
        return new JsonResult<T>(false, errMsg);
    }

    /**
     * 返回JSON类型的错误响应
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiErr(T t) {
        return new JsonResult<T>(false, t);
    }

    /**
     * 返回错误消息
     *
     * @param errMsg
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiErr(String errMsg,T t) {
        return new JsonResult<T>(false, errMsg,t);
    }

    /**
     * 返回JSON类型的正确响应
     *
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiSuccess() {
        return new JsonResult<T>(true, JsonResultResponseMsg.AjaxSuccessMsgTip);
    }

    /**
     * 返回JSON类型的正确响应
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiSuccess(T t) {
        return new JsonResult<T>(true,JsonResultResponseMsg.AjaxSuccessMsgTip, t);
    }

    /**
     * 返回JSON类型的正确响应
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> JsonResult<T> ApiSuccess(String msg,T t) {
        return new JsonResult<T>(true, t);
    }
}
