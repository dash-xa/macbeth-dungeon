public class ShakeSpeareWorld {

    private static int[][] original =
            {{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};

    private static int[][] witchesArr =
            {{5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 5, 5, 5, 8, 8, 5, 5, 5, 5, 5, 5, 5, 5, 5}};

    private static int[][] scene2arr =
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private static int[][] duncanArr =
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private static int[][] scene3arr =
            {{14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 13, 13, 13, 13, 14, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 13, 13, 13, 13, 14, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 14, 14, 13, 13, 14, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 14, 14, 13, 13, 14, 13, 13, 13, 13, 13, 14},
                    {14, 14, 14, 14, 13, 13, 14, 13, 13, 14, 14, 13, 13, 14, 14, 14, 14, 14, 14, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 14, 14, 13, 13, 14, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 14, 13, 13, 14, 14, 13, 13, 14, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 13, 13, 13, 14, 14, 13, 13, 13, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 13, 13, 13, 13, 13, 14, 14, 13, 13, 13, 13, 13, 14, 13, 13, 14},
                    {14, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 14},
                    {14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14},
                    {14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}};

    private static int[][] scene4Arr =
            {{12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 12, 12, 12, 11, 11, 12, 12, 12, 12, 11, 11, 12, 12, 12, 12, 12, 12}
                    , {12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 9, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 11, 12, 12, 12, 12, 11, 11, 11, 12, 12, 12, 12, 11, 11, 12}
                    , {12, 11, 11, 12, 12, 12, 12, 11, 11, 12, 12, 12, 12, 12, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12}
                    , {12, 12, 12, 12, 12, 12, 11, 11, 12, 12, 12, 12, 11, 11, 12, 12, 12, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12}
                    , {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};

    private static int[][] scene5Arr =
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

    private static int[][] scene6Arr =
            {{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 5, 5, 5, 5, 3, 3, 3, 5, 5, 5, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 5, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};

    private static int[][] scene7Arr =
            {{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 5, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 5, 5, 5, 5, 3, 3, 3, 5, 5, 5, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 5, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 3, 3, 5, 5, 5, 5, 3, 3, 5, 5, 5, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}
                    , {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    private static final Dialogue[] witchText = new Dialogue[]{
            new Dialogue("So foul and fair a day I have not seen.", true),
            new Dialogue("Banquo: What are these. So withered and so wild in their attire, that look not like th' inhabitants o' th' Earth,", false),
            new Dialogue("Witches: All hail, Macbeth! Hail to thee, thane of Glamis!\n" +
                    "", false),
            new Dialogue("All hail, Macbeth! Hail to thee, thane of Cawdor!\n All hail, Macbeth, that shalt be king hereafter!"
                    , false),
            new Dialogue("Stay, you imperfect speakers, tell me more.", true),
            new Dialogue("The thane of Cawdor lives.\n" +
                    " A prosperous gentleman, and to be king\n", true),
            new Dialogue(" stands not within the prospect of belief.\n" +
                    "\n" +
                    " Speak, I charge you.\n", true),};

    private static final Dialogue[] scene2Dialogue = {
            new Dialogue("My dearest love, Duncan comes here tonight.", true),
            new Dialogue("Lady Macbeth: Look like th' innocent flower, but be the serpent under't", false),
            new Dialogue("If we should fail?", true),
            new Dialogue("We fail? But screw your courage to the sticking-place, and we'll not fail", false),
            new Dialogue("I am settled, and bend up. Each corporal agent to this terrible feat", true)
    };

    private static final Dialogue[] scene3D = {
            new Dialogue("Banquo: Thou hast it now: king, Cawdor, Glamis, all, as the weird women promised,", false),
            new Dialogue("and I fear, thou played'st most foully for 't", false),
            new Dialogue("Know Banquo, me enemy, every minute of his being thrusts against my near'st of life", true),
            new Dialogue("Banquo, thy soul's flight, if it find heaven, must find it out tonight", true)
    };
    private static final Dialogue[] scene3BDialogue = {
            new Dialogue("It will have blood, they say. Blood will have blood. Stones have been known to move, and trees to speak.", true),
            new Dialogue(" I am in blood. Stepped in so far that, should I wade no more,", true),
            new Dialogue("Returning were as tedious as go o'er", true),
    };

    private static final Dialogue[] scene5Dialogue = {
            new Dialogue("Lennox: 'Tis two or three, my lord, that bring you word. Macduff is fled to England.", false),
            new Dialogue("Fled to England? The castle of Macduff I will surprise,", true),
            new Dialogue("Seize upon Fife, give to th' edge o' th' sword. His wife, his babes, and all unfortunate souls", true),
            new Dialogue("That trace him in his line. This deed I’ll do before this purpose cool.", true),

    };

    private static final Dialogue[] scene6Dialogue = {
            new Dialogue("Young Siward: What is thy name?", false),
            new Dialogue("Thou 'lt be afraid to hear it. Macbeth.", true),
            new Dialogue("The devil himself could not pronounce a title more hateful to mine ear.", false),
            new Dialogue("No, nor more fearful.", true),
            new Dialogue("Thou liest, abhorrèd tyrant. With my sword I’ll prove the lie thou speak’st.", false)
    };
    private static final Dialogue[] scene7Dialogue = {
            new Dialogue("I bear a charmèd life, which must not yield to one of woman born.", true),
            new Dialogue("Macduff: Despair thy charm, Macduff was from his mother’s womb, untimely ripped.", false),
            new Dialogue("These juggling fiends no more believed, that keep the word of promise to our ear,", true),
            new Dialogue("And break it to our hope. I’ll not fight with thee.", true)
    };
    private static TileMap start = new TileMap(original, scene7Dialogue); // Dialogue: Talk to witches: guarantee his “invincibility”. Show the start of witch’s influence
    private static TileMap witches = new TileMap(witchesArr, new Typewriter(witchText)); // Dialogue: Lady Macbeth convinces Macbeth and Duncan boss fight

    private static TileMap scene2 = new TileMap(scene2arr, new Typewriter(scene2Dialogue)); // Dialogue: Lady Macbeth convinces Macbeth and Duncan boss fight
    private static TileMap duncanFight = new TileMap(duncanArr); // Dialogue: Lady Macbeth convinces Macbeth and Duncan boss fight
    private static TileMap scene3 = new TileMap(scene3arr, new Typewriter(scene3D)); // Dialogue: and Then Banquo boss fight
    private static TileMap scene4 = new TileMap(scene4Arr, scene3BDialogue); // Dialogue:  and Then Banquo ghost returns to fight again
    private static TileMap scene5 = new TileMap(scene5Arr, scene5Dialogue); // Dialogue:  and Then fight Macduff’s family
    private static TileMap scene6 = new TileMap(scene6Arr, scene6Dialogue); // Dialogue:  and Then Young siward fight (England ppl)
    private static TileMap scene7 = new TileMap(scene7Arr, scene7Dialogue); // Dialogue:  and then Fight Macduff(User cannot win)
    //This also happens in scene 7 after battle: Macduff leaves the battle with Macbeth’s head and Malcolm crowned king (Macduff wins because he was born with a c-section (unnatural birth). → important bc witches said no one born of a woman could kill Macbeth)

    private static int length;

    private static TileMap[][] stageLayout = new TileMap[][]{
            {scene6, scene5, scene4, null, null},
            {scene7, null, scene3, duncanFight, scene2},
            {null, null, null, null, witches},
            {null, null, null, null, start},
            {null, null, null, null, null}
    };

    public int getWorldLength() {
        length = stageLayout.length;
        return length;
    }

    public TileMap get(int y, int x) {
        return stageLayout[y][x];
    }

    public ShakeSpeareWorld() {


        for (int y = 0; y < stageLayout.length; y++)
            for (int x = 0; x < stageLayout[y].length; x++)
                if (stageLayout[y][x] != null)
                    stageLayout[y][x].setWorldPos(x, y);
    }
}

