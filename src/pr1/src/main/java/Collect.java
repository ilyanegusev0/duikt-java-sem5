import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Collect {

    public static void main(String[] args) throws Exception {
        Path root = rootDir();
        System.out.println("Корень: " + root);

        List<Path> files = new ArrayList<>();
        try (var s = Files.walk(root)) {
            s.filter(Files::isRegularFile)
                    .filter(Collect::isTarget)
                    .sorted()
                    .forEach(files::add);
        }

        if (files.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int included = 0;

        for (Path f : files) {
            String content;
            try {
                content = Files.readString(f, StandardCharsets.UTF_8);
            } catch (IOException e) {
                continue; // сюда .class уже не попадёт, но на всякий случай
            }

            String rel = root.relativize(f).toString().replace('\\', '/');
            sb.append("[ ").append(rel).append(" ]\n");
            sb.append("***\n");
            sb.append(content.stripTrailing());
            sb.append("\n\n");
            included++;
        }

        String text = sb.toString();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(text), null);
        Files.writeString(root.resolve("project_dump.txt"), text, StandardCharsets.UTF_8);

        System.out.println("Файлов: " + included + ", символов: " + text.length());
        System.out.println("✓ скопировано в буфер обмена");
    }

    /**
     * Папка, где лежит Collect.java.
     */
    static Path rootDir() {
        String cmd = System.getProperty("sun.java.command", "");
        String first = cmd.split("\\s+", 2)[0];
        if (first.endsWith(".java")) {
            Path p = Paths.get(first).toAbsolutePath().normalize();
            if (Files.isRegularFile(p)) return p.getParent();
        }
        return Paths.get("").toAbsolutePath().normalize();
    }

    /**
     * Только .java, кроме самого себя, и не в мусорных папках.
     */
    static boolean isTarget(Path f) {
        String name = f.getFileName().toString();

        if (!name.endsWith(".java")) return false;
        if (name.equals("Collect.java")) return false;

        for (Path part : f) {
            String n = part.toString();
            if (n.equals("target") || n.equals("build") || n.equals("out")
                    || n.equals("bin") || n.equals("node_modules") || n.equals(".git")) {
                return false;
            }
        }
        return true;
    }
}