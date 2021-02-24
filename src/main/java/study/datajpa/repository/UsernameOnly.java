package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    //@Value("#{target.username + ' ' + target.age + ' ' + target.team.name}") // open Projection은 SQL 최적화가 안 된다
    String getUsername();
}
