package com.winjean.zuul.mapper;


import com.github.pagehelper.Page;
import com.winjean.zuul.model.request.RequestZuulRouteInsert;
import com.winjean.zuul.model.response.ResponseZuulRouteQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZuulRouteMapper {

    @Insert("insert into t_zuul_route(id,path,service_id,url,strip_prefix,retryable,create_user,create_time,update_user,update_time)" +
            "values "+
            "(#{id},#{path},#{serviceId},#{url},#{stripPrefix},#{retryable},#{createUser},#{createTime},#{updateUser},#{updateTime})")
    int insert(RequestZuulRouteInsert request);

    @Select("select id,user_name as name,birthday,email,sex,telephone,create_user as createUser,create_time as createTime from t_user")
    Page<ResponseZuulRouteQuery> query();

}
