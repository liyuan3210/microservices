package com.liyuan3210.dubboDemo.client.vo;



import java.io.Serializable;
import java.util.Date;


public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
    
    private Long age;
    
    private String say;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}
    
}
