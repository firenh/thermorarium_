package fireopal.thermorarium.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThermorariumGeneralUtil {
    public static <T> List<T> combine(List<T> list, Collection<T> add) {
        List<T> newList = new ArrayList<>(list);
        newList.addAll(add);
        return newList;
    }
}
