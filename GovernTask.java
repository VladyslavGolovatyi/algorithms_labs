import java.util.*;

public class GovernTask {
    static HashMap<String, List<String>> certificateToRequiredCertificates = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Govern input:");
        while (!(line = sc.nextLine()).equals("")){
            if(certificateToRequiredCertificates.containsKey(line.split(" ")[0])) {
                certificateToRequiredCertificates.get(line.split(" ")[0]).add(line.split(" ")[1]);
            } else {
                certificateToRequiredCertificates.put(line.split(" ")[0],new ArrayList<>(Collections.singletonList(line.split(" ")[1])));
            }
        }
        Set<String> result = new LinkedHashSet<>();
        for (String certificate:certificateToRequiredCertificates.keySet()) {
            if(!result.contains(certificate)) {
                addCertificateRecursively(result, certificate);
            }
        }
        result.forEach(System.out::println);
    }
    private static void addCertificateRecursively(Set<String> result, String certificate) {
        for (String requiredCertificate:certificateToRequiredCertificates.get(certificate)) {
            if(!certificateToRequiredCertificates.containsKey(requiredCertificate)) {
                result.add(requiredCertificate);
            } else {
                addCertificateRecursively(result, requiredCertificate);
            }
        }
        result.add(certificate);

    }
}
