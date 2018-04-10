package com.og.oms.controller;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.service.IWorkService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private IWorkService workService;

    /**
     * 机房问题排行
     *
     * @return
     */
    @PermisAnnotation(id = 401, name = "机房问题排行榜")
    @RequestMapping(value = "report-serverroom")
    public JsonResult serverRoomCount() {
        return new JsonResult(this.workService.serverRoomCount());
    }

    /**
     * 平台问题报表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @PermisAnnotation(id = 403, name = "平台问题报表")
    @RequestMapping(value = "report-platform")
    public JsonResult platformReport(String startTime, String endTime) {
        return new JsonResult(this.workService.platformReport(startTime, endTime));
    }

    /**
     * 站点问题报表
     *
     * @param startTime
     * @param endTime
     * @param platform
     * @return
     */
    @PermisAnnotation(id = 404, name = "站点问题报表")
    @RequestMapping(value = "report-station")
    public JsonResult stationReport(String startTime, String endTime, Integer platform) {
        return new JsonResult(this.workService.stationReport(startTime, endTime, platform));
    }

    /**
     * 别名问题报表
     *
     * @param startTime
     * @param endTime
     * @param platform
     * @param stationCode
     * @param aliasName
     * @return
     */
    @PermisAnnotation(id = 405, name = "别名问题报表")
    @RequestMapping(value = "report-alias")
    public JsonResult aliasReport(String startTime, String endTime, Integer platform, String stationCode, String aliasName) {
        return new JsonResult(this.workService.aliasReport(startTime, endTime, platform, stationCode, aliasName));
    }
}
