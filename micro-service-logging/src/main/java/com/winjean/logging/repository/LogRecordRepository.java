package com.winjean.logging.repository;

import com.winjean.logging.domain.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord,Long>, JpaSpecificationExecutor<LogRecord> {

    /**
     * 获取一个时间段的IP记录
     * @param date1
     * @param date2
     * @return
     */
    @Query(value = "select count(*) FROM (select request_ip FROM log where create_time between ?1 and ?2 GROUP BY request_ip) as s",nativeQuery = true)
    Long findIp(String date1, String date2);


    @Query(value = "delete from log where create_time < date_add(now(), interval -?1 day);",nativeQuery = true)
    Long deleteByCreateDate(int days);

}
