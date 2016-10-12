/**
 * Created by msrabon on 12-Oct-16.
 * Project Name: K-MAP Minimizer SOP & POS.
 */
public class Kmap {

    public static void main(String[] args) {
        Karnaugh_map my_kmap = new Karnaugh_map();

        my_kmap.insertToKmap();

        my_kmap.showKmapGrid();
    }

}
