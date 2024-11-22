package backend.academy.service;

import backend.academy.display.Display;
import backend.academy.display.InfoDisplay;
import backend.academy.generation.GenerationType;
import backend.academy.solution.SolutionType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OutputService implements Display {
    private final InfoDisplay infoDisplay;
    private Properties messages;

    public OutputService() {
        this.infoDisplay = new InfoDisplay();
        loadMessages();
    }

    private void loadMessages() {
        messages = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("en_messages.txt")) {
            if (input == null) {
                throw new IOException("Messages file not found");
            }
            messages.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load messages", e);
        }
    }

    private String getMessage(String key) {
        return messages.getProperty(key, "Message not found for key: " + key);
    }

    @Override
    public void show(String text) {
        infoDisplay.show(text);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void printGenerationAlgorithmPrompt() {
        show(getMessage("generation_algorithm"));
        for (GenerationType type : GenerationType.values()) {
            printType(String.valueOf(type), type.ordinal());
        }
    }

    public void printSolutionAlgorithmPrompt() {
        show(getMessage("solution_algorithm"));
        for (SolutionType type : SolutionType.values()) {
            printType(String.valueOf(type), type.ordinal());
        }
    }

    public void printSolutionStatus(boolean isSolved) {
        show(getMessage(isSolved ? "solution_found" : "solution_not_found"));
    }

    public void printSelectedStrategies(
        GenerationType generationType, SolutionType solutionType
    ) {
        show("Chosen generation type: " + generationType);
        show("Chosen solution type: " + solutionType);
    }

    private void printType(String type, int number) {
        show(number + " - " + type);
    }


    public void printMazeSizePrompt() {
        show(getMessage("maze_size_prompt"));
    }

    public void printStartCellPrompt() {
        show(getMessage("start_cell"));
    }

    public void printEndCellPrompt() {
        show(getMessage("end_cell"));
    }

    public void printTryAgainPrompt() {
        show(getMessage("try_again"));
    }
}

