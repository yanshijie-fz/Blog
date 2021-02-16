package com.shijie.service;


import com.shijie.dao.EditorDao;
import com.shijie.pojo.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    EditorDao editorDao;

    @Override
    public List<Editor> queryAllEditor() {
        return editorDao.queryAllEditor();
    }

    @Override
    public Editor queryEditorByID(int id) {
        return editorDao.queryEditorByID(id);
    }

    @Override
    public List<Editor> queryEditorByName(String Name) {
        return editorDao.queryEditorByName(Name);
    }

    @Override
    public int modifyEditor(String content, String textContent, int id, Date modifyTime) {
        return editorDao.modifyEditor(content,textContent,id,modifyTime);
    }

    @Override
    public int addEditor(Editor editor) {
        return editorDao.addEditor(editor);
    }

    @Override
    public int delEditorByID(int id) {
        return editorDao.delEditorByID(id);
    }
}
