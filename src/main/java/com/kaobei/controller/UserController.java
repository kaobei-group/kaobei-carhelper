package com.kaobei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaobei.commons.RestResult;
import com.kaobei.service.ParkService;
import com.kaobei.service.UserService;
import com.kaobei.utils.JwtTokenUtils;
import com.kaobei.utils.ResultUtils;
import com.kaobei.vo.GetNearParkVo;
import com.kaobei.vo.GetParkVo;
import com.kaobei.vo.GetPlateVo;
import com.kaobei.vo.SetPlateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
@PreAuthorize("hasAuthority('user')")
@RestController
public class UserController {
    @Autowired
    private ParkService parkService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping( "/getNearPark")
    public RestResult getNearPark(@Validated @RequestBody GetNearParkVo getNearParkVo){
        return ResultUtils.success(parkService.findParkListByPosNear(getNearParkVo.getLng(),getNearParkVo.getLat(),getNearParkVo.getRadius(),getNearParkVo.getCount()));
    }

    @PostMapping( "/getPark")
    public RestResult getPark(@Validated @RequestBody GetParkVo getParkVo){
        return ResultUtils.success(parkService.findParkByKeyword(getParkVo.getKeyword(),new Page(getParkVo.getPage(),getParkVo.getSize())));
    }

    @PostMapping( "/setPlate")
    public RestResult setPlate(HttpServletRequest request, @Validated @RequestBody SetPlateVo setPlateVo){
        String account = jwtTokenUtils.getAuthAccountFromRequest(request);
        //这边要验证
        return ResultUtils.success(userService.setPlate(setPlateVo,account));
    }
}