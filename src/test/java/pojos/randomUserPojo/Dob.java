package pojos.randomUserPojo;

public class Dob{
	private String date;
	private Integer age;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public Integer getAge(){
		return age;
	}

	@Override
 	public String toString(){
		return 
			"Dob{" + 
			"date = '" + date + '\'' + 
			",age = '" + age + '\'' + 
			"}";
		}
}