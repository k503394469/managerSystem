package com.liu.controller;

import com.liu.dao.ManagerDao;
import com.liu.domain.Manager;
import org.springframework.stereotype.Repository;

@Repository("manager")
public class ManagerImpl implements ManagerDao {
    public Manager loginCheck(Manager manager) {
        return manager;
    }
}
