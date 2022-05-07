package ua.advanced.lesson06.iterator;

import java.util.Iterator;

public class MyTable implements Iterable<String> {

    private String[] columns;
    private int[] rows;

    public MyTable(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Iterator<String> iterator() {
        return new MyTableIterator();
    }

    private class MyTableIterator implements Iterator<String> {

        private int column;
        private int row;

        public MyTableIterator() {
            column = 0;
            row = 0;
        }

        @Override
        public boolean hasNext() {
            return column < columns.length;
        }

        @Override
        public String next() {
            StringBuilder output = new StringBuilder(columns[column]);
            output.append(rows[row++]);
            if (row == rows.length) {
                column++;
                row = 0;
            }
            return output.toString();
        }
    }
}
