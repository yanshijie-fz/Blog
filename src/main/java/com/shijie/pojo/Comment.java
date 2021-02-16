package com.shijie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long comment_id;
    private long user_id;
    private long blog_id;
    private long reply_user_Id;
    private long pid;
    private String comment_msg;
    private Date creat_time;

    private String user_name;
    private String reply_user_name;


    private List<Comment> Replys;
    public String getCreat_time() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creat_time);
    }
}
