package com.t4er.olan.service;

import com.t4er.olan.mapper.SampleMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sampleServiceImpl")
@Log4j
public class SampleServiceImpl implements  SampleService{

    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public int totalCount() {

        return this.sampleMapper.totalCount();
    }
}
