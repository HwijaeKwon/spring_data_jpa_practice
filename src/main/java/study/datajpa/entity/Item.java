package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }

    //id를 내가 직접 생성해서 넣어줘야 하는 경우, 혹은 id를 client한테 받아오는 경우
    //직접 isNew를 구현해야 한다
    //createdDate의 null 유무를 통해 판단한다 (createdDate는 기본적으로 많이 사용하므로 이를 활용한다)   
    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
