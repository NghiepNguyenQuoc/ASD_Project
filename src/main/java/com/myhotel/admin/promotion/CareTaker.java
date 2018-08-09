package com.myhotel.admin.promotion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public class CareTaker {
	private Stack<PromotionMemento> mementoStack;
	private static int MAX_SIZE = 32;

	public CareTaker() {
		mementoStack = new Stack<>();
	}

	public PromotionMemento load(String fileName) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			return (PromotionMemento) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(String fileName, PromotionMemento m) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			oos.writeObject(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addAction(PromotionMemento memento) {
		if (mementoStack.size() == MAX_SIZE) {
			mementoStack.remove(0);
		}
		mementoStack.push(memento);
	}

	public PromotionMemento undo() {
		if (!mementoStack.isEmpty()) {
			return mementoStack.pop();
		}
		return null;
	}
}
