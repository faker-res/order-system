package com.tuniu.ord.vo.external;

import com.tuniu.ord.domain.BaseDomain;


/**
 * ProductRelatePerson product_relate_person
 */
public class ProductRelatePerson extends BaseDomain {

    private static final long serialVersionUID = 6845491328591150215L;

    /**
     * 产品ID product_relate_person.product_id
     */
    private Integer productId;

    /**
     * 用户ID product_relate_person.user_id
     */
    private Integer userId;

    /**
     * 姓名 product_relate_person.name
     */
    private String name;

    /**
     * 1:产品负责人 product_relate_person.person_type
     */
    private String personType;

    /**
     * @return product_relate_person.product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param Integer
     *            productId (product_relate_person.product_id )
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return product_relate_person.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param Integer
     *            userId (product_relate_person.user_id )
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return product_relate_person.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param String
     *            name (product_relate_person.name )
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return product_relate_person.person_type
     */
    public String getPersonType() {
        return personType;
    }

    /**
     * @param String
     *            personType (product_relate_person.person_type )
     */
    public void setPersonType(String personType) {
        this.personType = personType == null ? null : personType.trim();
    }

}