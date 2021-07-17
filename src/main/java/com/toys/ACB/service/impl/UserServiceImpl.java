package com.toys.ACB.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toys.ACB.Util.DBUtils;
import com.toys.ACB.dto.BillDetail;
import com.toys.ACB.entity.Bill;
import com.toys.ACB.entity.Type;
import com.toys.ACB.mapper.BillDetailMapper;
import com.toys.ACB.mapper.BillMapper;
import com.toys.ACB.mapper.TypeMapper;
import com.toys.ACB.service.UserService;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.toys.ACB.mapper.BillDynamicSqlSupport.bill;
import static com.toys.ACB.mapper.TypeDynamicSqlSupport.type;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private BillDetailMapper billDetailMapper;

    @Override
    public PageInfo<BillDetail> getAllBillList(Integer page, Integer size, Long userId) {
        SelectStatementProvider selectStatementProvider = select(bill.id, bill.cost, bill.note, bill.time, type.name, type.kind)
                .from(bill)
                .leftJoin(type)
                .on(bill.typeId, equalTo(type.id))
                .where(bill.userId, isEqualTo(userId))
                .orderBy(bill.time.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try {
            DBUtils.startSqlSession();
            BillDetailMapper billDetailMapper = DBUtils.getMapper(BillDetailMapper.class);
            PageHelper.startPage(page, size);
            List<BillDetail> billDetailList = billDetailMapper.selectMany(selectStatementProvider);
            return new PageInfo<>(billDetailList);
        } catch (Exception e) {
            System.out.println(selectStatementProvider.getSelectStatement());
            System.out.println(selectStatementProvider.getParameters());
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return null;
    }

    @Override
    public int addBill(Bill record) {
        try {
            DBUtils.startSqlSession();
            BillMapper billMapper = DBUtils.getMapper(BillMapper.class);
            return billMapper.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return -1;
    }

    @Override
    public int updateBill(Bill record) {

        UpdateStatementProvider render = update(bill)
                .set(bill.typeId).equalTo(record::getTypeId)
                .set(bill.cost).equalTo(record::getCost)
                .set(bill.time).equalTo(record::getTime)
                .set(bill.note).equalTo(record::getNote)
                .where(bill.id, isEqualTo(record.getId()),
                        // 验证数据库中的user_id等于用户填写的user_id
                        and(bill.userId, isEqualTo(record.getUserId())))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try {
            DBUtils.startSqlSession();
            BillMapper billMapper = DBUtils.getMapper(BillMapper.class);
            return billMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return -1;
    }

    @Override
    public int deleteBill(Long bid, Long userId) {
        DeleteStatementProvider render = deleteFrom(bill)
                .where(bill.id, isEqualTo(bid),
                        and(bill.userId, isEqualTo(userId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try {
            DBUtils.startSqlSession();
            BillMapper billMapper = DBUtils.getMapper(BillMapper.class);
            return billMapper.delete(render);
        } catch (Exception e) {
            System.out.println(render.getDeleteStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return -1;
    }

    @Override
    public List<Type> getAllTypes(Long userId) {
        try {
            DBUtils.startSqlSession();
            TypeMapper typeMapper = DBUtils.getMapper(TypeMapper.class);
            return typeMapper.selectMany(
                    select(type.id, type.name, type.kind)
                            .from(type)
                            .where(type.userId, isEqualTo(userId),
                                    and(type.status, isEqualTo(1)))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );
        } finally {
            DBUtils.close();
        }
    }

    @Override
    public int addType(Type record) {
        try {
            DBUtils.startSqlSession();
            TypeMapper typeMapper = DBUtils.getMapper(TypeMapper.class);
            return typeMapper.insertSelective(record);
        } finally {
            DBUtils.close();
        }
    }

    @Override
    public int updateType(Type record) {
        UpdateStatementProvider render = update(type)
                .set(type.kind).equalTo(record::getKind)
                .set(type.name).equalTo(record::getName)
                .where(type.id, isEqualTo(record.getId()),
                        // 验证数据库中的user_id是否等于用户填写的id
                        and(type.userId, isEqualTo(record.getUserId())))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try {
            DBUtils.startSqlSession();
            TypeMapper typeMapper = DBUtils.getMapper(TypeMapper.class);
            return typeMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return -1;
    }

    @Override
    public int deleteType(Long tid, Long userId) {
        UpdateStatementProvider render = update(type)
                .set(type.status).equalTo(0)
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try {
            DBUtils.startSqlSession();
            TypeMapper typeMapper = DBUtils.getMapper(TypeMapper.class);
            typeMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        } finally {
            DBUtils.close();
        }
        return -1;
    }

    @Override
    public int updateNickname(String nickname, Long userId) {
        return 0;
    }
}
