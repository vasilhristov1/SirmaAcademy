package com.academy.PairOfEmployees.operationsWithFiles;

import java.util.List;

// interface for the writer class
public interface CustomWriter<T> {
    void write(List<T> items, String filename);
}
