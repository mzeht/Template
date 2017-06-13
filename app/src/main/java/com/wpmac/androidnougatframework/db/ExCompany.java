package com.wpmac.androidnougatframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wpmac on 2017/3/20.
 *
 */
@Entity
public class ExCompany {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String name;//快递名称
    @Property
    private String no; //快递编号
    @Property
    private String logo;//快递公司LOGO地址
    @Property
    private boolean isDefault;//是否系统默认快递
    @Generated(hash = 804520941)
    public ExCompany(Long id, String name, String no, String logo,
            boolean isDefault) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.logo = logo;
        this.isDefault = isDefault;
    }
    @Generated(hash = 1936923087)
    public ExCompany() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNo() {
        return this.no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getLogo() {
        return this.logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public boolean getIsDefault() {
        return this.isDefault;
    }
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

   
}
