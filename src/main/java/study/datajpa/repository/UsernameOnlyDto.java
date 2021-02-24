package study.datajpa.repository;

public class UsernameOnlyDto {

    private final String username;
    private final int age;

    //parameter명을 이용하여 projection한다. parameter 명이 중요하다
    public UsernameOnlyDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
