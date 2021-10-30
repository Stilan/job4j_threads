package ru.job4j;

/**
 *  без volatile
 *  Проблема идиомы Double Checked Lock заключается в модели памяти Java,
 *  точнее в порядке создания объектов. Можно условно представить этот порядок следующими этапами [2, 3]:
 * Пусть мы создаем нового студента: DCLSingleton s = new DCLSingleton(), тогда
 * 1) local_ptr = DCLSingleton()) // выделение памяти под сам объект;
 * 2) s = local_ptr // инициализация указателя;
 * 3) DCLSingleton(s); // конструирование объекта (инициализация полей);
 * Таким образом, между вторым и третьим этапом возможна ситуация,
 * при которой другой поток может получить и начать использовать
 * (на основании условия, что указатель не нулевой) не полностью сконструированный объект.
 */
public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

    public static void main(String[] args) {
        DCLSingleton singleton = new DCLSingleton();
        System.out.println(instOf());
    }
}
