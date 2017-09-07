package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.SubjectDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 门禁主题数据转换器
 * @author zhutx
 */
public class SubjectConvertor {
	
	
	public static SubjectDto subjectToSubjectDto(Subject subject) {
		if (subject == null)
			return null;
		SubjectDto subjectDto = new SubjectDto();		
		subjectDto.setSubjectId(subject.getSubjectId());
		subjectDto.setSubjectCode(subject.getSubjectCode());
		subjectDto.setSubjectName(subject.getSubjectName());
		subjectDto.setShowImg(subject.getShowImg());
		subjectDto.setDefaultFlag(subject.getDefaultFlag());
		subjectDto.setStatus(subject.getStatus());
		subjectDto.setGmtCreate(subject.getGmtCreate());
		subjectDto.setGmtModify(subject.getGmtModify());
		return subjectDto;
	}
	
	public static Subject subjectDtoToSubject(SubjectDto subjectDto) {
		if (subjectDto == null)
			return null;
		Subject subject = new Subject();		
		subject.setSubjectId(subjectDto.getSubjectId());
		subject.setSubjectCode(subjectDto.getSubjectCode());
		subject.setSubjectName(subjectDto.getSubjectName());
		subject.setShowImg(subjectDto.getShowImg());
		subject.setDefaultFlag(subjectDto.getDefaultFlag());
		subject.setStatus(subjectDto.getStatus());
		subject.setGmtCreate(subjectDto.getGmtCreate());
		subject.setGmtModify(subjectDto.getGmtModify());
		return subject;
	}	
	
	public static List<SubjectDto> subjectListToSubjectDtoList(List<Subject> subjectList) {
		if (subjectList == null)
			return null;
		List<SubjectDto> SubjectDtoList = new ArrayList<SubjectDto>();
		for (Subject subject : subjectList) {
			SubjectDtoList.add(subjectToSubjectDto(subject));
		}
		return SubjectDtoList;
	}
	
	public static List<Subject> subjectDtoListToSubjectList(List<SubjectDto> subjectDtoList) {
		if (subjectDtoList == null)
			return null;
		List<Subject> SubjectList = new ArrayList<Subject>();
		for (SubjectDto subjectDto : subjectDtoList) {
			SubjectList.add(subjectDtoToSubject(subjectDto));
		}
		return SubjectList;
	}
	
	public static PaginationDto<SubjectDto> paginationSubjectToPaginationSubjectDto(Pagination<Subject> pagination) {
		PaginationDto<SubjectDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<SubjectDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(subjectListToSubjectDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<Subject> paginationSubjectDtoToPaginationSubject(PaginationDto<SubjectDto> paginationDto) {
		Pagination<Subject> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<Subject>());
		if (pagination == null)
			return null;
		pagination.setData(subjectDtoListToSubjectList(paginationDto.getData()));
		return pagination;
	}
	
}