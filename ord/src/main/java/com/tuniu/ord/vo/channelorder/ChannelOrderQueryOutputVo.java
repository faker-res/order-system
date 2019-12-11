/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.channelorder;

import java.util.ArrayList;
import java.util.List;

import com.tuniu.ord.vo.Tourist;

/**
 * 
 *       {
        "id":121212,
        "channelOrderId": 2121212221,
        "channelName":"途牛A"，
        "status": 0,
        "addTime":"2016-11-21 10:00:00",
        "operatorType": 1,
        "beforeChangeTourist": [
          {
            "touristId": 2212122,
            "touristName": "张三"
          },
          {
            "touristId": 2212123,
            "touristName": "张三"
          }
        ],
        "afterChangeTourist": [
          {
            "touristId": 2212122,
            "touristName": "张三"
          }
        ]
      }
 */
/**
 * @author zhairongping
 *
 */
public class ChannelOrderQueryOutputVo {
    private Integer id;
    private Integer channelOrderId;
    private String channelName;
    private Integer status;
    private String addTime;
    private Integer operatorType;
    private List<Tourist> beforeChangeTourist = new ArrayList<Tourist>();
    private List<Tourist> afterChangeTourist = new ArrayList<Tourist>();
    /**
     * 确认位置数
     */
    private Integer confirmNum;
    /**
     * 核损位置数
     */
    private Integer lossNum;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the channelOrderId
     */
    public Integer getChannelOrderId() {
        return channelOrderId;
    }

    /**
     * @param channelOrderId
     *            the channelOrderId to set
     */
    public void setChannelOrderId(Integer channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName
     *            the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the addTime
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     *            the addTime to set
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the operatorType
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * @param operatorType
     *            the operatorType to set
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * @return the beforeChangeTourist
     */
    public List<Tourist> getBeforeChangeTourist() {
        return beforeChangeTourist;
    }

    /**
     * @param beforeChangeTourist
     *            the beforeChangeTourist to set
     */
    public void setBeforeChangeTourist(List<Tourist> beforeChangeTourist) {
        this.beforeChangeTourist = beforeChangeTourist;
    }

    /**
     * @return the afterChangeTourist
     */
    public List<Tourist> getAfterChangeTourist() {
        return afterChangeTourist;
    }

    /**
     * @param afterChangeTourist
     *            the afterChangeTourist to set
     */
    public void setAfterChangeTourist(List<Tourist> afterChangeTourist) {
        this.afterChangeTourist = afterChangeTourist;
    }

    /**
     * @return the confirmNum
     */
    public Integer getConfirmNum() {
        return confirmNum;
    }

    /**
     * @param confirmNum
     *            the confirmNum to set
     */
    public void setConfirmNum(Integer confirmNum) {
        this.confirmNum = confirmNum;
    }

    /**
     * @return the lossNum
     */
    public Integer getLossNum() {
        return lossNum;
    }

    /**
     * @param lossNum
     *            the lossNum to set
     */
    public void setLossNum(Integer lossNum) {
        this.lossNum = lossNum;
    }
}
