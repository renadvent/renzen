package BackEndRewrite.DomainObjectBases;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter@Setter
public class BaseEntity {
    @Id
    String id;
}
