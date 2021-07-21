package com.toys.acb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DBConstant;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.Type;
import com.toys.acb.mapper.BillDetailMapper;
import com.toys.acb.mapper.BillMapper;
import com.toys.acb.mapper.SysUserMapper;
import com.toys.acb.mapper.TypeMapper;
import com.toys.acb.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.toys.acb.mapper.BillDynamicSqlSupport.bill;
import static com.toys.acb.mapper.TypeDynamicSqlSupport.type;
import static com.toys.acb.mapper.SysUserDynamicSqlSupport.sysUser;
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
        int rows = -1;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            rows = billMapper.insertSelective(record);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
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
        int rows = -1;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            rows = billMapper.update(render);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteBill(Long bid, Long userId) {
        DeleteStatementProvider render = deleteFrom(bill)
                .where(bill.id, isEqualTo(bid),
                        and(bill.userId, isEqualTo(userId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            rows = billMapper.delete(render);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(render.getDeleteStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return rows;
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
        int rows = -1;
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
                rows = typeMapper.insertSelective(record);
            } else {
                UpdateStatementProvider updateTypeStatus = update(type)
                        .set(type.status).equalTo(DBConstant.TYPE_STATUS_VISIBLE.getCode())
                        .where(type.id, isEqualTo(checkTypeExist::getId))
                        .build()
                        .render(RenderingStrategies.MYBATIS3);

                rows = typeMapper.update(updateTypeStatus);
            }

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
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

        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            rows = typeMapper.update(render);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteType(Long tid, Long userId) {
        UpdateStatementProvider render = update(type)
                .set(type.status).equalTo(DBConstant.TYPE_STATUS_INVISIBLE.getCode())
                .where(type.id, isEqualTo(tid),
                        and(type.userId, isEqualTo(userId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        int rows = -1;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            rows = typeMapper.update(render);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(render.getUpdateStatement());
            System.out.println(render.getParameters());
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateNickname(String nickname, Long userId) {
        UpdateStatementProvider render = update(sysUser)
                .set(sysUser.nickname).equalTo(nickname)
                .where(sysUser.id, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        int rows = -1;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            rows = sysUserMapper.update(render);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updatePassword(String username, String password, String newPW) {
        return 0;
    }
}
