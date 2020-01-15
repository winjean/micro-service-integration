package com.winjean.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> extends BaseResponse {
//TODO delete

    @JacksonXmlProperty
    private int pageNum;

    @JacksonXmlProperty
    private int pageSize;

    @JacksonXmlProperty
    private int pages;

    @JacksonXmlProperty
    private long total;

    @JacksonXmlProperty
    private List<T> list;

    public static PageResponse getResponse(int pageNum, int pageSize,int  total,int pages, List list){
        PageResponse response = new PageResponse();
        response.setCode(BaseResponse.INVOKE_SUCCESS_CODE);
        response.setMsg(BaseResponse.INVOKE_SUCCESS);
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);
        response.setTotal(total);
        response.setPages(pages);

        response.setList(list);

        return response;
    }

}
