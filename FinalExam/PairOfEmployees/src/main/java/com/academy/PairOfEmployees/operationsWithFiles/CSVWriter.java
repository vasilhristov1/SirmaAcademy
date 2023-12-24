package com.academy.PairOfEmployees.operationsWithFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter<T> implements CustomWriter<T> {
    @Override
    public void write(List<T> pairs, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (T pair : pairs) {
                writer.write(pair.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
