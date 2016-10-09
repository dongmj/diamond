package com.taobao.diamond.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taobao.diamond.domain.ConfigInfo;
import com.taobao.diamond.server.service.ConfigService;
import com.taobao.diamond.server.utils.DiamondUtils;

/**
 * 开放接口
 * 
 * @author dongmingjun
 */
@Controller
@RequestMapping("/api.do")
public class APIController {
    @Autowired
    private ConfigService configService;
	
	@RequestMapping(params = "method=upsertConfig", method = RequestMethod.POST)
	public String upsertConfig(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("dataId") String dataId, @RequestParam("group") String group,
            @RequestParam("content") String content, ModelMap modelMap) {
		boolean checkSuccess = true;
        String errorMessage = "fail:参数错误";
        if (StringUtils.isBlank(dataId) || DiamondUtils.hasInvalidChar(dataId.trim())) {
            checkSuccess = false;
            errorMessage = "fail:无效的DataId";
        }
        if (StringUtils.isBlank(group) || DiamondUtils.hasInvalidChar(group.trim())) {
            checkSuccess = false;
            errorMessage = "fail:无效的分组";
        }
        if (StringUtils.isBlank(content)) {
            checkSuccess = false;
            errorMessage = "fail:无效的内容";
        }
        if (!checkSuccess) {
            modelMap.addAttribute("message", errorMessage);
            return "admin/config/upsert";
        }
        
        ConfigInfo config = configService.findConfigInfo(dataId, group);
        if(config == null) {
        	this.configService.addConfigInfo(dataId, group, content);
        } else {
        	this.configService.updateConfigInfo(dataId, group, content);
        }
        
        return "admin/config/upsert";
	}
}
