package com.shijie.controller;

import com.alibaba.fastjson.JSONObject;
import com.shijie.pojo.Comment;
import com.shijie.pojo.Editor;
import com.shijie.pojo.User;
import com.shijie.service.CommentService;
import com.shijie.service.EditorService;
import com.shijie.utils.EditorMD;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.*;

@Api(tags="富文本编辑器相关接口")
@Controller
@Validated
public class EditorController {

    @Autowired
    EditorService editorService;

    @Autowired
    CommentService commentService;

    @Value("${System.SystemPath}")
    String SystemPath;
    @Value("${System.UrlPath}")
    String UrlPath;


    @ApiOperation("上传博客接口")
    @PostMapping("/addEditor")
    public String addEditor(
            @NotNull(message="contentTitle is not null") String contentTitle
            , @NotNull(message="contentDesc is not null") String contentDesc
            , @NotNull(message="content is not null") String content
            , @NotNull(message="textContent is not null") String textContent
            , HttpSession session){
        Editor editor=new Editor();
        Date date=new Date();
        User loginUser=(User) session.getAttribute("loginUser");

        editor.setContentTitle(contentTitle);
        editor.setContentDesc(contentDesc);
        editor.setContent(content);
        editor.setTextContent(textContent);
        editor.setCreateTime(date);
        editor.setModifyTime(date);
        editor.setUser_id(loginUser.getUser_id());
        editor.setUser_name(loginUser.getUser_name());
        editorService.addEditor(editor);
        return "redirect:/travel/1";
    }


    @ApiOperation("上传博客图片接口")
    @ResponseBody
    @PostMapping("/upload")
    public JSONObject upLoad(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file
            , HttpSession session) throws JSONException {
        EditorMD editorMD=new EditorMD();
        JSONObject object=editorMD.uploadImg(session
                , "loginUser"
                , SystemPath
                , file
                , UrlPath);
        return object;
    }

}
