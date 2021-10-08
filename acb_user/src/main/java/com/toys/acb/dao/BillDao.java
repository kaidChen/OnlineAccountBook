package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.entity.Bill;
import com.toys.acb.mapper.BillMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.BillDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class BillDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createBill(BillPo record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.insertSelective(record.parseToDbEntity());
        }
    }

    public Integer updateBill(BillPo record) {
        UpdateStatementProvider stmt = update(bill)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(cost).equalToWhenPresent(record::getCost)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(note).equalToWhenPresent(record::getNote)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.update(stmt);
        }
    }

    public Integer deleteBill(BillPo record) {
        DeleteStatementProvider stmt = deleteFrom(bill)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)),
                        and(status, isEqualTo(DbCode.BillStatusInvalid)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.delete(stmt);
        }
    }

    public BillPo getBill(BillPo record) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            Optional<Bill> billOpt = billMapper.selectOne(stmt);
            if (billOpt.isEmpty()) {
                return null;
            }
            return new BillPo(billOpt.get());
        }
    }

    public List<BillPo> getBillList(BillPo record, LocalDate start, LocalDate end) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(userId, isEqualTo(record::getUserId),
                        and(typeId, isEqualToWhenPresent(record::getTypeId)),
                        and(status, isEqualToWhenPresent(record::getStatus)),
                        and(createdAt, isGreaterThanOrEqualToWhenPresent(start)),
                        and(createdAt, isLessThanOrEqualToWhenPresent(end))
                )
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            List<Bill> bills = billMapper.selectMany(stmt);
            List<BillPo> billList = new ArrayList<>();
            bills.forEach(rec -> billList.add(new BillPo(rec)));
            return billList;
        }
    }
}
