package com.liuyu7177.zuoriweilai.web.RedPacket;

import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.model.entitys.RedPacket;
import com.liuyu7177.zuoriweilai.service.RedPacketService;
import com.liuyu7177.zuoriweilai.service.UserRedPacketUniqueService;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 一人只能抢一次 唯一索引限制
 * Created by liuyu7177 On 2019/5/22
 */
@Controller
@RequestMapping("/userRedPacketUnique")
public class UserRedPacketUniqueController extends JsonBaseController {
    @Autowired
    private UserRedPacketUniqueService userRedPacketUniqueService = null;

    @Autowired
    private RedPacketService redPacketService = null;

    @RequestMapping(value = "/beginGrabRedPacket", method = RequestMethod.GET)
    public ModelAndView beginGrabRedPacket(Integer beginUserId)
    {
        if(beginUserId==null)
        {
            beginUserId=1;
        }
        mv.addObject("userId",beginUserId);
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
        int result = userRedPacketUniqueService.grabRedPacket(redPackId, userId);
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
        int result = userRedPacketUniqueService.grabRedPacketForUpdate(redPackId, userId);
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
        int result = userRedPacketUniqueService.grabRedPacketForVersion(redPackId, userId);
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
        int result = userRedPacketUniqueService.grabRedPacketForVersionAndTimeMillis(redPackId, userId);
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
        int result = userRedPacketUniqueService.grabRedPacketForVersionAndThrice(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }

    /*************************唯一索引 调整代码顺序测试*********************************************/

    /**
     * 正常抢红包 悲观锁机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForUpdateOrder", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForUpdateOrder(Long redPackId, Long userId) {
        RedPacket redPacket= redPacketService.getRedPacket(redPackId);
        if (redPacket.getStock() > 0) {
            int result = userRedPacketUniqueService.grabRedPacketForUpdateOrder(redPackId, userId, redPacket.getAmount());
            return ApiSuccess("受影响的行数："+result, result);
        }
        return ApiErr();
    }

    /**
     * 正常抢红包 乐观锁机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersionOrder", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersionOrder(Long redPackId, Long userId) {
        RedPacket redPacket= redPacketService.getRedPacket(redPackId);
        if (redPacket.getStock() > 0) {
            int result = userRedPacketUniqueService.grabRedPacketForVersionOrder(redPackId, userId, redPacket.getAmount());
            return ApiSuccess("受影响的行数："+result, result);
        }
        return ApiErr();
    }

    /**
     * 正常抢红包 乐观锁100毫秒重入机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersionAndTimeMillisOrder", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersionAndTimeMillisOrder(Long redPackId, Long userId) {
        RedPacket redPacket= redPacketService.getRedPacket(redPackId);
        if (redPacket.getStock() > 0) {
            int result = userRedPacketUniqueService.grabRedPacketForVersionAndTimeMillisOrder(redPackId, userId, redPacket.getAmount());
            return ApiSuccess("受影响的行数："+result, result);
        }
        return ApiErr();
    }

    /**
     * 正常抢红包 乐观锁3次重入机制
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketForVersionAndThriceOrder", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketForVersionAndThriceOrder(Long redPackId, Long userId) {
        RedPacket redPacket= redPacketService.getRedPacket(redPackId);
        if (redPacket.getStock() > 0) {
            int result = userRedPacketUniqueService.grabRedPacketForVersionAndThriceOrder(redPackId, userId, redPacket.getAmount());
            return ApiSuccess("受影响的行数："+result, result);
        }
        return ApiErr();
    }

    /**
     * 正常抢红包 用存储过程执行抢红包
     * @param redPackId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/grabRedPacketByProcedure", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacketByProcedure(Long redPackId, Long userId) {
        int result = userRedPacketUniqueService.grabRedPacketByProcedure(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);
    }


}
