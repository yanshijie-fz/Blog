package com.shijie.service;

import com.shijie.pojo.Editor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface EditorService {

    List<Editor> queryAllEditor();

    Editor queryEditorByID(int id);

    List<Editor> queryEditorByName(String Name);

    int modifyEditor(String content, String textContent, int id, Date modifyTime);

    int addEditor(Editor editor);

    int delEditorByID(int id);
}
