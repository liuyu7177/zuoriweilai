package com.liuyu7177.zuoriweilai.web.AppCode;


import com.liuyu7177.zuoriweilai.model.dto.SeckillResult;
import org.springframework.stereotype.Controller;

/**
 * Created by liuyu7177 On 2019/5/9
 */
@Controller
public class JsonBaseController extends BaseController {

    /**
     * 返回错误消息
     * @param errMsg
     * @param <T>
     * @return
     */
    public <T> SeckillResult<T> ApiErr(String errMsg) {
        return new SeckillResult<T>(false, errMsg);
    }

    /**
     * 返回JSON类型的错误响应
     * @param t
     * @param <T>
     * @return
     */
    public <T> SeckillResult<T> ApiErr(T t) {
        return new SeckillResult<T>(false, t);
    }

    /**
     * 返回JSON类型的正确响应
     * @param t
     * @param <T>
     * @return
     */
    public <T> SeckillResult<T> ApiSuccess(T t) {
        return new SeckillResult<T>(true, t);
    }
}
