package org.matsim.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filer {

    BufferedWriter writer;
    private String headers;

    public Filer(String filename) {
        File file = new File(filename);

        if(file.exists() == false)
            file.getParentFile().mkdirs();

        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String line) {
        try{
//            this.writer.newLine();
            this.writer.write(line);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.writer.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeHeaders(String headers) {
        if(this.headers != null) return;
//        try {
            this.headers = headers;
            write(headers);
////            writer.newLine();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void newHeaders(String headers) {
        this.headers = headers;
    }

    public void newHeaders(String[] headers) {
        String hdrs = "";

        for (String h : headers) {
            hdrs += h+",";
        }

        this.headers = hdrs;
    }

    public void writeHeaders() {
        String hdrs = headers;
        this.headers = null;
        writeHeaders(hdrs);
    }

    public void writeHeaders(String[] headers) {
        if(this.headers != null) return;

        String hdrs = "";

        for (String h : headers) {
            hdrs += h+",";
        }

        writeHeaders(hdrs);
    }

    public void writeToSpreadSheet(Integer numberOfColumns, String data) {
        String[] d = data.split(",");
        writeToSpreadSheet(numberOfColumns, d);
    }

    public void writeToSpreadSheet(Integer numberOfColumns, String[] data) {
        String line = "";
        // System.out.println(headers);
        for (int i = 0; i < data.length; i++) {
            if (i % (numberOfColumns) == 0) {
//                    write(line+"\n");
                try {
                    write(line);
                    writer.newLine();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                line = "";

            }
            line += data[i] + ",";
        }
        if(line.equals("") == false) {
            write(line + "\n");
            line = "";
        }
    }
    

}
