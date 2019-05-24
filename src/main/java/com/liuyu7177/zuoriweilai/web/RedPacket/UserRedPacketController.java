package com.liuyu7177.zuoriweilai.web.RedPacket;

import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.service.UserRedPacketService;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 一人可抢多次
 * Created by liuyu7177 On 2019/5/22
 */
@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController extends JsonBaseController {
    @Autowired
    private UserRedPacketService userRedPacketService = null;

    @RequestMapping(value = "/beginGrabRedPacket", method = RequestMethod.GET)
    public ModelAndView beginGrabRedPacket()
    {
        return  View();
    }

    /**
     * 正常抢红包有超发可能
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacket", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacket(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacket(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /**
     * 正常抢红包 悲观锁机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForUpdate(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacketForUpdate(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /**
     * 正常抢红包 乐观锁机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersion", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersion(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacketForVersion(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /**
     * 正常抢红包 乐观锁100毫秒重入机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersionAndTimeMillis", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersionAndTimeMillis(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacketForVersionAndTimeMillis(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /**
     * 正常抢红包 乐观锁3次重入机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersionAndThrice", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersionAndThrice(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacketForVersionAndThrice(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /**
     * 用存储过程执行抢红包
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketByProcedure", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketByProcedure(Long redPackId, Long userId) {
        int result = userRedPacketService.grabRedPacketByProcedure(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    @RequestMapping(value = "/grabRedPacketByRedis", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Long> grabRedPacketByRedis(Long redPackId,Long userId){
        Long result=userRedPacketService.grabRedPacketByRedis(redPackId,userId);
        String msg=result>0?"抢红包成功":"抢红包失败";
        return ApiSuccess(msg,result);
    }

}
