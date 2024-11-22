package backend.academy.solution;

public class SolutionAlgorithmFactory {
    public static SolutionAlgorithm getSolver(SolutionType type) {
        return switch (type) {
            case SolutionType.BFS -> new BFSSolutionAlgorithm();
            case SolutionType.ASTAR -> new AStarSolutionAlgorithm();
        };
    }

    private SolutionAlgorithmFactory() {}
}
