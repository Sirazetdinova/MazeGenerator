package backend.academy.maze;

import backend.academy.cell.Cell;
import backend.academy.service.OutputService;

public class MazePrinter {

    private final OutputService outputService;

    public MazePrinter(OutputService outputService) {
        this.outputService = outputService;
    }

    public void printMaze(Maze maze) {
        Cell[][] grid = maze.grid();
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                switch (cell.type()) {
                    case WALL -> outputService.print("⬛\uFE0F");
                    case EMPTY -> outputService.print("⬜\uFE0F");
                    case VISITED -> outputService.print("◾\uFE0F");
                    case PATH -> outputService.print("\uD83D\uDD33");
                    default -> throw new RuntimeException();
                }
            }
            outputService.print("\n");
        }
        outputService.show("Passage cells : " + maze.passageCellsCount());
        outputService.show("Visited cells : " + maze.visitedCellsCount());
        outputService.show("Optimal path length : " + maze.optimalPathLength());
    }

    public void printMazeForChooseCell(Maze maze) {
        Cell[][] grid = maze.grid();
        outputService.print("  ");
        for (int i= 0;i<grid.length;i++) outputService.print(i<10?i+" ": String.valueOf(i));
        outputService.print("\n");
        for (int i =0; i < grid.length; i++){
            outputService.print(i<10?i+" ": String.valueOf(i));
            for (Cell cell : grid[i]){
                switch (cell.type()) {
                    case WALL -> outputService.print("⬛\uFE0F");
                    case EMPTY -> outputService.print("⬜\uFE0F");
                    case VISITED -> outputService.print("◾\uFE0F");
                    case PATH -> outputService.print("\uD83D\uDD33");
                    default -> throw new RuntimeException();
                }
            }
            outputService.print("\n");
        }
    }
}
