/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.constant;

/**
 * @author fangzhongwei
 * 
 */
public enum CardTypeEnum {

    ID_CARD(1, "身份证"),

    PASSPORT(2, "护照"),

    SOLDIER(3, "军官证"),

    HONGKONG_MACAO_CARD(4, "港澳通行证"),

    OTHER_CARD(5, "其他"),

    TAIWAI(6, "台胞证");

    private int id;
    private String name;

    private CardTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(Integer id) {
        String name = null;
        for (CardTypeEnum cardTypeEnum : CardTypeEnum.values()) {
            if (cardTypeEnum.getId() == id.intValue()) {
                name = cardTypeEnum.getName();
                break;
            }
        }

        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
