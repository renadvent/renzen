package BackEndRewrite.Controllers;

import BackEndRewrite.Services.Interfaces.DiscussionService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateDiscussionController {

    //services
    final DiscussionService discussionService;

    public CreateDiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    //converters

    //assemblers

    //TODO create discussion
    @PostMapping(path="/createDiscussion")
    public ResponseEntity<?> createDiscussion(@RequestBody createDiscussionPayload payload){
        return null;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    class createDiscussionPayload{

    }
}
