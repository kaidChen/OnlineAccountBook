package com.toys.acb.service.impl;

import com.toys.acb.dao.BillDao;
import com.toys.acb.dao.BillPo;
import com.toys.acb.dao.BillTypeDao;
import com.toys.acb.dto.BillDto;
import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BillDao billDao;
    @Autowired
    private BillTypeDao billTypeDao;

    @Override
    public Integer createBill(BillDto billDto) {
        try {
            return billDao.createBill(new BillPo(billDto));
        } catch (Exception e) {
            LOGGER.error("create bill error:{}", e.getMessage());
        }
        return -1;
    }

    @Override
    public Integer invalidateBill(BillDto billDto) {
        return null;
    }

    @Override
    public Integer deleteBill(BillDto billDto) {
        return null;
    }

    @Override
    public Integer updateBill(BillDto billDto) {
        return null;
    }

    @Override
    public BillDto getBill(BillDto billDto) {
        return null;
    }

    @Override
    public List<BillDto> getBillList(BillDto billDto) {
        return null;
    }

    @Override
    public Integer createBillType(BillTypeDto billTypeDto) {
        return null;
    }

    @Override
    public Integer invalidateBillType(BillTypeDto billTypeDto) {
        return null;
    }

    @Override
    public Integer updateBillType(BillTypeDto billTypeDto) {
        return null;
    }

    @Override
    public BillTypeDto getBillType(BillTypeDto billTypeDto) {
        return null;
    }

    @Override
    public List<BillTypeDto> getBillTypeList(BillTypeDto billTypeDto) {
        return null;
    }
}
