package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.entity.BillType;
import com.toys.acb.mapper.BillTypeMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.BillTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class BillTypeDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createBillType(BillType record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.insertSelective(record);
        }
    }

    public Integer updateBillType(BillType record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.updateByPrimaryKeySelective(record);
        }
    }

    public Integer deleteBillTypeById(Long recUserId, Long recId) {
        UpdateStatementProvider stmt = update(billType)
                .set(status).equalTo(DbCode.BillTypeStatusInvalid)
                .where(id, isEqualTo(recId),
                        and(userId, isEqualTo(recUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.update(stmt);
        }
    }

    public List<BillType> getAllBillTypesByUserId(Long recUserId) {
        SelectStatementProvider stmt = select(billType.allColumns())
                .from(billType)
                .where(userId, isEqualTo(recUserId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.selectMany(stmt);
        }
    }

    public List<BillType> getVaidBillTypes(Long recUserId) {
        SelectStatementProvider stmt = select(billType.allColumns())
                .from(billType)
                .where(userId, isEqualTo(recUserId),
                        and(status, isEqualTo(DbCode.BillTypeStatusValid)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.selectMany(stmt);
        }
    }

    public BillType getBillTypeById(Long recUserId, Long recId) {
        SelectStatementProvider stmt = select(billType.allColumns())
                .from(billType)
                .where(id, isEqualTo(recId),
                        and(userId, isEqualTo(recUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            Optional<BillType> btOpt = billTypeMapper.selectOne(stmt);
            return btOpt.orElse(null);
        }
    }
}
