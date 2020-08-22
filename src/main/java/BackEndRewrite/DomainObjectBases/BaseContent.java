package BackEndRewrite.DomainObjectBases;

import org.springframework.data.annotation.Id;

public class BaseContent {
    @Id
    String id;
    String content;
}
