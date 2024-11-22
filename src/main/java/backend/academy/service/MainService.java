package backend.academy.service;

import backend.academy.generation.GenerationAlgorithm;
import backend.academy.generation.GenerationAlgorithmFactory;
import backend.academy.generation.GenerationType;
import backend.academy.maze.Maze;
import backend.academy.maze.MazePrinter;
import backend.academy.maze.MazeService;
import backend.academy.solution.SolutionAlgorithm;
import backend.academy.solution.SolutionAlgorithmFactory;
import backend.academy.solution.SolutionType;

public class MainService {
    InputService inputService;
    OutputService outputService;
    MazeService mazeService;
    MazePrinter mazePrinter;

    public MainService() {
        inputService = new InputService();
        outputService = new OutputService();
        mazeService = new MazeService();
        mazePrinter = new MazePrinter(outputService);
    }

    public void start() {

        GenerationType generationType = getGenerator();
        GenerationAlgorithm generator = GenerationAlgorithmFactory.getGenerator(generationType);
        SolutionType solutionType = getSolver();
        SolutionAlgorithm solver = SolutionAlgorithmFactory.getSolver(solutionType);

        outputService.printSelectedStrategies(generationType, solutionType);

        Maze maze = getMaze(generator);

        boolean isSolved = solver.solve(maze);
        outputService.printSolutionStatus(isSolved);
        mazePrinter.printMaze(maze);
    }

    private GenerationType getGenerator() {
        outputService.printGenerationAlgorithmPrompt();
        GenerationType generationType = inputService.inputGenerationType();
        while (generationType == null) {
            outputService.printTryAgainPrompt();
            outputService.printGenerationAlgorithmPrompt();
            generationType = inputService.inputGenerationType();
        }
        return generationType;
    }

    private SolutionType getSolver(){
        outputService.printSolutionAlgorithmPrompt();
        SolutionType solutionType = inputService.inputSolutionType();
        while (solutionType == null) {
            outputService.printTryAgainPrompt();
            outputService.printSolutionAlgorithmPrompt();
            solutionType = inputService.inputSolutionType();
        }
        return solutionType;
    }
    private Maze getMaze(GenerationAlgorithm generator){
        outputService.printMazeSizePrompt();
        int[] size = inputService.inputMazeSize();
        while (size == null) {
            outputService.printTryAgainPrompt();
            outputService.printMazeSizePrompt();
            size = inputService.inputMazeSize();
        }
        Maze maze = generator.generate(size[0], size[1]);
        mazePrinter.printMazeForChooseCell(maze);
        outputService.printStartCellPrompt();
        int[] start = inputService.inputCell();
        while (start == null) {
            outputService.printTryAgainPrompt();
            outputService.printStartCellPrompt();
            start = inputService.inputCell();
        }
        mazeService.setStartCell(maze, start);
        outputService.printEndCellPrompt();
        int[] end = inputService.inputCell();
        while (end == null) {
            outputService.printTryAgainPrompt();
            outputService.printEndCellPrompt();
            end = inputService.inputCell();
        }
        mazeService.setEndCell(maze, end);
        return maze;
    }
}

