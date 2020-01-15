package com.winjean.logging.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "t_log_record")
@NoArgsConstructor
public class LogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 描述
     */
    private String description;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    @Column(columnDefinition = "text")
    private String params;

    /**
     * 日志类型
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 请求ip
     */
    @Column(name = "request_ip")
    private String requestIp;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 异常详细
     */
    @Column(name = "exception_detail", columnDefinition = "text")
    private String exceptionDetail;

    /**
     * 创建日期
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    public LogRecord(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

    public enum LogRecordType{

        //多个枚举值,注意名字并不是构造方法名
        TRACE(1,"TRACE"),
        DEBUG(2,"DEBUG"),
        INFO(3,"INFO"),
        WARN(4,"WARN"),
        ERROR(5,"ERROR"),
        FATAL(6,"FATAL");

        //枚举值所包含的属性
        private int key;
        private String value;

        //构造方法
        LogRecordType(int key,String value){
            this.setKey(key);
            this.setValue(value);
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
