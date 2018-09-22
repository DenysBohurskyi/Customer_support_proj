package test.com.entity.data.wrapper;

import test.com.entity.linetype.extend.C;
import test.com.entity.linetype.extend.D;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Data {

    private int linesCount;
    private Map<D, List<C>> queriesAndItsWaitingTimeLines;

    public Data() {}

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public Map<D, List<C>> getQueriesAndItsWaitingTimeLines() {
        return queriesAndItsWaitingTimeLines;
    }

    public void setQueriesAndItsWaitingTimeLines(Map<D, List<C>> queriesAndItsWaitingTimeLines) {
        this.queriesAndItsWaitingTimeLines = queriesAndItsWaitingTimeLines;
    }

    @Override
    public String toString() {
        for (Map.Entry entry : queriesAndItsWaitingTimeLines.entrySet()) {
//            List value = (List) entry.getValue();
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
        return "Data{" +
                "linesCount=" + linesCount +
                ", queriesAndItsWaitingTimeLines=" + queriesAndItsWaitingTimeLines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return linesCount == data.linesCount &&
                Objects.equals(queriesAndItsWaitingTimeLines, data.queriesAndItsWaitingTimeLines);
    }

    @Override
    public int hashCode() {

        return Objects.hash(linesCount, queriesAndItsWaitingTimeLines);
    }
}

