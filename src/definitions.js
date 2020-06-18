var definitions = [
    {
        id: 1,

        name: "Null Hypothesis",
        
        content: "is a general statement or default position that there is no relationship between two measured phenomena or no association among groups",
        references: ["https://en.wikipedia.org/wiki/Null_hypothesis"],

        author: "UserXYZ",
        timestamp: "",
        upvotes: 137,
        downvotes: 10,

        comments: [
            {
                id: 7,
                author: "randomUser 895",
                commment: "I like this definition",
                timestamp: 0
            },
            {
                id: 8,
                author: "randomUer456",
                comment: "this is a really good definition. made it a lot clearer",
                timestamp: 0
            },
            {
                id: 9,
                author: "xyxyx",
                comment:"this is a stupid definition to a stupid subject"
            }
        ],

        uses: [
            {
                situation: "Example Question format:",
                example: "When X is Y, what is Z?"
            },
            {
                situation: "Example Situation: Gambling",
                example: "For example, a gambler may be interested in whether a game of chance is fair. If it is fair, then the expected earnings per play is 0 for both players. If the game is not fair, then the expected earnings is positive for one player and negative for the other. To test whether the game is fair, the gambler collects earnings data from many repetitions of the game, calculates the average earnings from these data, then tests the null hypothesis that the expected earnings is not different from zero."
            }

        ],

        Walkthrough: [
            {
                id: 11,
                name: "User768's Walkthrough",
                description: "A walkthrough using xyz",

                tags: [
                    "quick", "easy-to-follow"
                ],

                type: "example"

                
            },
            {
                id:12,
                name: "User90968's Walkthrough",
                description: "A walkthrough using abc",

                tags: [
                    "thorough", "Expert Level"
                ],

                type: "concept"

            }
        ],

        QA: [
            {
                Q: "When is the best time to use Null Hypothesis?",
                A: "Analysts look to reject the null hypothesis because it is a strong conclusion. The alternative conclusion, that the results are “explainable by chance alone,” is a weak conclusion because it allows that factors other than chance may be at work."
            }
        ]


    }
]

export default definitions