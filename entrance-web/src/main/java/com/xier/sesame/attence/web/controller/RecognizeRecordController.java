package com.xier.sesame.attence.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.sesame.attence.dto.RecognizeRecordViewDto;
import com.xier.sesame.attence.service.RecognizeRecordService;
import com.xier.sesame.attence.web.controller.resp.CompanyRecognizeRecord;
import com.xier.sesame.attence.web.controller.resp.CompanyRecognizeRecordWarp;
import com.xier.sesame.attence.web.controller.resp.EmployeeRecognizeRecord;
import com.xier.sesame.attence.web.controller.resp.EmployeeRecognizeRecordWarp;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.common.web.BaseResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xxu on 2017/4/18.
 */
@RestController
@RequestMapping("/attence/recognize")
public class RecognizeRecordController {

    @Reference
    private RecognizeRecordService recognizeRecordService;
    @Autowired
    private ImageFileManager imageFileManager;

    @ApiOperation(value="获取用户历史识别记录", notes="根据url的id来获取用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "beginDate", value = "查询开始时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "endDate", value = "查询结束时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "pageNo", value = "分页第几页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页每页的数据条数", required = true, dataType = "Integer"),
    })
    @RequestMapping("/employee/records")
    @ResponseBody
    public BaseResponse getPersonalRecords(@RequestParam(required = true, value = "userId") Long userId,
                             @RequestParam(required = true, value = "orgId") Long orgId,
                             @RequestParam(required = true, value = "beginDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                             @RequestParam(required = true, value = "endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                             @RequestParam(required = true, value = "pageNo") Integer pageNo,
                             @RequestParam(required = true, value = "pageSize") Integer pageSize) {

        BaseDataResponse<EmployeeRecognizeRecordWarp> bdr = new BaseDataResponse<>();
        EmployeeRecognizeRecordWarp dataWarp = new EmployeeRecognizeRecordWarp();
        List<EmployeeRecognizeRecord> recognizeList = new ArrayList<>();


        PaginationDto<RecognizeRecordViewDto> paginationDto=buildPaginationDto(pageNo,pageSize);
        RecognizeRecordViewDto recognizeRecordViewDto=buildRecognizeRecordDto(orgId,userId,beginDate,endDate,null);
        ServiceResponse<PaginationDto<RecognizeRecordViewDto>> sr = recognizeRecordService.getPaginationRecognizeRecordView(
                paginationDto, recognizeRecordViewDto);
        if(sr.isSuccess() && sr.isExistData() && CollectionUtils.isNotEmpty(sr.getData().getData())){
            dataWarp.setPageNo(sr.getData().getPageNo());
            dataWarp.setTotalCount(sr.getData().getTotalCount());
            recognizeList = this.buildEmployeeRecognizeList(sr.getData().getData());
        }

        dataWarp.setRecognizeList(recognizeList);
        bdr.setData(dataWarp);

        return bdr;
    }


    @ApiOperation(value="管理员获取公司的历史识别记录", notes="根据url的机构Id来获取机构的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "orgId", value = "机构ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "beginDate", value = "查询开始时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "endDate", value = "查询结束时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "pageNo", value = "分页第几页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页每页的数据条数", required = true, dataType = "Integer"),
    })
    @RequestMapping("/company/records")
    @ResponseBody
    public BaseResponse getCompanyRecords(@RequestParam(required = false, value = "userName") String userName,
                             @RequestParam(required = false, value = "departId") String departId,
                             @RequestParam(required = false, value = "userType") Integer userType,
                             @RequestParam(required = true, value = "orgId") Long orgId,
                             @RequestParam(required = true, value = "beginDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                             @RequestParam(required = true, value = "endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                             @RequestParam(required = true, value = "pageNo") Integer pageNo,
                             @RequestParam(required = true, value = "pageSize") Integer pageSize) {

        BaseDataResponse<CompanyRecognizeRecordWarp> bdr = new BaseDataResponse<>();
        CompanyRecognizeRecordWarp dataWarp = new CompanyRecognizeRecordWarp();
        List<CompanyRecognizeRecord> recognizeList = new ArrayList<>();


        PaginationDto<RecognizeRecordViewDto> paginationDto=buildPaginationDto(pageNo,pageSize);
        RecognizeRecordViewDto recognizeRecordViewDto=buildCompanyRecognizeRecordDto(orgId,null,beginDate,endDate,userName,departId,userType);
        ServiceResponse<PaginationDto<RecognizeRecordViewDto>> sr = recognizeRecordService.getH5CompanyPaginationRecognizeRecordView(
                paginationDto, recognizeRecordViewDto);
        if(sr.isSuccess() && sr.isExistData() && CollectionUtils.isNotEmpty(sr.getData().getData())){
            dataWarp.setPageNo(sr.getData().getPageNo());
            dataWarp.setTotalCount(sr.getData().getTotalCount());
            recognizeList = this.buildCompanyRecognizeList(sr.getData().getData());
        }
        dataWarp.setRecognizeList(recognizeList);
        bdr.setData(dataWarp);

        return bdr;
    }

    private PaginationDto<RecognizeRecordViewDto> buildPaginationDto(int pageNo,int pageSize){
        PaginationDto<RecognizeRecordViewDto> dto = new PaginationDto<RecognizeRecordViewDto>();
        dto.setPageNo(pageNo);
        dto.setPageSize(pageSize);
        return dto;
    }

    private RecognizeRecordViewDto buildCompanyRecognizeRecordDto(Long orgId,Long userId,Date beginDate,Date endDate,String userName,String departmentId,Integer userType) {
        RecognizeRecordViewDto dto = new RecognizeRecordViewDto();
        dto.setOrgId(orgId);
        if(userId!=null){
            dto.setUserId(userId);
        }
        //front end use one parameter to search user/department
        if(StringUtils.isNotEmpty(userName)){
            dto.setRealName(userName);

        }
        if(StringUtils.isNotEmpty(departmentId)){
            dto.setDepartmentId(departmentId);
        }
        if(userType!=null){
            dto.setRecognizeUserType(userType);
        }
        dto.setSearchBeginDate(beginDate.getTime());
        dto.setSearchEndDate(caculateEndDate(endDate).getTime());
//        dto.setRecognizeUserType(1);
//        dto.setRecognizeType(20);

        return dto;
    }

    private RecognizeRecordViewDto buildRecognizeRecordDto(Long orgId,Long userId,Date beginDate,Date endDate,String userName) {
        RecognizeRecordViewDto dto = new RecognizeRecordViewDto();
        dto.setOrgId(orgId);
        if(userId!=null){
            dto.setUserId(userId);
        }
        //front end use one parameter to search user/department
        if(StringUtils.isNotEmpty(userName)){
            dto.setRealName(userName);
            dto.setDepartment(userName);
        }
        dto.setSearchBeginDate(beginDate.getTime());
        dto.setSearchEndDate(caculateEndDate(endDate).getTime());
        dto.setRecognizeUserType(1);
        dto.setRecognizeType(20);

        return dto;
    }

    private Date caculateEndDate(Date endDate){
            // 将传来的日期从00:00调整为23:59分
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.set(Calendar.HOUR_OF_DAY, 24);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.MILLISECOND, -1);
            return cal.getTime();
    }

    private List<EmployeeRecognizeRecord> buildEmployeeRecognizeList(List<RecognizeRecordViewDto> recognizeRecordViewDtoList){
        List<EmployeeRecognizeRecord> list = new ArrayList<>();
        for(RecognizeRecordViewDto dto: recognizeRecordViewDtoList) {
            EmployeeRecognizeRecord item = new EmployeeRecognizeRecord();
            item.setRecoId(dto.getRecognizeRecordId());
            item.setCatchImg(imageFileManager.getImageUrl(dto.getCatchImageUrl()));
            item.setDeviceName(dto.getDeviceName());
            item.setRecoTime(dto.getRecognizeTime());
            list.add(item);
        }
        return list;
    }

    private List<CompanyRecognizeRecord> buildCompanyRecognizeList(List<RecognizeRecordViewDto> recognizeRecordViewDtoList){
        List<CompanyRecognizeRecord> list = new ArrayList<>();
        for(RecognizeRecordViewDto dto: recognizeRecordViewDtoList) {
            CompanyRecognizeRecord item = new CompanyRecognizeRecord();
            item.setRecoId(dto.getRecognizeRecordId());
            item.setCatchImg(imageFileManager.getImageUrl(dto.getCatchImageUrl()));
            item.setDeviceName(dto.getDeviceName());
            item.setRecoTime(dto.getRecognizeTime());
            item.setRealName(dto.getRealName());
            item.setDepartment(dto.getDepartment());
            list.add(item);
        }
        return list;
    }


}
