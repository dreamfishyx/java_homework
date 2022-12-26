package entity;

/**
 * @Description: 学生实体类
 * @Author: dreamfish
 * @Date: 2022/12/1 16:51
 * @Version: 1.0
 **/
public class Student {
    private int uid;//id
    private String name;//姓名
    private String stuNumber;//学号
    private int age;//年龄
    private String phoneNumber;//电话
    private String nativePlace;//籍贯

    public Student() {
    }

    public Student(String stuNumber, String name, int age, String phoneNumber, String nativePlace) {
        this.stuNumber = stuNumber;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.nativePlace = nativePlace;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                '}';
    }
}
