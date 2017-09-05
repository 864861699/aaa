package com.sixi.service.common;

import com.sixi.domain.model.oamodel.Class1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClassList1 {

    List<Class1> getGenerateList();
}
