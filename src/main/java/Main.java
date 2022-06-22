import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String [] arr = new String[]{"name", "Anton1", "Vasily", "Max"};
        System.out.println(mapToUppercase(arr));

        System.out.println(getTotalNumberOfLettersOfNamesLongerThanFive(arr));

        List<List<String>> listList = new ArrayList<>();
        listList.add(new ArrayList<>(Arrays.asList(arr)));
        System.out.println(transform(listList));

        List<Person> personList = getPersonList();
        System.out.println(getOldestPerson(personList));

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        System.out.println(calculate(integerList));


        System.out.println(partitionAdults(personList));

        System.out.println(groupByNationality(personList));

        System.out.println(namesToString(personList));

    }

    private static List<Person> getPersonList() {
        Person person1 = new Person("Anton", 17, "Russian");
        Person person2 = new Person("NeAnton", 20, "NeRussian");
        Person person3 = new Person("Iya", 24, "Russian");
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        return personList;
    }

    public static Collection<String> mapToUppercase(String... names) {
//         Collection<String> uppercaseNames = new ArrayList<>();
        // for(String name : names) {
        // 	uppercaseNames.add(name.toUpperCase());
        // }
        // return uppercaseNames;
        return Arrays.stream(names).map(String::toUpperCase).collect(Collectors.toList());

    }

    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Arrays.stream(names).filter(n -> n.length()>5).mapToInt(String::length).sum();
    }

    public static List<String> transform(List<List<String>> collection) {
//        List<String> newCollection = new ArrayList<>();
//        for (List<String> subCollection : collection) {
//            for (String value : subCollection) {
//                newCollection.add(value);
//            }
//        }
//        return newCollection;

        return collection.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static Person getOldestPerson(List<Person> people) {
//        Person oldestPerson = new Person("", 0);
//        for (Person person : people) {
//            if (person.getAge() > oldestPerson.getAge()) {
//                oldestPerson = person;
//            }
//        }
//        return oldestPerson;

//        return people.stream().sorted(Comparator.comparing(Person::getAge).reversed()).findFirst().get();

        return people.stream().max(Comparator.comparing(Person::getAge)).get();
    }


    public static int calculate(List<Integer> numbers) {
//        int total = 0;
//        for (int number : numbers) {
//            total += number;
//        }
//        return total;

        return numbers.stream().reduce(0, Integer::sum);
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
//        Map<Boolean, List<Person>> map = new HashMap<>();
//        map.put(true, new ArrayList<>());
//        map.put(false, new ArrayList<>());
//        for (Person person : people) {
//            map.get(person.getAge() >= 18).add(person);
//        }
//        return map;

        return people.stream().collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
    }

    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
//        Map<String, List<Person>> map = new HashMap<>();
//        for (Person person : people) {
//            if (!map.containsKey(person.getNationality())) {
//                map.put(person.getNationality(), new ArrayList<>());
//            }
//            map.get(person.getNationality()).add(person);
//        }
//        return map;

        return people.stream().collect(Collectors.groupingBy(Person::getNationality, Collectors.toList()));

    }

    public static String namesToString(List<Person> people) {
//        String label = "Names: ";
//        StringBuilder sb = new StringBuilder(label);
//        for (Person person : people) {
//            if (sb.length() > label.length()) {
//                sb.append(", ");
//            }
//            sb.append(person.getName());
//        }
//        sb.append(".");
//        return sb.toString();

//        return "Names: " + people.stream().map(Person::getName).collect(Collectors.joining(", ")) + ".";

        return people.stream().map(Person::getName).collect(Collectors.joining(", ", "Names: ", "." ));
    }



}
