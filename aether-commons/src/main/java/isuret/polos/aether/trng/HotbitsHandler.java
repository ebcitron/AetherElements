package isuret.polos.aether.trng;

import isuret.polos.aether.database.Database;
import isuret.polos.aether.domains.HotBitIntegers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotbitsHandler {

    private Database database;
    private List<Integer> hotbits = new ArrayList<Integer>();

    public HotbitsHandler(Database database) {
        this.database = database;
    }

    private synchronized void loadHotbitsFromHarddisk() throws IOException {

        if (hotbits.size() > 10000) return;

        HotBitIntegers integers = database.getHotBitPackage();

        for (Integer number : integers.getIntegerList()) {
            hotbits.add(number);
        }
    }

    public Integer nextInteger() {

        try {
            loadHotbitsFromHarddisk();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
