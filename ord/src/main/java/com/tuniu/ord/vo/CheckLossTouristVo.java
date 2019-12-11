package com.tuniu.ord.vo;

import java.io.Serializable;

public class CheckLossTouristVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6453014437696447985L;

    /**
     * 核损单主键
     */
    private Integer checkLossId;

    /**
     * 核损游客姓名
     */
    private String touristName;

    /**
     * 核损游客证件类型
     */
    private Integer psptType;

    /**
     * 核损的游客证件号
     */
    private String psptId;

    /**
     * 核损的游客出生日期
     */
    private String birthday;

    /**
     * 核损的游客性别（0-女）
     */
    private Integer sex;

    /**
     * 游客fabid
     */
    private String touristId;

    /**
     * A类批次号
     */
    private Integer roundId;

    /**
     * D类切位单号
     */
    private Integer branchId;

    /**
     * 游客主键id
     */
    private Integer peopleTouristId;

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Integer getPsptType() {
        return psptType;
    }

    public void setPsptType(Integer psptType) {
        this.psptType = psptType;
    }

    public String getPsptId() {
        return psptId;
    }

    public void setPsptId(String psptId) {
        this.psptId = psptId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getPeopleTouristId() {
        return peopleTouristId;
    }

    public void setPeopleTouristId(Integer peopleTouristId) {
        this.peopleTouristId = peopleTouristId;
    }

}
