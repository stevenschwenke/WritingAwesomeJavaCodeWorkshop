package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_2_golden_master;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Random;

class GoldenMaster {

    static final long SAMPLESIZE = 100L;

    private ByteArrayOutputStream getConsoleOutput() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        return stream;
    }

    private File generateFile(long seed) {
        return new File("goldenMasterData/" + seed + ".txt");
    }

    void generateGoldenMaster() throws IOException {
        for (long seed = 0; seed < SAMPLESIZE; seed++) {
            FileUtils.write(generateFile(seed), getGameResult(seed), Charset.defaultCharset());
        }
    }

    String getGameResult(long seed) {
        ByteArrayOutputStream stream = getConsoleOutput();
        GameRunner.play(new Random(seed));
        return stream.toString();
    }

    String getGoldenMaster(long seed) throws IOException {
        return FileUtils.readFileToString(generateFile(seed), Charset.defaultCharset());
    }

}
