package com.shijie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shijie.pojo.Comment;
import com.shijie.pojo.Editor;
import com.shijie.pojo.User;
import com.shijie.service.CommentService;
import com.shijie.service.EditorService;
import com.shijie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Api(tags = "博客相关接口")
@Controller
public class BlogController {
    @Autowired
    EditorService editorService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @ApiOperation("跳转首页")
    @GetMapping({"/","/index","/index.html"})
    public String toIndex(){
        return "index";
    }

    @ApiOperation("跳转到login")
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public String login(String user_name, String user_password, Model model){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user_name, user_password);
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            model.addAttribute("msg","用户名或密码错误");
            System.out.println("用户名或密码错误");
            return "login";
        }
        return "redirect:/index";
    }

    @ApiOperation("退出登录接口")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

    @PostMapping("/Register")
    public String register(String user_name,String user_password,String user_email,Model model){
        User user=new User();
        user.setUser_name(user_name);
        user.setUser_password(user_password);
        user.setUser_email(user_email);
        user.setUser_grade(2);
        int i=userService.addUser(user);
        if (i>0){
            model.addAttribute("msg","注册成功！请登录");
        }else{
            model.addAttribute("msg","注册失败！请检查后重新注册");
        }
        return "login";
    }

    @ApiOperation("跳转用户接口")
    @GetMapping("/user")
    public String toUser(){
        return "UserInfo";
    }

    @ApiOperation("跳转联系作者")
    @GetMapping("/contact")
    public String toContact(){
        return "contact";
    }

    @ApiOperation("跳转博客主页面")
    @GetMapping("/travel/{pageIndex}")
    public String toTravel(@PathVariable("pageIndex") int pageIndex, Model model){
        int pageStart=pageIndex;
        int pageSize = 10;
        Page page=PageHelper.startPage(pageStart, pageSize);
        List<Editor> editors =editorService.queryAllEditor();
        PageInfo<Editor> pageInfo=new PageInfo<>(editors);

        model.addAttribute("editors",editors);
        model.addAttribute("total",pageInfo.getPages());
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        model.addAttribute("isLastPage",pageInfo.isIsLastPage());
        model.addAttribute("prePage",pageInfo.getPrePage());
        model.addAttribute("nextPage",pageInfo.getNextPage());
        model.addAttribute("pageNum",pageInfo.getPageNum());
        return "travel";
    }

    @ApiOperation("跳转到博客详情")
    @GetMapping("/singlepage/{blogID}")
    public String toSinglepage(@PathVariable("blogID") int blogID,Model model){
        Editor editor=editorService.queryEditorByID(blogID);
        List<Comment> commentList=commentService.queryAllCommentByBlogID(blogID);
        model.addAttribute("editor",editor);
        model.addAttribute("comments", commentList);
        model.addAttribute("blogID",blogID);
        return "singlepage";
    }

    @ApiOperation("跳转发布博客")
    @GetMapping("/toPostBlog")
    public String toPostBlog(){
        return "PostBlog";
    }


    @ApiOperation("评论回复接口")
    @PostMapping("/Reply")
    public String Reply(String blogID,String user_name,String user_id,String comment_msg,String comment_id,HttpSession session){
        User loginUser=(User) session.getAttribute("loginUser");
        Comment comment=new Comment();
        comment.setBlog_id(Long.parseLong(blogID));
        comment.setUser_name(loginUser.getUser_name());
        comment.setReply_user_name(user_name);
        comment.setUser_id(loginUser.getUser_id());
        comment.setReply_user_Id(Long.parseLong(user_id));
        comment.setPid(Long.parseLong(comment_id));
        comment.setComment_msg(comment_msg);
        comment.setCreat_time(new Date());

        commentService.addComment(comment);

        return "redirect:/singlepage/"+blogID;
    }

    @ApiOperation("评论接口")
    @PostMapping("/Comment")
    public String Comment(String blogID,String comment_msg,HttpSession session){
        User loginUser=(User) session.getAttribute("loginUser");

        Comment comment=new Comment();
        comment.setUser_id(loginUser.getUser_id());
        comment.setBlog_id(Long.parseLong(blogID));
        comment.setPid(0);
        comment.setComment_msg(comment_msg);
        comment.setCreat_time(new Date());
        comment.setUser_name(loginUser.getUser_name());
        commentService.addComment(comment);

        return "redirect:/singlepage/"+blogID;
    }

    @ApiOperation("查询博客接口")
    @PostMapping("/queryTravel")
    public String QueryTravel(String Name, Model model){
        int pageStart=1;
        int pageSize = 10;
        Page page=PageHelper.startPage(pageStart, pageSize);
        List<Editor> editors =editorService.queryEditorByName(Name);
        PageInfo<Editor> pageInfo=new PageInfo<>(editors);

        model.addAttribute("editors",editors);
        model.addAttribute("total",pageInfo.getPages());
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        model.addAttribute("isLastPage",pageInfo.isIsLastPage());
        model.addAttribute("prePage",pageInfo.getPrePage());
        model.addAttribute("nextPage",pageInfo.getNextPage());
        model.addAttribute("pageNum",pageInfo.getPageNum());
        return "travel";
    }
}
