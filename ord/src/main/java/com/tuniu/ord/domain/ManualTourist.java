package com.tuniu.ord.domain;

public class ManualTourist extends BaseManualDomain{

    /**
     * 
     */
    private static final long serialVersionUID = 5597453667266805770L;

    private Integer touristType;

    private String name;

    private String firstname;

    private String lastname;

    private Integer sex;

    private String tel;


    /**
     * @return the touristType
     */
    public Integer getTouristType() {
        return touristType;
    }

    /**
     * @param touristType the touristType to set
     */
    public void setTouristType(Integer touristType) {
        this.touristType = touristType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname == null ? null : firstname.trim();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname == null ? null : lastname.trim();
    }
    
    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}