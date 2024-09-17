package self.roashe.kanutils.backend;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class GeneralTest {

    @Test
    public void testCollections(){
        System.out.println(Set.copyOf(List.of("A", "B")).equals(Set.copyOf(List.of("B", "A"))));
        System.out.println(List.of("A", "B").equals(List.of("B", "A")));
        System.out.println(Set.copyOf(List.of("A", "B")).hashCode());
        System.out.println(Set.copyOf(List.of("A", "B")).hashCode());
        System.out.println(Set.copyOf(List.of("B", "A")).hashCode());
    }


}
