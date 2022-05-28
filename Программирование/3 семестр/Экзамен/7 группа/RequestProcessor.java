import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;

public class RequestProcessor {
    private Map<Integer, Employee> employees;
    private List<Request> requests;
    public RequestProcessor() throws IOException, ParseException {
        inputEmployees();
        inputRequests();
    }
    private void inputEmployees() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        try (br) {
            employees = new LinkedHashMap<>();
            while (br.ready()) {
                String[] split = br.readLine().split(";");
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                int vac = Integer.parseInt(split[5]);
                int comp = Integer.parseInt(split[6]);
                Employee emp = new Employee(split[1], split[2], sdf.parse(split[3]), sdf.parse(split[4]), vac, comp);
                employees.put(Integer.parseInt(split[0]), emp);
            }
        }
    }
    private void inputRequests() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        try (br) {
            requests = new ArrayList<>();
            while (br.ready()) {
                Request req = new Request(br.readLine());
                requests.add(req);
            }
        }
    }
    public void out1() throws IOException {
        List<Request> res = requests.stream()
                .filter(x -> x.getDaysId() == 1 && employees.get(x.getEmpId()).getVacation() < x.getDays())
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter("output1.txt");
        try(fw) {
            Iterator<Request> it = res.iterator();
            if (it.hasNext()) {
                fw.write(it.next().toString());
            }
            while (it.hasNext()) {
                fw.write(System.lineSeparator());
                fw.write(it.next().toString());
            }
        }
    }
    public void out2() throws IOException {
        Scanner sc = new Scanner(new File("input3.txt"));
        FileWriter fw = new FileWriter("output2.txt");
        try(sc; fw) {
            int days = sc.nextInt();
            sc.close();
            List<Employee> res = employees.values()
                    .stream()
                    .filter(x -> x.getVacation() >= days)
                    .sorted(Comparator.comparing(Employee::getSurname))
                    .collect(Collectors.toList());
            Iterator<Employee> it = res.iterator();
            if (it.hasNext()) {
                Employee emp = it.next();
                fw.write(emp.toString());
                fw.write(';');
                fw.write(String.valueOf(emp.getVacation()));
            }
            while (it.hasNext()) {
                fw.write(System.lineSeparator());
                Employee emp = it.next();
                fw.write(emp.toString());
                fw.write(';');
                fw.write(String.valueOf(emp.getVacation()));
            }
        }
    }
    public void out3() throws IOException {
        List<Employee> res = employees.values()
                .stream()
                .sorted((x, y) -> {
                    if (x.getDaysDiff() != y.getDaysDiff()) {
                        return (int) (y.getDaysDiff() - x.getDaysDiff());
                    } else {
                        return x.getSurname().compareTo(y.getSurname());
                    }
                })
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter("output3.txt");
        try (fw) {
            Iterator<Employee> it = res.iterator();
            if (it.hasNext()) {
                Employee emp = it.next();
                fw.write(emp.toString());
                fw.write(';');
                fw.write(String.valueOf(emp.getDaysDiff()));
            }
            while (it.hasNext()) {
                fw.write(System.lineSeparator());
                Employee emp = it.next();
                fw.write(emp.toString());
                fw.write(';');
                fw.write(String.valueOf(emp.getDaysDiff()));
            }
        }
    }
    public void out4() throws IOException {
        Scanner sc = new Scanner(new File("input4.txt"));
        FileWriter fw = new FileWriter("output4.txt");
        try (sc; fw) {
            int empId = sc.nextInt();
            int sum;
            if (employees.containsKey(empId)) {
                Employee emp = employees.get(empId);
                sum = emp.getVacation() - emp.getMonthsDiff() * 2;
                if (sum >= 0) {
                    fw.write(String.valueOf(sum));
                }
            } else {
                fw.write(String.valueOf(-1));
            }
        }
    }
}
