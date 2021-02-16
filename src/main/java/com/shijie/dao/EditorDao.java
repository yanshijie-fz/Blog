package com.shijie.dao;

import com.shijie.pojo.Editor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface EditorDao {

    List<Editor> queryAllEditor();

    Editor queryEditorByID(int id);

    List<Editor> queryEditorByName(String Name);

    int modifyEditor(String content, String textContent, int id, Date modifyTime);

    int addEditor(Editor editor);

    int delEditorByID(int id);

}
