package com.learning.display;

public interface Subject {
	void addObserver(Observer observer);
	void deleteObserver(Observer observer);
	void notifyObservers();
}
