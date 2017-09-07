package com.xier.sesame.attence.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.utils.Pagination;
import com.moredian.fishnet.device.enums.DeviceType;
import com.moredian.fishnet.device.model.CameraDeviceInfo;
import com.moredian.fishnet.device.model.CameraDeviceInfoVo;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.model.DeviceStateInfo;
import com.moredian.fishnet.device.request.DeviceQueryRequest;
import com.moredian.fishnet.device.request.StatusRequest;
import com.moredian.fishnet.device.service.CameraDeviceService;
import com.moredian.fishnet.device.service.DeviceGroupRelationService;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.member.service.MemberService;
import com.moredian.fishnet.org.model.GroupInfo;
import com.moredian.fishnet.org.model.OrgInfo;
import com.moredian.fishnet.org.request.GroupQueryRequest;
import com.moredian.fishnet.org.service.DeptService;
import com.moredian.fishnet.org.service.GroupService;
import com.moredian.fishnet.org.service.OrgService;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.dto.RecognizeDailyReportDto;
import com.xier.sesame.attence.dto.RecognizeRecordViewDto;
import com.xier.sesame.attence.service.NewSwitchSettingService;
import com.xier.sesame.attence.service.RecognizeDailyReportService;
import com.xier.sesame.attence.service.RecognizeRecordService;
import com.xier.sesame.attence.web.controller.resp.*;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.common.web.BaseResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xxu on 2017/5/4.
 */
@RestController
@RequestMapping("/attence/management")
public class AttenceManagementController extends BaseController {

    @Reference
    private RecognizeRecordService recognizeRecordService;

    @Reference
    private RecognizeDailyReportService recognizeDailyReportService;

    @Autowired
    private ImageFileManager imageFileManager;

    @Reference
    private NewSwitchSettingService switchSettingService;


    @Reference
    private OrgService orgService;

    @Reference
    private DeptService deptService;

    @Reference
    private MemberService memberService;

    @Reference
    private GroupService groupService;

    @Reference
    private DeviceService deviceService;

    @Reference
    private  DeviceGroupRelationService deviceGroupRelationService;

    @Reference
    private CameraDeviceService cameraDeviceService;

    public static final Integer ONLINE_STATUS=1;

    public static final Integer OFFLINE_STATUS=0;



    /**
     * get all switchsettings according to orgId
     *
     * @param orgId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @ApiOperation(value = "管理平台获取公司的群组信息", notes = "根据机构Id来获取机构的群组信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "pageSize", value = "分页数据，每页的数据条数", required = true, dataType = "Integer", paramType = "query"), @ApiImplicitParam(name = "pageNo", value = "分页数据，第几页的数据", required = true, dataType = "Integer", paramType = "query"), @ApiImplicitParam(name = "groupName", value = "群组的名字，支持模糊查询", required = false, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public BaseDataResponse getAllGroupSettings(@ApiParam(required = true, name = "orgId", value = "orgId") @RequestParam(required = true, value = "orgId") Long orgId, @ApiParam(required = true, name = "pageSize", value = "pageSize") @RequestParam(required = true, value = "pageSize") Integer pageSize, @ApiParam(required = true, name = "pageNo", value = "pageNo") @RequestParam(required = true, value = "pageNo") Integer pageNo, @ApiParam(required = false, name = "groupName", value = "groupName") @RequestParam(required = false, value = "groupName") String groupName) {

        BaseDataResponse bdr = new BaseDataResponse();

        //first,取得全员组信息
        GroupQueryRequest request = new GroupQueryRequest();
        request.setOrgId(orgId);
        if (StringUtils.isNotEmpty(groupName)) {
            request.setGroupName(groupName);
        }

        Pagination<GroupInfo> paginationGroup = getPaginationGroupInfoByOrgId(pageSize, pageNo, request);

        Pagination<GroupInfoWithSetting> paginationResult = new Pagination<>();
        if (paginationGroup != null) {
            paginationResult = buildGroupInfoSettingPagination(paginationGroup, orgId);
        }


        bdr.setData(paginationResult);
        bdr.setResult("0");

        bdr.setMessage("操作成功");

        return bdr;
    }

    @ApiOperation(value = " 获取设备详细信息", notes = "根据机构Id和设备ID来获取设备的详细信息")
    @RequestMapping(value = "/devices/info", method = RequestMethod.GET)
    public BaseDataResponse getAllDevices(@ApiParam(required = true, name = "orgId", value = "orgId") @RequestParam(required = true, value = "orgId") Long orgId,
                                          @ApiParam(required = true, name = "deviceId", value = "deviceId") @RequestParam(required = true, value = "deviceId") Long deviceId
                                          ) {
        BaseDataResponse bdr = new BaseDataResponse();
        DeviceInfoWithGroup deviceInfoWithGroup=new DeviceInfoWithGroup();
        DeviceInfo result = deviceService.getDeviceById(orgId,deviceId);
        List<com.moredian.fishnet.device.model.GroupInfo> groupLists=deviceGroupRelationService.findGroupByDeviceId(orgId,deviceId);

        deviceInfoWithGroup.setDeviceInfo(result);
        deviceInfoWithGroup.setGroupInfos(groupLists);
        //box device need return camera info
        if(DeviceType.BOARD_BOX.getValue()==result.getDeviceType()){
            CameraDeviceInfo cameraDeviceInfo=cameraDeviceService.getCameraDeviceByBoxId(deviceId,orgId);

            CameraDeviceInfoVo cameraDeviceInfoVo=new CameraDeviceInfoVo();
            if(cameraDeviceInfo!=null) {
                cameraDeviceInfoVo.setCameraResolution(cameraDeviceInfo.getCameraResolution());
                cameraDeviceInfoVo.setOrgId(cameraDeviceInfo.getOrgId());
                cameraDeviceInfoVo.setCameraStream(cameraDeviceInfo.getCameraStream());
                cameraDeviceInfoVo.setProviderType(cameraDeviceInfo.getProviderType());
                cameraDeviceInfoVo.setCameraId(cameraDeviceInfo.getDeviceId());
                cameraDeviceInfoVo.setCameraName(cameraDeviceInfo.getDeviceName());
            }
            deviceInfoWithGroup.setCamera(cameraDeviceInfoVo);
        }



        if(result!=null){
            bdr.setData(deviceInfoWithGroup);
            bdr.setResult("0");
            bdr.setMessage("操作成功");
        }else{
            bdr.setData(deviceInfoWithGroup);
            bdr.setResult("1");
            bdr.setMessage("操作失败");
        }
        return bdr;
    }


    @ApiOperation(value = "管理平台获取所有设备的相关信息", notes = "根据机构Id来获取机构的群组信息并提供多种条件查询")
    @RequestMapping(value = "/allTypeDevices", method = RequestMethod.GET)
    public BaseDataResponse getAllTypeDevices(@ApiParam(required = true, name = "orgId", value = "orgId") @RequestParam(required = true, value = "orgId") Long orgId, @ApiParam(required = true, name = "pageSize", value = "pageSize") @RequestParam(required = true, value = "pageSize") Integer pageSize, @ApiParam(required = true, name = "pageNo", value = "pageNo") @RequestParam(required = true, value = "pageNo") Integer pageNo, @ApiParam(required = false, name = "status", value = "status") @RequestParam(required = false, value = "status") Integer status, @ApiParam(required = false, name = "keywords", value = "keywords") @RequestParam(required = false, value = "keywords") String keywords) {

        BaseDataResponse bdr = new BaseDataResponse();

        //first,取到device列表
        Pagination<DeviceInfo> searchPagination = new Pagination<DeviceInfo>();
        searchPagination.setPageNo(pageNo);
        searchPagination.setPageSize(pageSize);

        DeviceQueryRequest request = new DeviceQueryRequest();
        request.setOrgId(orgId);
        request.setStatus(status);
        request.setKeywords(keywords);
//        request.setDeviceType(DeviceType.BOARD_ATTENDANCE.getValue());
        List<Integer> deviceTypeList=new ArrayList<>();
        deviceTypeList.add(DeviceType.BOARD_BOX.getValue());
        deviceTypeList.add(DeviceType.BOARD_ATTENDANCE.getValue());
        deviceTypeList.add(DeviceType.BOARD_ATTENDANCE_DUALEYE.getValue());
        request.setDeviceTypeList(deviceTypeList);

        Pagination<DeviceInfo> result = deviceService.findPaginationDevice(searchPagination, request);
        Pagination<AttenceDeviceInfo> attenceResult = new Pagination<>();

        List<AttenceDeviceInfo> deviceInfoList=new ArrayList<>();

        String snArray="";

        if(result!=null && result.getData()!=null){
            snArray=getDeviceSnRequestStr(result.getData());
            deviceInfoList=buildAttenceDeviceInfoList(result.getData());
        }
        StatusRequest statusRequest=new StatusRequest();
        statusRequest.setOrgId(orgId);
        statusRequest.setSnArray(snArray);

        List<DeviceStateInfo> deviceStateInfos=deviceService.getStatusList(statusRequest);
        for(AttenceDeviceInfo deviceInfo:deviceInfoList){
            for(DeviceStateInfo deviceStateInfo:deviceStateInfos){
                if(deviceInfo.getDeviceSn()!=null && deviceStateInfo.getSerialNumber()!=null && deviceInfo.getDeviceSn().equals(deviceStateInfo.getSerialNumber())){
                    if(BooleanUtils.isTrue(deviceStateInfo.getOnline())){
                        deviceInfo.setNetState(ONLINE_STATUS);
                    }else if(BooleanUtils.isFalse(deviceStateInfo.getOnline())){
                        deviceInfo.setNetState(OFFLINE_STATUS);
                    }
                    deviceInfo.setProgress(deviceStateInfo.getProgress());
                }
            }
        }

        attenceResult.setData(deviceInfoList);
        attenceResult.setPageSize(result.getPageSize());
        attenceResult.setPageNo(result.getPageNo());
        attenceResult.setTotalCount(result.getTotalCount());

        bdr.setData(attenceResult);
        bdr.setResult("0");

        bdr.setMessage("操作成功");

        return bdr;
    }



    @ApiOperation(value = "管理平台获取所有设备的相关信息", notes = "根据机构Id来获取机构的群组信息并提供多种条件查询")
    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public BaseDataResponse getAllDevices(@ApiParam(required = true, name = "orgId", value = "orgId") @RequestParam(required = true, value = "orgId") Long orgId, @ApiParam(required = true, name = "pageSize", value = "pageSize") @RequestParam(required = true, value = "pageSize") Integer pageSize, @ApiParam(required = true, name = "pageNo", value = "pageNo") @RequestParam(required = true, value = "pageNo") Integer pageNo, @ApiParam(required = false, name = "status", value = "status") @RequestParam(required = false, value = "status") Integer status, @ApiParam(required = false, name = "keywords", value = "keywords") @RequestParam(required = false, value = "keywords") String keywords) {

        BaseDataResponse bdr = new BaseDataResponse();

        //first,取到device列表
        Pagination<DeviceInfo> searchPagination = new Pagination<DeviceInfo>();
        searchPagination.setPageNo(pageNo);
        searchPagination.setPageSize(pageSize);

        DeviceQueryRequest request = new DeviceQueryRequest();
        request.setOrgId(orgId);
        request.setStatus(status);
        request.setKeywords(keywords);
//        request.setDeviceType(DeviceType.BOARD_ATTENDANCE.getValue());
        //        request.setDeviceType(DeviceType.BOARD_ATTENDANCE.getValue());
        List<Integer> deviceTypeList=new ArrayList<>();
        deviceTypeList.add(DeviceType.BOARD_BOX.getValue());
        deviceTypeList.add(DeviceType.BOARD_ATTENDANCE.getValue());
        request.setDeviceTypeList(deviceTypeList);


        Pagination<DeviceInfo> result = deviceService.findPaginationDevice(searchPagination, request);
        Pagination<AttenceDeviceInfo> attenceResult = new Pagination<>();

        List<AttenceDeviceInfo> deviceInfoList=new ArrayList<>();

        String snArray="";

        if(result!=null && result.getData()!=null){
            snArray=getDeviceSnRequestStr(result.getData());
            deviceInfoList=buildAttenceDeviceInfoList(result.getData());
        }
        StatusRequest statusRequest=new StatusRequest();
        statusRequest.setOrgId(orgId);
        statusRequest.setSnArray(snArray);

        List<DeviceStateInfo> deviceStateInfos=deviceService.getStatusList(statusRequest);
        for(AttenceDeviceInfo deviceInfo:deviceInfoList){
            for(DeviceStateInfo deviceStateInfo:deviceStateInfos){
                if(deviceInfo.getDeviceSn()!=null && deviceStateInfo.getSerialNumber()!=null && deviceInfo.getDeviceSn().equals(deviceStateInfo.getSerialNumber())){
                    if(BooleanUtils.isTrue(deviceStateInfo.getOnline())){
                        deviceInfo.setNetState(ONLINE_STATUS);
                    }else if(BooleanUtils.isFalse(deviceStateInfo.getOnline())){
                        deviceInfo.setNetState(OFFLINE_STATUS);
                    }
                    deviceInfo.setProgress(deviceStateInfo.getProgress());
                }
            }
        }

        attenceResult.setData(deviceInfoList);
        attenceResult.setPageSize(result.getPageSize());
        attenceResult.setPageNo(result.getPageNo());
        attenceResult.setTotalCount(result.getTotalCount());

        bdr.setData(attenceResult);
        bdr.setResult("0");

        bdr.setMessage("操作成功");

        return bdr;
    }


    private List<AttenceDeviceInfo> buildAttenceDeviceInfoList(List<DeviceInfo> deviceInfoList){
        List<AttenceDeviceInfo> attenceDeviceInfos=new ArrayList<>();
        for(DeviceInfo deviceInfo:deviceInfoList){
            AttenceDeviceInfo attenceDeviceInfo=new AttenceDeviceInfo();
            attenceDeviceInfo.setDeviceType(deviceInfo.getDeviceType());
            attenceDeviceInfo.setOrgId(deviceInfo.getOrgId());
            attenceDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
            attenceDeviceInfo.setDeviceSn(deviceInfo.getDeviceSn());
            attenceDeviceInfo.setVersion(deviceInfo.getVersion());
            attenceDeviceInfo.setStatus(deviceInfo.getStatus());
            attenceDeviceInfo.setDeviceId(deviceInfo.getDeviceId());

            attenceDeviceInfos.add(attenceDeviceInfo);
        }

        return attenceDeviceInfos;
    }

    private String getDeviceSnRequestStr(List<DeviceInfo> deviceInfoList){
        StringBuilder str=new StringBuilder();
        for(DeviceInfo deviceInfo:deviceInfoList){
            str.append(deviceInfo.getDeviceSn()).append(",");
        }
        if(str.length()>1){
            str.deleteCharAt(str.length()-1);
        }

        return str.toString();
    }


    @ApiOperation(value = "管理平台获取公司的信息", notes = "根据机构Id来获取机构的信息和识别人数统计")
    @ApiImplicitParams({@ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "startTime", value = "查询开始时间", required = true, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "endTime", value = "查询结束时间", required = true, dataType = "String", paramType = "query"),})
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public BaseDataResponse getAllGroupSettings(@RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "startTime") String startTime, @RequestParam(required = true, value = "endTime") String endTime) {

        BaseDataResponse bdr = new BaseDataResponse();

        //dailyreport table only contains data before today
        //need to get today's data from recognizerecord table
        ServiceResponse<RecognizeDailyReportDto> sr = recognizeDailyReportService.getRecordCountByTimeRangeAndOrgId(startTime, endTime, orgId);

        Integer totalTimes = 0;
        Integer stangerTimes = 0;
        Integer visitorTimes = 0;
        Integer employeeTimes = 0;

        AttenceManagementHomePageData homePageData = new AttenceManagementHomePageData();
        homePageData.setOrgId(orgId);

        if (sr.isSuccess() && sr.isExistData()) {
            RecognizeDailyReportDto recognizeDailyReportDto = sr.getData();
            stangerTimes += parseTimes(recognizeDailyReportDto.getStangerTimes());
            totalTimes += parseTimes(recognizeDailyReportDto.getTotalTimes());
            visitorTimes += parseTimes(recognizeDailyReportDto.getVisitorTimes());
            employeeTimes += parseTimes(recognizeDailyReportDto.getEmployeeTimes());
        }


        SimpleDateFormat endFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long start = null;
        Long end = null;

        try {
            end = endFormat.parse(endTime).getTime();
            start = startFormat.parse(endTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //get today's record
        ServiceResponse<RecognizeDailyReportDto> sourceSr = recognizeDailyReportService.getSourceRecordCountByTimeRangeAndOrgId(start, end, orgId);

        if (sourceSr.isSuccess() && sourceSr.isExistData()) {
            RecognizeDailyReportDto recognizeDailyReportDto = sourceSr.getData();
            stangerTimes += parseTimes(recognizeDailyReportDto.getStangerTimes());
            totalTimes += parseTimes(recognizeDailyReportDto.getTotalTimes());
            visitorTimes += parseTimes(recognizeDailyReportDto.getVisitorTimes());
            employeeTimes += parseTimes(recognizeDailyReportDto.getEmployeeTimes());

        }

        homePageData.setTotalTimes(totalTimes);
        homePageData.setStangerTimes(stangerTimes);
        homePageData.setEmployeeTimes(employeeTimes);
        homePageData.setVisitorTimes(visitorTimes);

        //get membernumber
        homePageData.setMemberNumber(memberService.countMember(orgId));
        homePageData.setDepartmentNumber(deptService.countDept(orgId));
        OrgInfo orgInfo = orgService.getOrgInfo(orgId);
        if (orgInfo != null) {
            homePageData.setOrgName(orgInfo.getOrgName());
            String logoUrl = orgInfo.getLogoUrl();
            homePageData.setOrgLogo(imageFileManager.getImageUrl(logoUrl));
        }


        bdr.setData(homePageData);

        return bdr;

    }


    private Integer parseTimes(Object o) {
        if (o == null) {
            return 0;
        } else {
            return (Integer) o;
        }
    }

    @ApiOperation(value = "管理员获取公司的历史识别记录", notes = "根据url的机构Id来获取机构的信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "userType", value = "用户类型", required = false, dataType = "Integer", paramType = "query"), @ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "beginDate", value = "查询开始时间", required = true, dataType = "Date", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "查询结束时间", required = true, dataType = "Date", paramType = "query"), @ApiImplicitParam(name = "pageNo", value = "分页第几页", required = true, dataType = "Integer", paramType = "query"), @ApiImplicitParam(name = "pageSize", value = "分页每页的数据条数", required = true, dataType = "Integer", paramType = "query"),})
    @RequestMapping(value = "/company/records", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCompanyRecords(@RequestParam(required = false, value = "userName") String userName, @RequestParam(required = false, value = "userType") Integer userType, @RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(required = true, value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam(required = true, value = "pageNo") Integer pageNo, @RequestParam(required = true, value = "pageSize") Integer pageSize) {

        BaseDataResponse<CompanyRecognizeRecordWarp> bdr = new BaseDataResponse<>();
        CompanyRecognizeRecordWarp dataWarp = new CompanyRecognizeRecordWarp();
        List<CompanyRecognizeRecord> recognizeList = new ArrayList<>();


        PaginationDto<RecognizeRecordViewDto> paginationDto = buildPaginationDto(pageNo, pageSize);
        RecognizeRecordViewDto recognizeRecordViewDto = buildRecognizeRecordDto(orgId, null, beginDate, endDate, userName, userType);
        ServiceResponse<PaginationDto<RecognizeRecordViewDto>> sr = recognizeRecordService.getPaginationRecognizeRecordView(paginationDto, recognizeRecordViewDto);
        if (sr.isSuccess() && sr.isExistData() && CollectionUtils.isNotEmpty(sr.getData().getData())) {
            dataWarp.setPageNo(sr.getData().getPageNo());
            dataWarp.setTotalCount(sr.getData().getTotalCount());
            recognizeList = this.buildCompanyRecognizeList(sr.getData().getData());
        }
        dataWarp.setRecognizeList(recognizeList);
        bdr.setData(dataWarp);

        return bdr;
    }


    @ApiOperation(value = "获取用户历史识别记录", notes = "根据url的id来获取用户的信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"), @ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long"), @ApiImplicitParam(name = "beginDate", value = "查询开始时间", required = true, dataType = "Date"), @ApiImplicitParam(name = "endDate", value = "查询结束时间", required = true, dataType = "Date"), @ApiImplicitParam(name = "pageNo", value = "分页第几页", required = true, dataType = "Integer"), @ApiImplicitParam(name = "pageSize", value = "分页每页的数据条数", required = true, dataType = "Integer"),})
    @RequestMapping(value = "/employee/records", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getPersonalRecords(@RequestParam(required = true, value = "userId") Long userId, @RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(required = true, value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam(required = true, value = "pageNo") Integer pageNo, @RequestParam(required = true, value = "pageSize") Integer pageSize) {

        BaseDataResponse<AttenceRecognizeRecordWarp> bdr = new BaseDataResponse<>();
        AttenceRecognizeRecordWarp dataWarp = new AttenceRecognizeRecordWarp();
        List<AttenceRecognizeRecord> recognizeList = new ArrayList<>();


        PaginationDto<RecognizeRecordViewDto> paginationDto = buildPaginationDto(pageNo, pageSize);
        RecognizeRecordViewDto recognizeRecordViewDto = buildRecognizeRecordDto(orgId, userId, beginDate, endDate, null, 1);
        ServiceResponse<PaginationDto<RecognizeRecordViewDto>> sr = recognizeRecordService.getPaginationRecognizeRecordView(paginationDto, recognizeRecordViewDto);
        if (sr.isSuccess() && sr.isExistData() && CollectionUtils.isNotEmpty(sr.getData().getData())) {
            dataWarp.setPageNo(sr.getData().getPageNo());
            dataWarp.setTotalCount(sr.getData().getTotalCount());
            recognizeList = this.buildAttenceRecognizeRecordList(sr.getData().getData());
        }

        dataWarp.setRecognizeList(recognizeList);
        bdr.setData(dataWarp);

        return bdr;
    }


    private PaginationDto<RecognizeRecordViewDto> buildPaginationDto(int pageNo, int pageSize) {
        PaginationDto<RecognizeRecordViewDto> dto = new PaginationDto<RecognizeRecordViewDto>();
        dto.setPageNo(pageNo);
        dto.setPageSize(pageSize);
        return dto;
    }

    private RecognizeRecordViewDto buildRecognizeRecordDto(Long orgId, Long userId, Date beginDate, Date endDate, String userName, Integer userType) {
        RecognizeRecordViewDto dto = new RecognizeRecordViewDto();
        dto.setOrgId(orgId);
        if (userId != null) {
            dto.setUserId(userId);
        }
        //front end use one parameter to search user/department
        if (StringUtils.isNotEmpty(userName)) {
            dto.setRealName(userName);
            dto.setDepartment(userName);
        }
        if (userType != null) {
            dto.setRecognizeUserType(userType);
        }
        dto.setSearchBeginDate(beginDate.getTime());
        dto.setSearchEndDate(caculateEndDate(endDate).getTime());
//        dto.setRecognizeType(20);

        return dto;
    }

    private Date caculateEndDate(Date endDate) {
        // 将传来的日期从00:00调整为23:59分
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    private List<AttenceRecognizeRecord> buildAttenceRecognizeRecordList(List<RecognizeRecordViewDto> recognizeRecordViewDtoList) {
        List<AttenceRecognizeRecord> list = new ArrayList<>();
        for (RecognizeRecordViewDto dto : recognizeRecordViewDtoList) {
            AttenceRecognizeRecord item = new AttenceRecognizeRecord();
            item.setRecoId(dto.getRecognizeRecordId());
            item.setCatchImg(imageFileManager.getImageUrl(dto.getCatchImageUrl()));
            item.setDeviceName(dto.getDeviceName());
            item.setRecoTime(dto.getRecognizeTime());
            item.setRealName(dto.getRealName());
            item.setDepartment(dto.getDepartment());
            item.setRecognizeUserType(dto.getRecognizeUserType());
            if (dto.getMatchImageUrl() != null) {
                item.setOpenDoorImag(imageFileManager.getImageUrl(dto.getMatchImageUrl()));
            }
            list.add(item);
        }
        return list;
    }


    private List<CompanyRecognizeRecord> buildCompanyRecognizeList(List<RecognizeRecordViewDto> recognizeRecordViewDtoList) {
        List<CompanyRecognizeRecord> list = new ArrayList<>();
        for (RecognizeRecordViewDto dto : recognizeRecordViewDtoList) {
            CompanyRecognizeRecord item = new CompanyRecognizeRecord();
            item.setRecoId(dto.getRecognizeRecordId());
            item.setCatchImg(imageFileManager.getImageUrl(dto.getCatchImageUrl()));
            item.setDeviceName(dto.getDeviceName());
            item.setRecoTime(dto.getRecognizeTime());
            item.setRealName(dto.getRealName());
            item.setDepartment(dto.getDepartment());
            item.setOpenDoorImg(imageFileManager.getImageUrl(dto.getMatchImageUrl()));
            item.setUserType(dto.getRecognizeUserType());
            list.add(item);
        }
        return list;
    }


    /**
     * 根据orgId，取得所有group信息，包括group的信息
     *
     * @param pageSize
     * @param pageNumber
     * @param request
     * @return
     */
    private Pagination<GroupInfo> getPaginationGroupInfoByOrgId(int pageSize, int pageNumber, GroupQueryRequest request) {

        Pagination<GroupInfo> pagination = new Pagination<>();
        pagination.setPageSize(pageSize);
        pagination.setPageNo(pageNumber);
        Pagination<GroupInfo> paginationResult = groupService.findPaginationGroup(pagination, request);

        return paginationResult;
    }

    private GroupInfoWithSetting buildGroupInfoWithSetting(GroupInfo groupInfo, Long orgId) {
        GroupInfoWithSetting groupInfoWithSetting = new GroupInfoWithSetting();
        groupInfoWithSetting.setGroupId(groupInfo.getGroupId());
        groupInfoWithSetting.setOrgId(orgId);
        groupInfoWithSetting.setGroupName(groupInfo.getGroupName());
        groupInfoWithSetting.setMemberSize(groupInfo.getMemberSize());
        groupInfoWithSetting.setAllMemberFlag(groupInfo.getAllMemberFlag());
        groupInfoWithSetting.setGroupType(groupInfo.getGroupType());
        groupInfoWithSetting.setSystemDefault(groupInfo.getSystemDefault());
        return groupInfoWithSetting;

    }


    private Pagination<GroupInfoWithSetting> buildGroupInfoSettingPagination(Pagination<GroupInfo> paginationGroup, Long orgId) {
        Pagination<GroupInfoWithSetting> paginationResult = new Pagination<>();
        paginationResult.setPageNo(paginationGroup.getPageNo());
        paginationResult.setPageSize(paginationGroup.getPageSize());
        if (paginationGroup.getData() != null) {
            paginationResult.setTotalCount(paginationGroup.getTotalCount());

            List<GroupInfo> groupInfos = paginationGroup.getData();
            List<GroupInfoWithSetting> groupInfoWithSettings = new ArrayList<>();
            for (GroupInfo groupInfo : groupInfos) {
                ServiceResponse<NewSwitchSettingDto> sr = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId, groupInfo.getGroupId());

                GroupInfoWithSetting groupInfoWithSetting = buildGroupInfoWithSetting(groupInfo, orgId);
                if (sr.isSuccess() && sr.getData() != null) {
                    groupInfoWithSetting.setDayBeginTime(sr.getData().getDayBeginTime());
                    groupInfoWithSetting.setDayEndTime(sr.getData().getDayEndTime());
                    groupInfoWithSetting.setOpenWeekDays(sr.getData().getOpenWeekDays());
                }
                groupInfoWithSettings.add(groupInfoWithSetting);
            }

            paginationResult.setData(groupInfoWithSettings);

        } else {
            logger.error("/attence/management/groups get error.No groups found from group service.orgId is" + orgId);

        }

        return paginationResult;


    }


}
