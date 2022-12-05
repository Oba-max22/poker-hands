package service;

import model.Card;

import java.util.List;

public interface FileService {

    List<List<List<Card>>> readFile();
}
