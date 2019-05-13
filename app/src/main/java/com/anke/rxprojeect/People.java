package com.anke.rxprojeect;
public class People {
    private Integer id;
    private String sex;
    private String name;
    public People() {
        super();
    }
    public People(Integer id,String sex,String name) {
        super();
        this.id = id;
        this.sex = sex;
        this.name = name;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
