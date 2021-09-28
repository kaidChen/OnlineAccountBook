package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.entity.BillType;
import com.toys.acb.mapper.BillTypeMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.BillTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class BillTypeDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createBillType(BillTypePo record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.insertSelective(record.parseToDbEntity());
        }
    }

    public Integer deleteBillType(BillTypePo record) {
        DeleteStatementProvider stmt = deleteFrom(billType)
                .where(id, isEqualTo(record.getId()),
                        and(userId, isEqualTo(record.getUserId())))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.delete(stmt);
        }
    }

    public Integer updateBillType(BillTypePo record) {
        UpdateStatementProvider stmt = update(billType)
                .set(name).equalToWhenPresent(record::getName)
                .set(kind).equalToWhenPresent(record::getKind)
                .set(status).equalToWhenPresent(record::getStatus)
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            return billTypeMapper.update(stmt);
        }
    }

    public BillTypePo getBillType(BillTypePo record) {
        SelectStatementProvider stmt = select(billType.allColumns())
                .from(billType)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            Optional<BillType> billTypeOpt = billTypeMapper.selectOne(stmt);
            if (billTypeOpt.isEmpty()) {
                return null;
            }

            return new BillTypePo(billTypeOpt.get());
        }
    }

    public List<BillTypePo> getBillTypeList(BillTypePo record) {
        SelectStatementProvider stmt = select(billType.allColumns())
                .from(billType)
                .where(userId, isEqualTo(record::getUserId),
                        and(name, isEqualToWhenPresent(record::getName)),
                        and(kind, isEqualToWhenPresent(record::getKind)),
                        and(status, isEqualToWhenPresent(record::getStatus)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillTypeMapper billTypeMapper = sqlSession.getMapper(BillTypeMapper.class);
            List<BillType> billTypes = billTypeMapper.selectMany(stmt);
            List<BillTypePo> billTypeList = new ArrayList<>();
            billTypes.forEach(rec -> billTypeList.add(new BillTypePo(rec)));
            return billTypeList;
        }
    }
}
