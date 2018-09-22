package test.com.entity.data;

import java.util.Date;
import java.util.Objects;

public class DatePeriod {

    private Date fromDate;
    private Date toDate;

    public DatePeriod(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean consistsInPeriod(Date date) {
        if ((date.after(fromDate) && date.before(toDate)) || date.equals(fromDate)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatePeriod that = (DatePeriod) o;
        return Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fromDate, toDate);
    }
}
