package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Arrays;
import java.util.List;

import com.alextim.bookshelf.datauploader.uploader.IUploader;

public class DummyUploader implements IUploader {
    private final List<String> data = Arrays.asList(new String[] {
            "�����,��������,���,���������� �����,��� ������� (������),��� ������� (����������)",
            "������ �� ��������� ��������,���������� ������� ��� ����� ����������,1,2,1979,",
            "������ �� ��������� ��������,���������� ������� ��� ����� ����������,2,2,1979,",
            "�.�. ������,�������� ��������� � 5-�� �����,1,5,1981,",
            "�.�. ������,�������� ��������� � 5-�� �����,2,5,1981,",
            "�.�. ������,�������� ��������� � 5-�� �����,3,5,1981,",
            "����� ������ ������,�������� ��������� � 3-�� �����,1,3,1975,",
            "����� ������ ������,�������� ��������� � 3-�� �����,2,3,1975,",
            "����� ������ ������,�������� ��������� � 3-�� �����,3,3,1975,"
    });

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
