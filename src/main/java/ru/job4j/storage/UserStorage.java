package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> userStore = new HashMap<>();

   public synchronized boolean add(User user) {
        if (!userStore.containsKey(user.getId())) {
            userStore.put(user.getId(), user);
            return true;
        }
        return false;
    }
  public synchronized boolean update(User user) {
      if (userStore.containsKey(user.getId())) {
          userStore.put(user.getId(), user);
          return true;
      }
      return false;
  }
   public synchronized boolean delete(User user) {
       if (userStore.containsKey(user.getId())) {
           userStore.remove(user.getId());
           return true;
       }
       return false;
   }
  public synchronized boolean transfer(int fromId, int toId, int amount) {
       User userFrom = userStore.get(fromId);
       User userTo = userStore.get(toId);
       if (userFrom.getAmount() > amount) {
           userFrom.setAmount(userFrom.getAmount() - amount);
           userTo.setAmount(userTo.getAmount() + amount);
           return true;
       }
       return false;
  }

    public static void main(String[] args) {
        UserStorage stoge = new UserStorage();

        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));
        stoge.update(new User(1, 130));
        stoge.delete(new User(1, 130));
        stoge.transfer(1, 2, 50);

        stoge.add(new User(3, 500));
    }
}
