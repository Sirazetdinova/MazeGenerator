package backend.academy.maze;

import backend.academy.cell.CellType;

public class MazeService {
    public void setStartCell(Maze maze, int[] cords) {
        maze.grid()[cords[0]][cords[1]].type(CellType.EMPTY);
        maze.start(maze.grid()[cords[0]][cords[1]]);
    }

    public void setEndCell(Maze maze, int[] cords) {
        maze.grid()[cords[0]][cords[1]].type(CellType.EMPTY);
        maze.end(maze.grid()[cords[0]][cords[1]]);
    }
}
