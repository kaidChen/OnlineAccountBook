package com.toys.acb.service.impl;

import com.toys.acb.dao.BillDao;
import com.toys.acb.dao.BillTypeDao;
import com.toys.acb.dto.BillDto;
import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.BillType;
import com.toys.acb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BillDao billDao;

    @Autowired
    private BillTypeDao billTypeDao;

    @Override
    public List<BillDto> getBillListOrderByTimeDesc(Long userId, LocalDate start, LocalDate end) {
        try {
            List<BillType> billTypeList = billTypeDao.getAllBillTypesByUserId(userId);
            Map<Long, BillType> billTypeMap = new HashMap<>();
            billTypeList.forEach(rec -> billTypeMap.put(rec.getId(), rec));

            List<Bill> billList = billDao.getBillListOrderByDate(userId, start, end);
            List<BillDto> resultList = new ArrayList<>();
            for (Bill bill : billList) {
                BillDto dto = new BillDto();
                resultList.add(
                        dto.parseFromPo(
                                bill,
                                billTypeMap.getOrDefault(bill.getTypeId(), null)
                        )
                );
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error("getBillListOrderByTimeDesc error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<BillDto> getBillListOrderByType(Long userId, LocalDate start, LocalDate end) {
        try {
            List<Bill> billList = billDao.getBillListOrderByType(userId, start, end);
            List<BillDto> resultList = new ArrayList<>();
            for (Bill bill : billList) {
                BillDto dto = new BillDto();
                resultList.add(dto.parseFromPo(bill, null));
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error("getBillListByTypeId error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public BillDto getBillById(Long userId, Long id) {
        try {
            Bill bill = billDao.getBillById(userId, id);
            BillType billType = billTypeDao.getBillTypeById(userId, id);
            BillDto dto = new BillDto();
            return dto.parseFromPo(bill, billType);
        } catch (Exception e) {
            LOGGER.error("getBillById error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer createBill(BillDto billDto) {
        try {
            return billDao.createBill(billDto.parseToPo());
        } catch (Exception e) {
            LOGGER.error("create bill error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updateBill(BillDto billDto) {
        try {
            return billDao.updateBill(billDto.parseToPo());
        } catch (Exception e) {
            LOGGER.error("update bill error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<BillTypeDto> getBillTypeListByUserId(Long userId) {
        try {
            List<BillType> billTypeList = billTypeDao.getVaidBillTypes(userId);
            List<BillTypeDto> resultList = new ArrayList<>();
            for (BillType billType : billTypeList) {
                BillTypeDto dto = new BillTypeDto();
                resultList.add(dto.parseFromPo(billType));
            }
            return resultList;
        } catch (Exception e) {
            LOGGER.error("getBillTypeListByUserId error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer deleteBillById(Long userId, Long id) {
        try {
            return billDao.deleteBillById(userId, id);
        } catch (Exception e) {
            LOGGER.error("deleteBillById error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer deleteBillTypeById(Long userId, Long id) {
        try {
            return billTypeDao.deleteBillTypeById(userId, id);
        } catch (Exception e) {
            LOGGER.error("deleteBillTypeById error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer createBillType(BillTypeDto billTypeDto) {
        try {
            return billTypeDao.createBillType(billTypeDto.parseToPo());
        } catch (Exception e) {
            LOGGER.error("create bill type error:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updateBillType(BillTypeDto billTypeDto) {
        try {
            return billTypeDao.updateBillType(billTypeDto.parseToPo());
        } catch (Exception e) {
            LOGGER.error("update bill type error:{}", e.getMessage());
        }
        return null;
    }
}
