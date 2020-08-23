package BackEndRewrite.CommandObjects.ContentCOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionContentCO {
    String author;
    String content;
    String id;

    Integer replyCount;
    List<String> replyCOIDList;
}
