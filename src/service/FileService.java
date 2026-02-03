package service;
import model.NumberItem;
import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    // Save all
    public static void saveAll(List<NumberItem> list) {
        saveToJson(list);
        saveToXml(list);
        System.out.println("Saved to JSON & XML");
    }

    private static void saveToJson(List<NumberItem> list) {
        try (FileWriter writer = new FileWriter("data.json")) {
            writer.write("{\"items\":[");
            for (int i = 0; i < list.size(); i++) {
                NumberItem item = list.get(i);
                writer.write("{\"id\":" + item.getId()
                        + ",\"value\":" + item.getValue() + "}");
                if (i != list.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]}");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  static void saveToXml(List<NumberItem> list) {
        try (FileWriter writer = new FileWriter("data.xml")) {
            writer.write("<items>\n");
            for (NumberItem item : list) {
                writer.write("  <item>\n");
                writer.write("    <id>" + item.getId() + "</id>\n");
                writer.write("    <value>" + item.getValue() + "</value>\n");
                writer.write("  </item>\n");
            }
            writer.write("</items>");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<NumberItem> readAll() {
        File json = new File("data.json");
        if (json.exists()) {
            return readFromJson();
        }
        return readFromXml();
    }

    private static List<NumberItem> readFromJson() {
        List<NumberItem> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.json"))) {
            String content = br.readLine();
            content = content.replaceAll("[^0-9.,]", "");
            String[] parts = content.split(",");

            for (int i = 0; i < parts.length; i += 2) {
                int id = Integer.parseInt(parts[i]);
                double value = Double.parseDouble(parts[i + 1]);
                list.add(new NumberItem(id, value));
            }
            System.out.println("Đã đọc dữ liệu từ JSON");
        } catch (Exception e) {
            System.out.println("Lỗi đọc JSON");
        }
        return list;
    }

    private static List<NumberItem> readFromXml() {
        List<NumberItem> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.xml"))) {
            String line;
            int id = 0;
            double value = 0;

            while ((line = br.readLine()) != null) {
                if (line.contains("<id>"))
                    id = Integer.parseInt(line.replaceAll("\\D+", ""));
                if (line.contains("<value>"))
                    value = Double.parseDouble(line.replaceAll("[^0-9.]", ""));
                if (line.contains("</item>"))
                    list.add(new NumberItem(id, value));
            }
            System.out.println("Đã đọc dữ liệu từ XML");
        } catch (Exception e) {
            System.out.println("Lỗi đọc XML");
        }
        return list;
    }
}
