import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PositionProcessor {
    private Map<Integer, Employee> employees;
    private Map<Integer, Position> positions;
    private Map<Integer, Project> projects;
    private List<OpenPosition> openPositions;
    public PositionProcessor() throws IOException, ParseException {
        inputEmployees();
        inputPositions();
        inputProjects();
        inputOpenPositions();
    }
    private void inputEmployees() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        try (br) {
            employees = new LinkedHashMap<>();
            while (br.ready()) {
                String[] split = br.readLine().split(";");
                Employee emp = new Employee(split[1], split[2], split[3]);
                employees.put(Integer.parseInt(split[0]), emp);
            }
        }
    }
    private void inputPositions() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
        try (br) {
            positions = new LinkedHashMap<>();
            while (br.ready()) {
                String[] split = br.readLine().split(";");
                int projId = Integer.parseInt(split[1]);
                int empId = Integer.parseInt(split[2]);
                int work = Integer.parseInt(split[3]);
                Position pos = new Position(projId, empId, work, split[4]);
                positions.put(Integer.parseInt(split[0]), pos);
            }
        }
    }
    private void inputProjects() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        try (br) {
            projects = new LinkedHashMap<>();
            while (br.ready()) {
                String[] split = br.readLine().split(";");
                Project proj = new Project(split[1]);
                projects.put(Integer.parseInt(split[0]), proj);
            }
        }
    }
    private void inputOpenPositions() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("input4.txt"));
        try (br) {
            openPositions = new ArrayList<>();
            while (br.ready()) {
                OpenPosition op = new OpenPosition(br.readLine());
                openPositions.add(op);
            }
        }
    }
    public void out1() throws IOException {
        Map<Integer, Integer> workSums;
        workSums = new LinkedHashMap<>();
        employees.keySet().forEach(x -> {
            List<Position> empX = positions.values()
                    .stream()
                    .filter(y -> y.getEmpId() == x)
                    .collect(Collectors.toList());
            int workSum = 0;
            for (Position pos : empX) {
                workSum += pos.getWorkload();
            }
            workSums.put(x, workSum);
        });
        StringBuilder sb = new StringBuilder();
        workSums.entrySet()
                .stream()
                .filter(x -> x.getValue() > 100)
                .sorted((x, y) -> {
                    if (!x.getValue().equals(y.getValue())) {
                        return y.getValue() - x.getValue();
                    } else {
                        return employees.get(x.getKey()).getSurname().compareTo(employees.get(y.getKey()).getSurname());
                    }
                })
                .forEach(x -> {
                    sb.append(x.getKey()).append(';');
                    sb.append(employees.get(x.getKey()));
                    sb.append(';').append(x.getValue());
                    sb.append('\n');
                });
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        FileWriter fw = new FileWriter("output1.txt");
        fw.write(sb.toString());
        fw.close();
    }
    public void out2() throws IOException {
        Scanner sc = new Scanner(new File("input8.txt"));
        FileWriter fw = new FileWriter("output2.txt");
        try(sc; fw) {
            int projId = sc.nextInt();
            List<Position> pos = positions.values()
                    .stream()
                    .filter(x -> x.getProjId() == projId)
                    .sorted(Comparator.comparingInt(Position::getEmpId))
                    .collect(Collectors.toList());
            Iterator<Position> it = pos.iterator();
            if (it.hasNext()) {
                Position p = it.next();
                fw.write(String.valueOf(p.getEmpId()));
                fw.write(';');
                fw.write(employees.get(p.getEmpId()).toString());
            }
            while (it.hasNext()) {
                fw.write('\n');
                Position p = it.next();
                fw.write(String.valueOf(p.getEmpId()));
                fw.write(';');
                fw.write(employees.get(p.getEmpId()).toString());
            }
        }
    }
    public void out3() throws IOException, ParseException {
        Scanner sc1 = new Scanner(new File("input6.txt"));
        Scanner sc2 = new Scanner(new File("input7.txt"));
        FileWriter fw = new FileWriter("output3.txt");
        try(sc1; sc2; fw) {
            sc1.useDelimiter("[;\n\r]");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = sdf.parse(sc1.next());
            Date date2 = sdf.parse(sc1.next());
            int projId = sc2.nextInt();
            int sum = 0;
            for (OpenPosition op : openPositions ) {
                if (op.getProjId() == projId && op.isInInterval(date1, date2)) {
                    sum++;
                }
            }
            fw.write(projects.get(projId).getTitle());
            fw.write(';');
            fw.write(String.valueOf(sum));
        }
    }
    public void out4() throws IOException {
        Scanner sc = new Scanner(new File("input5.txt"));
        FileWriter fw = new FileWriter("output4.txt");
        try (sc; fw) {
            List<Skill> skills = new ArrayList<>();
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                String skill = split[0];
                int work = Integer.parseInt(split[1]);
                Skill s = new Skill(skill, work);
                skills.add(s);
            }
            for (Skill s : skills) {
                int sum = 0;
                for (Position pos : positions.values()) {
                    if (employees.get(pos.getEmpId()).getSkill().equals(s.skill) && pos.getBilling().equals("non-billable")) {
                        sum += pos.getWorkload();
                    }
                }
                if(s.workload <= sum) {
                    s.setAble(true);
                }
            }
            boolean yes = true;
            for (Skill s : skills) {
                if (!s.able) {
                    yes = false;
                    break;
                }
            }
            if (yes) {
                fw.write("Yes");
            } else {
                fw.write("No");
                for (Skill s : skills) {
                    if (!s.able) {
                        fw.write('\n');
                        fw.write(s.toString());
                    }
                }
            }
        }
    }
    class Skill {
        private final String skill;
        private final int workload;
        private boolean able = false;
        public Skill(String skill, int workload) {
            this.skill = skill;
            this.workload = workload;
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(skill);
            sb.append(";").append(workload);
            return sb.toString();
        }

        public void setAble(boolean able) {
            this.able = able;
        }
    }
}
