package com.toys.acb.service.impl;

import com.toys.acb.dao.BillDao;
import com.toys.acb.dao.BillPo;
import com.toys.acb.dao.BillTypeDao;
import com.toys.acb.dao.BillTypePo;
import com.toys.acb.dto.BillDto;
import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.dto.SearchCondition;
import com.toys.acb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public Integer deleteBill(BillDto billDto) {
        try {
            return billDao.deleteBill(new BillPo(billDto));
        } catch (Exception e) {
            LOGGER.error("delete bill error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updateBill(BillDto billDto) {
        try {
            return billDao.updateBill(new BillPo(billDto));
        } catch (Exception e) {
            LOGGER.error("update bill error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public BillDto getBill(BillDto billDto) {
        try {
            BillPo bill = billDao.getBill(new BillPo(billDto));
            if (bill != null) {
                BillTypePo billTypePo = new BillTypePo();
                billTypePo.setId(bill.getTypeId());
                billTypePo.setUserId(bill.getUserId());
                billTypePo = billTypeDao.getBillType(billTypePo);
                if (billTypePo != null) {
                    BillDto res = new BillDto();
                    res.parseFromPo(bill, billTypePo);
                    return res;
                }
            }
        } catch (Exception e) {
            LOGGER.error("get bill error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<BillDto> getBillList(BillDto billDto, SearchCondition cond) {
        try {
            List<BillPo> billList = billDao.getBillList(new BillPo(billDto), cond.getStart(), cond.getEnd());
            if (billList != null) {
                List<BillDto> resList = new ArrayList<>();
                billList.forEach(rec -> {
                    BillTypePo billTypePo = new BillTypePo();
                    billTypePo.setId(rec.getTypeId());
                    billTypePo.setUserId(rec.getUserId());
                    billTypePo = billTypeDao.getBillType(billTypePo);

                    BillDto resDto = new BillDto();
                    resDto.parseFromPo(rec, billTypePo);
                    resList.add(resDto);
                });
                return resList;
            }
        } catch (Exception e) {
            LOGGER.error("get bill list error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer createBillType(BillTypeDto billTypeDto) {
        try {
            return billTypeDao.createBillType(new BillTypePo(billTypeDto));
        } catch (Exception e) {
            LOGGER.error("create bill type error:{}", e.getMessage());
        }
        return null;
    }


    @Override
    public Integer updateBillType(BillTypeDto billTypeDto) {
        try {
            return billTypeDao.updateBillType(new BillTypePo(billTypeDto));
        } catch (Exception e) {
            LOGGER.error("update bill type error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public BillTypeDto getBillType(BillTypeDto billTypeDto) {
        try {
            BillTypePo billType = billTypeDao.getBillType(new BillTypePo(billTypeDto));
            if (billType != null) {
                BillTypeDto res = new BillTypeDto();
                res.parseFromPo(billType);
                return res;
            }
        } catch (Exception e) {
            LOGGER.error("get bill type error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<BillTypeDto> getBillTypeList(BillTypeDto billTypeDto) {
        try {
            List<BillTypePo> billTypeList = billTypeDao.getBillTypeList(new BillTypePo(billTypeDto));
            if (billTypeList != null) {
                List<BillTypeDto> resList = new ArrayList<>();
                billTypeList.forEach(rec -> {
                    BillTypeDto res = new BillTypeDto();
                    res.parseFromPo(rec);
                    resList.add(res);
                });
                return resList;
            }
        } catch (Exception e) {
            LOGGER.error("get bill type list error:{}", e.getMessage());
        }
        return null;
    }
}
