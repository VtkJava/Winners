package util;

public interface Stack <T> {

	void push(T v);

	T    pop();

	boolean isEmpty();

	void reset();
}
