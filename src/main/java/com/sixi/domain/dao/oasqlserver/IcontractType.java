package com.sixi.domain.dao.oasqlserver;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface IcontractType {
    List<Map<String,Object>> getContractType();
}
