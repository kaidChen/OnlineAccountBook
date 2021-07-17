package com.toys.ACB.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toys.ACB.component.SqlSessionBuilder;
import com.toys.ACB.constant.DBConstant;
import com.toys.ACB.dto.BillDetail;
import com.toys.ACB.entity.Bill;
import com.toys.ACB.entity.Type;
import com.toys.ACB.mapper.BillDetailMapper;
import com.toys.ACB.mapper.BillMapper;
import com.toys.ACB.mapper.TypeMapper;
import com.toys.ACB.service.UserService;
import org.apache.ibatis.session.SqlSession;
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
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Override
    public PageInfo<BillDetail> getAllBillList(Integer page, Integer size, Long userId) {
        SelectStatementProvider selectStatementProvider = select(bill.id, bill.cost, bill.note, bill.time, bill.cycle, bill.source, type.name, type.kind)
                .from(bill)
                .leftJoin(type)
                .on(bill.typeId, equalTo(type.id))
                .where(bill.userId, isEqualTo(userId))
                .orderBy(bill.time.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillDetailMapper billDetailMapper = sqlSession.getMapper(BillDetailMapper.class);
            PageHelper.startPage(page, size);
            List<BillDetail> billDetailList = billDetailMapper.selectMany(selectStatementProvider);
            return new PageInfo<>(billDetailList);
        } catch (Exception e) {
            System.out.println(selectStatementProvider.getSelectStatement());
            System.out.println(selectStatementProvider.getParameters());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addBill(Bill record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateBill(Bill record) {
        UpdateStatementProvider render = update(bill)
                .set(bill.typeId).equalToWhenPresent(record::getTypeId)
                .set(bill.cost).equalToWhenPresent(record::getCost)
                .set(bill.time).equalToWhenPresent(record::getTime)
                .set(bill.note).equalToWhenPresent(record::getNote)
                .where(bill.id, isEqualTo(record.getId()),
                        // 验证数据库中的user_id等于用户填写的user_id
                        and(bill.userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
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

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.delete(render);
        } catch (Exception e) {
            System.out.println(render.getDeleteStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Type> getAllTypes(Long userId) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            return typeMapper.selectMany(
                    select(type.id, type.name, type.kind)
                            .from(type)
                            .where(type.userId, isEqualTo(userId),
                                    and(type.status, isEqualTo(DBConstant.TYPE_STATUS_VISIBLE.getCode())))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addType(Type record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);

            SelectStatementProvider selectType = select(type.id)
                    .from(type)
                    .where(type.name, isEqualTo(record::getName),
                            and(type.userId, isEqualTo(record::getUserId)))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);

            Type checkTypeExist = typeMapper.selectOne(selectType).orElse(null);
            if (checkTypeExist == null) {
                return typeMapper.insertSelective(record);
            }

            UpdateStatementProvider updateTypeStatus = update(type)
                    .set(type.status).equalTo(DBConstant.TYPE_STATUS_VISIBLE.getCode())
                    .where(type.id, isEqualTo(checkTypeExist::getId))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);

            return typeMapper.update(updateTypeStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateType(Type record) {
        UpdateStatementProvider render = update(type)
                .set(type.kind).equalToWhenPresent(record::getKind)
                .set(type.name).equalToWhenPresent(record::getName)
                .where(type.id, isEqualTo(record.getId()),
                        // 验证数据库中的user_id是否等于用户填写的id
                        and(type.userId, isEqualTo(record.getUserId())))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            return typeMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteType(Long tid, Long userId) {
        UpdateStatementProvider render = update(type)
                .set(type.status).equalTo(DBConstant.TYPE_STATUS_INVISIBLE.getCode())
                .where(type.id, isEqualTo(tid),
                        and(type.userId, isEqualTo(userId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            typeMapper.update(render);
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateNickname(String nickname, Long userId) {
        return 0;
    }

    @Override
    public int updatePassword(String username, String password) throws Exception {
        return 0;
    }
}
