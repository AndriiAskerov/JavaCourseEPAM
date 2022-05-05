package ua.advanced.lesson06.decorator.task;

import java.util.*;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new AbstractList<>(){
            private List<String> list = new LinkedList<>();
            private int size;

            {
                for(int i = 0; i < sourceList.size(); i++){
                    if(i % 2 != 0)
                        continue;
                    list.add(sourceList.get(i));
                    size++;
                }
            }

            @Override
            public String get(int index) {
                return list.get(index);
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public Iterator<String> iterator(){
                return list.iterator();
            }
        };
    }
}
