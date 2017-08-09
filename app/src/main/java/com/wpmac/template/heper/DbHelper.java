package com.wpmac.template.heper;

import android.text.TextUtils;

import com.wpmac.template.base.BasePreference;
import com.wpmac.template.db.DBManager;
import com.wpmac.template.db.ExCompany;
import com.wpmac.template.db.ExCompanyDao;
import com.wpmac.template.db.Express;
import com.wpmac.template.db.ExpressDao;
import com.wpmac.template.debug.L;

import java.util.List;


/**
 * Created by wpmac on 2017/1/1.
 * GreenDao帮助类
 */

public class DbHelper {


    //-------------------------------------快递单-----------------------------------------------//

    /**
     * @param moduleName 获取当前用户当前模块的订单列表
     *                   查询order的userid 为当前用户的id
     * @return
     */
    public static List<Express> getUserOrderByMouleName(String moduleName) {
        ExpressDao expressDao = DBManager.getInstance().getDaoSession().getExpressDao();
        List<Express> expresses = expressDao.queryBuilder()
                .where(ExpressDao.Properties.MoudleName.eq(moduleName))
                .where(ExpressDao.Properties.EmployeeNo.eq(BasePreference.getInstance().getEmployeeNo()))
                .list();

        return expresses;
    }


    /**
     * 默认设置order的userid 为当前用户的id
     * setIsChecked false
     *
     * @param express
     * @throws Exception
     */
    public static void addExpress(Express express) throws Exception {
        if (TextUtils.isEmpty(express.getMoudleName()))
            throw new Exception("模块名称不能为空");
        if (TextUtils.isEmpty(express.getExpressNumber()))
            throw new Exception("订单为空");
        if (selectByOrderNumberAndModuleName(express.getExpressNumber(), express.getMoudleName()).size() > 0) {
            throw new Exception("订单重复");
        }
        if (TextUtils.isEmpty(BasePreference.getInstance().getEmployeeNo()))
            throw new Exception("登录名为空");
        express.setIsChecked(false);
        express.setEmployeeNo(BasePreference.getInstance().getEmployeeNo());
        ExpressDao orderdao = DBManager.getInstance().getDaoSession().getExpressDao();
        orderdao.insert(express);
    }

    /**
     * 删除单条数据
     *
     * @param id
     * @throws Exception
     */
    public static void deleteById(Long id) throws Exception {
        ExpressDao orderDao = DBManager.getInstance().getDaoSession().getExpressDao();
        orderDao.deleteByKey(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @throws Exception
     */
    public static void deleteByIds(List<Long> ids) throws Exception {
        ExpressDao orderDao = DBManager.getInstance().getDaoSession().getExpressDao();
        for (Long id : ids) {
            orderDao.deleteByKey(id);
        }

    }

    /**
     * 清空数据
     *
     * @param express
     * @throws Exception
     */
    public static void deleteAll(Express express) throws Exception {

        ExpressDao orderDao = DBManager.getInstance().getDaoSession().getExpressDao();
        orderDao.deleteAll();
    }

    /**
     * 根据订单号,模块名称, 登录名称查询
     *
     * @param orderNumber
     * @param moudleName
     * @return
     * @throws Exception
     */
    public static List<Express> selectByOrderNumberAndModuleName(String orderNumber, String moudleName) throws Exception {
        ExpressDao expressDao = DBManager.getInstance().getDaoSession().getExpressDao();
        List<Express> expresses = expressDao.queryBuilder()
                .where(ExpressDao.Properties.ExpressNumber.eq(orderNumber))
                .where(ExpressDao.Properties.MoudleName.eq(moudleName))
                .where(ExpressDao.Properties.EmployeeNo.eq(BasePreference.getInstance().getLoginName()))
                .list();
        return expresses;
    }


    /**
     * 根据模块名称和订单号查询是否重复
     *
     * @param moduleName
     * @param orderNumber
     * @return
     */
    public static boolean isExistExpress(String moduleName, String orderNumber) {
        boolean flag = false;
        ExpressDao expressDao = DBManager.getInstance().getDaoSession().getExpressDao();
        List<Express> expresses = expressDao.queryBuilder()
                .where(ExpressDao.Properties.ExpressNumber.eq(orderNumber))
                .where(ExpressDao.Properties.EmployeeNo.eq(BasePreference.getInstance().getLoginName()))
                .where(ExpressDao.Properties.MoudleName.eq(moduleName))
                .list();
        if (expresses != null && expresses.size() > 0)
            flag = true;
        return flag;
    }


    //-------------------------------------快递公司-----------------------------------------------//

    /**
     * 获取快递公司信息列表
     *
     * @return
     */
    public static List<ExCompany> getExCompanys() {
        ExCompanyDao exCompanyDao = DBManager.getInstance().getDaoSession().getExCompanyDao();
        List<ExCompany> exCompanies = exCompanyDao.queryBuilder().list();
        return exCompanies;
    }

    /**
     * 根据公司名称查询快递公司信息
     *
     * @param name
     * @return
     */
    public static ExCompany selectExCompanyByName(String name) {
        ExCompanyDao exCompanyDao = DBManager.getInstance().getDaoSession().getExCompanyDao();
        List<ExCompany> exCompanies = exCompanyDao.queryBuilder()
                .where(ExCompanyDao.Properties.Name.eq(name))
                .list();
        return (exCompanies != null && exCompanies.size() > 0) ? exCompanies.get(0) : null;

    }

    /**
     * 删除快递公司信息
     */
    public static void deleteExCompany() {
        ExCompanyDao exCompanyDao = DBManager.getInstance().getDaoSession().getExCompanyDao();
        exCompanyDao.deleteAll();
    }

    /**
     * 插入快递公司信息
     *
     * @param exCompany
     */
    public static void addExCompany(ExCompany exCompany) {
        ExCompany exCompany1 = selectExCompanyByName(exCompany.getName());
        if (exCompany1 == null) {
            ExCompanyDao exCompanyDao = DBManager.getInstance().getDaoSession().getExCompanyDao();
            exCompanyDao.insert(exCompany);
        } else {
            L.i("该名称的快递公司已存在");
        }

    }




}
