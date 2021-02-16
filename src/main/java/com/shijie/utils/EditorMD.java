package com.shijie.utils;

import com.alibaba.fastjson.JSONObject;
import com.shijie.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

public class EditorMD {

    public JSONObject uploadImg(HttpSession session
            , String Attribute
            , String SystemPath
            , MultipartFile file
            , String urlPath){
        JSONObject object = new JSONObject();
        User user = (User)session.getAttribute(Attribute);
        try {
            String realPath = SystemPath+user.getUser_id();
            File myFlie = new File(realPath);
            if (!myFlie.exists()) {
                myFlie.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid;
            file.transferTo(new File(realPath, fileName));
            object.put("success", 1);
            object.put("message", "上传成功");
            object.put("url", urlPath+user.getUser_id()+"/"+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("success", 0);
            object.put("message", "上传失败");
        }
        return object;
    }
}
