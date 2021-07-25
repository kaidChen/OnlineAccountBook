package com.toys.acb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.SysUser;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.BillDynamicSqlSupport.bill;
import static com.toys.acb.mapper.SysUserDynamicSqlSupport.sysUser;
import static com.toys.acb.mapper.TypeDynamicSqlSupport.type;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Override
    public PageInfo<BillDetail> getCurrentBillList(Integer page, Integer size, Long userId) {
        SelectStatementProvider selectStatementProvider = select(bill.id, bill.cost, bill.note, bill.time, bill.cycle, bill.source, type.name, type.kind)
                .from(bill)
                .leftJoin(type)
                .on(bill.typeId, equalTo(type.id))
                .leftJoin(sysUser)
                .on(bill.userId, equalTo(sysUser.id))
                .where(bill.userId, isEqualTo(userId),
                        and(bill.cycle, isEqualTo(sysUser.cycle)))
                .orderBy(bill.time.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillDetailMapper billDetailMapper = sqlSession.getMapper(BillDetailMapper.class);
            PageHelper.startPage(page, size);
            List<BillDetail> billDetailList = billDetailMapper.selectMany(selectStatementProvider);
            LOGGER.info("getCurrentBillList: page={}, size={}, userId={}", page, size, userId);
            return new PageInfo<>(billDetailList);
        } catch (Exception e) {
            LOGGER.error("error at getCurrentBillList: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public PageInfo<BillDetail> getBillListByCycle(Integer page, Integer size, Long cycle, Long userId) {
        SelectStatementProvider selectStatementProvider = select(bill.id, bill.cost, bill.note, bill.time, bill.cycle, bill.source, type.name, type.kind)
                .from(bill)
                .leftJoin(type)
                .on(bill.typeId, equalTo(type.id))
                .where(bill.userId, isEqualTo(userId),
                        and(bill.cycle, isEqualTo(cycle)))
                .orderBy(bill.time.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillDetailMapper billDetailMapper = sqlSession.getMapper(BillDetailMapper.class);
            PageHelper.startPage(page, size);
            List<BillDetail> billDetailList = billDetailMapper.selectMany(selectStatementProvider);
            LOGGER.info("getBillListByCycle: page={}, size={}, cycle={}, userId={}", page, size, cycle, userId);
            return new PageInfo<>(billDetailList);
        } catch (Exception e) {
            LOGGER.error("error at getBillListByCycle: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public PageInfo<BillDetail> getBillListByTypeId(Integer page, Integer size, Long typeId, Long userId) {
        SelectStatementProvider selectStatementProvider = select(bill.id, bill.cost, bill.note, bill.time, bill.cycle, bill.source, type.name, type.kind)
                .from(bill)
                .leftJoin(type)
                .on(bill.typeId, equalTo(type.id))
                .where(bill.userId, isEqualTo(userId),
                        and(bill.typeId, isEqualTo(typeId)))
                .orderBy(bill.time.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillDetailMapper billDetailMapper = sqlSession.getMapper(BillDetailMapper.class);
            PageHelper.startPage(page, size);
            List<BillDetail> billDetailList = billDetailMapper.selectMany(selectStatementProvider);
            LOGGER.info("getBillListByTypeId: page={}, size={}, typeId={}, userId={}", page, size, typeId, userId);
            return new PageInfo<>(billDetailList);
        } catch (Exception e) {
            LOGGER.error("error at getBillListByTypeId: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public int addBill(Bill record) {
        int rows = -1;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            rows = billMapper.insertSelective(record);
            LOGGER.info("addBill: {}", record);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at addBill: {}", e.getMessage());
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
            LOGGER.info("updateBill: {}", record);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at updateBill: {}", e.getMessage());
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
            LOGGER.info("deletBill, id={}", bid);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at deleteBill: {}", e.getMessage());
        }
        return rows;
    }

    @Override
    public List<Type> getTypeList(Long userId) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            List<Type> typeList = typeMapper.selectMany(
                    select(type.id, type.name, type.kind)
                            .from(type)
                            .where(type.userId, isEqualTo(userId),
                                    and(type.status, isEqualTo(DbCode.TYPE_STATUS_VISIBLE.getCode())))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );
            LOGGER.info("getTypeList, userId={}", userId);
            return typeList;
        } catch (Exception e) {
            LOGGER.error("error at getTypeList: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public int addType(Type record) {
        SelectStatementProvider selectType = select(type.id)
                .from(type)
                .where(type.name, isEqualTo(record::getName),
                        and(type.userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            Type checkTypeExist = typeMapper.selectOne(selectType).orElse(null);
            if (checkTypeExist == null) {
                if (getTypeList(record.getUserId()).size() > 30) {
                    LOGGER.info("unable to insert Type, for amount is at the limit. record: {}", record);
                    return -2;
                }
                rows = typeMapper.insertSelective(record);
                LOGGER.info("addType: user_id={}, type_name={}", record.getUserId(), record);
            } else {
                record.setId(checkTypeExist.getId());
                record.setStatus(DbCode.TYPE_STATUS_VISIBLE.getCode());
                rows = typeMapper.updateByPrimaryKeySelective(record);
                LOGGER.info("addType by recover type_status: user_id={}, type_name={}", record.getUserId(), record.getName());
            }
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at addType: {}", e.getMessage());
        }
        return rows;
    }

    @Override
    public int updateType(Type record) {
        UpdateStatementProvider render = update(type)
                .set(type.kind).equalToWhenPresent(record::getKind)
                .set(type.name).equalToWhenPresent(record::getName)
                .where(type.id, isEqualTo(record::getId),
                        // 验证数据库中的user_id是否等于用户填写的id
                        and(type.userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
            rows = typeMapper.update(render);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at updateType: {}", e.getMessage());
        }
        return rows;
    }

    @Override
    public int deleteType(Long tid, Long userId) {
        UpdateStatementProvider render = update(type)
                .set(type.status).equalTo(DbCode.TYPE_STATUS_INVISIBLE.getCode())
                .where(type.id, isEqualTo(tid),
                        and(type.userId, isEqualTo(userId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);

            if(getBillListByTypeId(1, 1, tid, userId).getTotal() > 0) {
                rows = typeMapper.deleteByPrimaryKey(tid);
                LOGGER.info("deleteType: id={}", tid);
            } else {
                rows = typeMapper.update(render);
                LOGGER.info("deleteType by update status: id={}", tid);
            }
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at deleteType: {}", e.getMessage());
        }
        return rows;
    }

    @Override
    public SysUser getUserByUserId(Long userId) {
        SelectStatementProvider render = select(sysUser.cycle, sysUser.nickname)
                .from(sysUser)
                .where(sysUser.id, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Optional<SysUser> sysUserOptional = sysUserMapper.selectOne(render);
            return sysUserOptional.orElse(null);
        } catch (Exception e) {
            LOGGER.error("error at getUserByUserId: {}", e.getMessage());
        }
        return null;
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
            LOGGER.info("updateNickname: nickname={}, suerId={}", nickname, userId);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("error at updateNickname: {}", e.getMessage());
        }
        return rows;
    }
}
