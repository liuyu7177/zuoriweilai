package com.liuyu7177.zuoriweilai.web;


import com.liuyu7177.zuoriweilai.framework.exceptions.RepeatKillException;
import com.liuyu7177.zuoriweilai.framework.exceptions.SeckillCloseException;
import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.model.dto.Exposer;
import com.liuyu7177.zuoriweilai.model.dto.SeckillExecution;
import com.liuyu7177.zuoriweilai.model.entity.Seckill;
import com.liuyu7177.zuoriweilai.model.enums.SeckillStateEnum;
import com.liuyu7177.zuoriweilai.service.SeckillService;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/8
 */
@Controller
@RequestMapping("seckill")
public class SeckillController extends JsonBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            return ApiSuccess(exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiErr(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public JsonResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return ApiErr("未注册");
        }
        try {

            //SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            return ApiSuccess(execution);
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return ApiSuccess(execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            return ApiSuccess(execution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return ApiSuccess(execution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Long> time() {
        Date now = new Date();
        return ApiSuccess(now.getTime());
    }
}
