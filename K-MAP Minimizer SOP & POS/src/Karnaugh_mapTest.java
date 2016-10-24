import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by msrabon on 10/25/16.
 */
public class Karnaugh_mapTest {
    @Test
    public void showResult() throws Exception {
        Karnaugh_map map = new Karnaugh_map();

//        map.initiate_Kmap_three();
//        boolean[] a = new boolean[16];
//        boolean[] c = a.clone();
//        boolean[] b = Arrays.copyOf(a, a.length);
//        b[3] = true;
//        System.out.println(a[3] + "  " + b[3] + "  " + c[3]);
        map.test_run();
        map.k_mapSolver_three();
        map.showResult();
    }

}