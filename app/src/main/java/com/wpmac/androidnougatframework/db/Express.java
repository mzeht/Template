package com.wpmac.androidnougatframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wpmac on 2017/1/1.
 */
@Entity
public class Express {

    @Id(autoincrement = true)
    private Long id;
    @Property
    private String employeeNo;//登录名
    @Property
    private String phoneNumber; //手机号
    @Property
    private String paymentType; //支付类型(1:预付 2:到付
    @Property
    private boolean isChecked;//是否选择
    @Property
    private String moudleName;//模块名称
    @Property
    private String expressNo;//快递公司编号
    @Property
    private String expressName;//快递公司名称
    @Property
    private String expressLogo;//快递公司logo
    @Property
    private String expressNumber;//快递单号
    @Property
    private String ownPhoneNumber;//取件人手机号
    @Property
    private String fee;//到付费用(预付费包裹传0)
    @Property
    private String shelfNo;//货架编号
    @Property
    private String type;//投递类型(2:大件 3:文件 4:到付 5:急件 6:快递公司专件 7:超时上架)
    @Property
    private String deReason;////作废原因
    @Property
    private String returnReason;//退件原因描述
    @Property
    private String remark;//退件备注
    @Property
    private String pointBranchNo;//网点分支编号
    @Generated(hash = 1119390026)
    public Express(Long id, String employeeNo, String phoneNumber,
            String paymentType, boolean isChecked, String moudleName,
            String expressNo, String expressName, String expressLogo,
            String expressNumber, String ownPhoneNumber, String fee, String shelfNo,
            String type, String deReason, String returnReason, String remark,
            String pointBranchNo) {
        this.id = id;
        this.employeeNo = employeeNo;
        this.phoneNumber = phoneNumber;
        this.paymentType = paymentType;
        this.isChecked = isChecked;
        this.moudleName = moudleName;
        this.expressNo = expressNo;
        this.expressName = expressName;
        this.expressLogo = expressLogo;
        this.expressNumber = expressNumber;
        this.ownPhoneNumber = ownPhoneNumber;
        this.fee = fee;
        this.shelfNo = shelfNo;
        this.type = type;
        this.deReason = deReason;
        this.returnReason = returnReason;
        this.remark = remark;
        this.pointBranchNo = pointBranchNo;
    }
    @Generated(hash = 760607181)
    public Express() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeeNo() {
        return this.employeeNo;
    }
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPaymentType() {
        return this.paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    public boolean getIsChecked() {
        return this.isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public String getMoudleName() {
        return this.moudleName;
    }
    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }
    public String getExpressNo() {
        return this.expressNo;
    }
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
    public String getExpressName() {
        return this.expressName;
    }
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
    public String getExpressLogo() {
        return this.expressLogo;
    }
    public void setExpressLogo(String expressLogo) {
        this.expressLogo = expressLogo;
    }
    public String getExpressNumber() {
        return this.expressNumber;
    }
    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }
    public String getOwnPhoneNumber() {
        return this.ownPhoneNumber;
    }
    public void setOwnPhoneNumber(String ownPhoneNumber) {
        this.ownPhoneNumber = ownPhoneNumber;
    }
    public String getFee() {
        return this.fee;
    }
    public void setFee(String fee) {
        this.fee = fee;
    }
    public String getShelfNo() {
        return this.shelfNo;
    }
    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDeReason() {
        return this.deReason;
    }
    public void setDeReason(String deReason) {
        this.deReason = deReason;
    }
    public String getReturnReason() {
        return this.returnReason;
    }
    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPointBranchNo() {
        return this.pointBranchNo;
    }
    public void setPointBranchNo(String pointBranchNo) {
        this.pointBranchNo = pointBranchNo;
    }



}
