package com.shijie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Editor {

    private Integer id;
    private String contentTitle;
    private String contentDesc;
    private String content;
    private String textContent="";
    private Date createTime;
    private Date modifyTime;
    private Integer user_id;
    private String user_name;

    public String getModifyTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifyTime);
    }

    public String getModifyTimeDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(modifyTime);
    }

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }
}
