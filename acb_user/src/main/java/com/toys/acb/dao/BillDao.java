package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.entity.Bill;
import com.toys.acb.mapper.BillMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.BillDynamicSqlSupport.*;
import static com.toys.acb.mapper.BillDynamicSqlSupport.userId;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class BillDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createBill(Bill bill) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            Integer rows = billMapper.insertSelective(bill);
            sqlSession.commit();
            return rows;
        }
    }

    public Integer updateBill(Bill record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            Integer rows = billMapper.updateByPrimaryKeySelective(record);
            sqlSession.commit();
            return rows;
        }
    }

    public Integer deleteBillById(Long recUserId, Long recId) {
        UpdateStatementProvider stmt = update(bill)
                .set(status).equalTo(DbCode.BillStatusInvalid)
                .where(id, isEqualTo(recId),
                        and(userId, isEqualTo(recUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            Integer rows = billMapper.update(stmt);
            sqlSession.commit();
            return rows;
        }
    }

    public List<Bill> getBillListOrderByDate(Long recordUserId, LocalDate start, LocalDate end) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(userId, isEqualTo(recordUserId),
                        and(createdAt, isGreaterThanOrEqualToWhenPresent(start)),
                        and(createdAt, isLessThanOrEqualToWhenPresent(end)),
                        and(status, isEqualTo(DbCode.BillStatusValid)))
                .orderBy(createdAt.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);

            return billMapper.selectMany(stmt);
        }
    }

    public List<Bill> getBillListOrderByType(Long recordUserId, LocalDate start, LocalDate end) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(userId, isEqualTo(recordUserId),
                        and(createdAt, isGreaterThanOrEqualToWhenPresent(start)),
                        and(createdAt, isLessThanOrEqualToWhenPresent(end)),
                        and(status, isEqualTo(DbCode.BillStatusValid)))
                .orderBy(typeId)
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);

            return billMapper.selectMany(stmt);
        }
    }

    public List<Bill> getBillListByUserIdAndTypeId(Long recUserId, Long recTypeId, LocalDate start, LocalDate end) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(userId, isEqualTo(recUserId),
                        and(typeId, isGreaterThanOrEqualToWhenPresent(recTypeId)),
                        and(createdAt, isGreaterThanOrEqualToWhenPresent(start)),
                        and(createdAt, isLessThanOrEqualToWhenPresent(end)),
                        and(status, isEqualTo(DbCode.BillStatusValid))
                )
                .orderBy(createdAt.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);

            return billMapper.selectMany(stmt);
        }
    }

    public Bill getBillById(Long recUserId, Long recId) {
        SelectStatementProvider stmt = select(bill.allColumns())
                .from(bill)
                .where(id, isEqualTo(recId),
                        and(userId, isEqualTo(recUserId)),
                        and(status, isEqualTo(DbCode.BillStatusValid))
                )
                .orderBy(createdAt.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);

            Optional<Bill> billOptional = billMapper.selectOne(stmt);
            return billOptional.orElse(null);
        }
    }
}
