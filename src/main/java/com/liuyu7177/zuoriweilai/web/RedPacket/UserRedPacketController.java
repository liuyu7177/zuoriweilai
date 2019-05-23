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

    @RequestMapping(value = "/grabRedPacket", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> grabRedPacket(Long redPackId, Long userId) {
        //int result = userRedPacketService.grabRedPacket(redPackId, userId);
        int result = userRedPacketService.grabRedPacketForVersion(redPackId, userId);
        return ApiSuccess("受影响的行数："+result, result);

    }


}
