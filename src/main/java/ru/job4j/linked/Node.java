package ru.job4j.linked;

/**
 * Сделать класс финальным
 * сделать все члены окончательными, установить их явно, в статическом блоке или в конструкторе
 * Сделать всех участников закрытыми
 * Нет методов, изменяющих состояние
 * Будьте предельно осторожны, чтобы ограничить доступ к изменяемым членам (помните, что поле может быть,
 * finalно объект все еще может быть изменяемым, т. Е.
 * private final Date imStillMutable). Вы должны сделать это defensive copiesв этих случаях.
 * @param <T>
 */
public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

}
