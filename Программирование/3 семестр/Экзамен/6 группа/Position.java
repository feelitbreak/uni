import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

enum Priority {
    A, B, C, D
}
public class Position {
    private final Integer id;
    private final String skill;
    private final String name;
    private final Priority prior;
    public Position(Integer id, String skill, String name, Priority prior) {
        this.id = id;
        this.skill = skill;
        this.name = name;
        this.prior = prior;
    }
    public Priority getPrior() {
        return prior;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(';').append(name);
        sb.append(';').append(prior);
        return sb.toString();
    }
}
class PositionDate {
    private final Integer id;
    private final Date openDate;
    private Date closeDate;
    private final Integer recId;
    PositionDate(String data) throws ParseException {
        String[] split = data.split(";");
        id = Integer.parseInt(split[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        openDate = sdf.parse(split[1]);
        if (!split[2].equals("0")) {
            closeDate = sdf.parse(split[2]);
        }
        recId = Integer.parseInt(split[3]);
    }
    public Integer getId() {
        return id;
    }
    public Date getOpenDate() {
        return openDate;
    }
    public Date getCloseDate() {
        return closeDate;
    }
    public Integer getRecId() {
        return recId;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sb.append(";").append(sdf.format(openDate));
        return sb.toString();
    }
}
